#pragma once
#pragma warning(disable:4996)

#include <stdio.h>
#include <stdlib.h>
#include "Structs.h"
#include "Checks.h"
#include "Parent.h"

#define CHILD_DATA "child.fl"
#define CHILD_GARBAGE "child_garbage.txt"
#define CHILD_SIZE sizeof(struct Child)

int updateParent(struct Parent parent, char* error);

void reopenDatabase(FILE* database)
{
	fclose(database);
	database = fopen(CHILD_DATA, "r+b");
}

void linkAddresses(FILE* database, struct Parent parent, struct Child child)
{
	reopenDatabase(database);

	struct Child previous;

	fseek(database, parent.firstChildAddress, SEEK_SET);

	for (int i = 0; i < parent.childCount; i++)		    // ітеруємо список до останнього елемента
	{
		fread(&previous, CHILD_SIZE, 1, database);
		fseek(database, previous.nextAddress, SEEK_SET);
	}

	previous.nextAddress = child.selfAddress;			// зв'язка адрес
	fwrite(&previous, CHILD_SIZE, 1, database);	// збереження оновленого файлу
}

void relinkAddresses(FILE* database, struct Child previous, struct Child child, struct Parent* parent)
{
	if (child.selfAddress == parent->firstChildAddress)		// немає попереднього елемента - створення першого запису
	{
		if (child.selfAddress == child.nextAddress)			// наступника теж немає
		{
            parent->firstChildAddress = -1;					// Неможлива адреса щоб без помилок
		}
		else                                                // якщо наступник є
		{
            parent->firstChildAddress = child.nextAddress;  // перемістити на перше місце
		}
	}
	else                                                    // Попередник існує
	{
		if (child.selfAddress == child.nextAddress)			// наступного ел нема
		{
			previous.nextAddress = previous.selfAddress;    // Робимо попередник останнім
		}
		else                                                // наступник теж
		{
			previous.nextAddress = child.nextAddress;		// Робимо наступник наступником попередника
		}

		fseek(database, previous.selfAddress, SEEK_SET);	// Записуємо оновлений попередник
		fwrite(&previous, CHILD_SIZE, 1, database);
	}
}

void noteDeletedChild(long address)
{
	FILE* garbageZone = fopen(CHILD_GARBAGE, "rb");			//відкриваємо бінарний файл для читання

	int garbageCount;
	fscanf(garbageZone, "%d", &garbageCount);

	long* delAddresses = malloc(garbageCount * sizeof(long)); //Виділяємо місце під список "сміттєвих" адрес

	for (int i = 0; i < garbageCount; i++)
	{
		fscanf(garbageZone, "%ld", delAddresses + i);		//Заповнюємо його
	}

	fclose(garbageZone);									        // За допомогою цих двох команд
	garbageZone = fopen(CHILD_GARBAGE, "wb");				// повністю очищуємо файл зі "сміттям"
	fprintf(garbageZone, "%ld", garbageCount + 1);			// Записуємо нову кількість "сміттєвих" адрес

	for (int i = 0; i < garbageCount; i++)
	{
		fprintf(garbageZone, " %ld", delAddresses[i]);		// Заносимо "сміттєві" адреси назад
	}

	fprintf(garbageZone, " %d", address);					// дописуємо до них адресу щойно видаленого запису
	free(delAddresses);										        // Звільняємо виділену під масив пам'ять
	fclose(garbageZone);									        // Закриваємо файл
}

void overwriteGarbageAddress(int garbageCount, FILE* garbageZone, struct Child* record)
{
	long* delIds = malloc(garbageCount * sizeof(long));		// Виділяємо місце під список "сміттєвих" адрес

	for (int i = 0; i < garbageCount; i++)
	{
		fscanf(garbageZone, "%d", delIds + i);				// Заповнюємо його
	}

	record->selfAddress = delIds[0];						        // Для запису замість логічно видаленої "сміттєвої"
	record->nextAddress = delIds[0];

	fclose(garbageZone);									        // За допомогою цих двох команд
	fopen(CHILD_GARBAGE, "wb");							    // повністю очищуємо файл зі "сміттям"
	fprintf(garbageZone, "%d", garbageCount - 1);			// Записуємо нову кількість "сміттєвих" адрес

	for (int i = 1; i < garbageCount; i++)
	{
		fprintf(garbageZone, " %d", delIds[i]);				// Записуємо решту "сміттєвих" адрес
	}

	free(delIds);											        // Звільняємо виділену під масив пам'ять
	fclose(garbageZone);									        // Закриваємо файл
}

int insertChild(struct Parent parent, struct Child child, char* error)
{
    child.exists = 1;

	FILE* database = fopen(CHILD_DATA, "a+b");
	FILE* garbageZone = fopen(CHILD_GARBAGE, "rb");

	int garbageCount;											

	fscanf(garbageZone, "%d", &garbageCount);

	if (garbageCount)											     // Наявні видалені записи
	{
		overwriteGarbageAddress(garbageCount, garbageZone, &child);
		reopenDatabase(database);								     // Змінюємо режим доступу файлу
		fseek(database, child.selfAddress, SEEK_SET);			     // Ставимо курсор на "сміття" для подальшого перезапису
	}
	else                                                             // Видалених немає, пишемо в кінець файлу
	{
		fseek(database, 0, SEEK_END);

		int dbSize = ftell(database);

        child.selfAddress = dbSize;
        child.nextAddress = dbSize;
	}

	fwrite(&child, CHILD_SIZE, 1, database);					// Записуємо поставку до свого файлу

	if (!parent.childCount)								            // Поставок ще немає, пишемо адресу першої
	{
        parent.firstChildAddress = child.selfAddress;
	}
	else                                                             // Поставки вже є, оновлюємо "адресу наступника" останньої
	{
		linkAddresses(database, parent, child);
	}

	fclose(database);											      // Закриваємо файл

	parent.childCount++;										      // Стало на одну поставку більше
    updateParent(parent, error);								      // Оновлюємо запис постачальника

	return 1;
}

int getChild(struct Parent parent, struct Child* child, int productId, char* error)
{
	if (!parent.childCount)									            // У постачальника немає поставок
	{
		strcpy(error, "This parent has no child yet");
		return 0;
	}

	FILE* database = fopen(CHILD_DATA, "rb");


	fseek(database, parent.firstChildAddress, SEEK_SET);		        // Отримуємо перший запис
	fread(child, CHILD_SIZE, 1, database);

	for (int i = 0; i < parent.childCount; i++)				            // Шукаємо потрібний запис по коду деталі
	{
		if (child->productId == productId)						        // Знайшли
		{
			fclose(database);
			return 1;
		}

		fseek(database, child->nextAddress, SEEK_SET);
		fread(child, CHILD_SIZE, 1, database);
	}
	
	strcpy(error, "No such child in database");					// Не знайшли
	fclose(database);
	return 0;
}

// На вхід подається поставка з оновленими значеннями, яку треба записати у файл
int updateChild(struct Child child, int productId)
{
	FILE* database = fopen(CHILD_DATA, "r+b");

	fseek(database, child.selfAddress, SEEK_SET);
	fwrite(&child, CHILD_SIZE, 1, database);
	fclose(database);
	
	return 1;
}

int deleteChild(struct Parent parent, struct Child child, int productId, char* error)
{
	FILE* database = fopen(CHILD_DATA, "r+b");
	struct Child previous;

	fseek(database, parent.firstChildAddress, SEEK_SET);

	do		                                                    // Шукаємо попередника запису (його може й не бути,
	{															// тоді в попередника занесеться сам запис)
		fread(&previous, CHILD_SIZE, 1, database);
		fseek(database, previous.nextAddress, SEEK_SET);
	}
	while (previous.nextAddress != child.selfAddress && child.selfAddress != parent.firstChildAddress);

	relinkAddresses(database, previous, child, &parent);
    noteDeletedChild(child.selfAddress);						// Заносимо адресу видаленого запису у "смітник"

	child.exists = 0;											// Логічно не існуватиме

	fseek(database, child.selfAddress, SEEK_SET);				// ...але фізично
	fwrite(&child, CHILD_SIZE, 1, database);					// записуємо назад
	fclose(database);

	parent.childCount--;										// Однією поставкою менше
    updateParent(parent, error);

}