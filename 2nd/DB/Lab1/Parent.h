#pragma once
#pragma warning(disable:4996)

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Checks.h"
#include "Child.h"

#define PARENT_IND "parent.ind"
#define PARENT_DATA "parent.fl"
#define PARENT_GARBAGE "parent_garbage.txt"
#define INDEXER_SIZE sizeof(struct Indexer)
#define PARENT_SIZE sizeof(struct Parent)

void noteDeletedParent(int id)
{
	FILE* garbageZone = fopen(PARENT_GARBAGE, "rb");		// "rb": відкриваємо бінарний файл для читання

	int garbageCount;
	fscanf(garbageZone, "%d", &garbageCount);

	int* delIds = malloc(garbageCount * sizeof(int));		// Виділяємо місце під список "сміттєвих" індексів

	for (int i = 0; i < garbageCount; i++)
	{
		fscanf(garbageZone, "%d", delIds + i);				// Заповнюємо його
	}

	fclose(garbageZone);									// За допомогою цих двох команд
	garbageZone = fopen(PARENT_GARBAGE, "wb");				// повністю очищуємо файл зі "сміттям"
	fprintf(garbageZone, "%d", garbageCount + 1);			// Записуємо нову кількість "сміттєвих" індексів

	for (int i = 0; i < garbageCount; i++)
	{
		fprintf(garbageZone, " %d", delIds[i]);				// Заносимо "сміттєві" індекси назад...
	}

	fprintf(garbageZone, " %d", id);						// ...і дописуємо до них індекс щойно видаленого запису
	free(delIds);											// Звільняємо виділену під масив пам'ять
	fclose(garbageZone);									// Закриваємо файл
}

void rewriteGarbageId(int garbageCount, FILE* garbageZone, struct Parent* record)
{
	int* delIds = malloc(garbageCount * sizeof(int));		// Виділяємо місце під список "сміттєвих" індексів

	for (int i = 0; i < garbageCount; i++)
	{
		fscanf(garbageZone, "%d", delIds + i);				// Заповнюємо його
	}

	record->id = delIds[0];									// Для запису замість логічно видаленого "сміттєвого"

	fclose(garbageZone);									// За допомогою цих двох команд
	fopen(PARENT_GARBAGE, "wb");							// повністю очищуємо файл зі "сміттям"
	fprintf(garbageZone, "%d", garbageCount - 1);			// Записуємо нову кількість "сміттєвих" індексів

	for (int i = 1; i < garbageCount; i++)
	{
		fprintf(garbageZone, " %d", delIds[i]);				// Записуємо решту "сміттєвих" індексів
	}

	free(delIds);											// Звільняємо виділену під масив пам'ять
	fclose(garbageZone);									// Закриваємо файл
}

int insertParent(struct Parent record)
{
	FILE* indexTable = fopen(PARENT_IND, "a+b");			// "a+b": відкрити бінарний файл
	FILE* database = fopen(PARENT_DATA, "a+b");				// для запису в кінець та читання
	FILE* garbageZone = fopen(PARENT_GARBAGE, "rb");		// "rb": відкрити бінарний файл для читання
	struct Indexer indexer;
	int garbageCount;

	fscanf(garbageZone, "%d", &garbageCount);

	if (garbageCount)										// Наявні "сміттєві" записи, перепишемо перший з них
	{
        rewriteGarbageId(garbageCount, garbageZone, &record);

		fclose(indexTable);									// Закриваємо файли для зміни
		fclose(database);									// режиму доступу в подальшому

		indexTable = fopen(PARENT_IND, "r+b");				// Знову відкриваємо і змінюємо режим на
		database = fopen(PARENT_DATA, "r+b");				// "читання з та запис у довільне місце файлу"

		fseek(indexTable, (record.id - 1) * INDEXER_SIZE, SEEK_SET);
		fread(&indexer, INDEXER_SIZE, 1, indexTable);
		fseek(database, indexer.address, SEEK_SET);			// Ставимо курсор на "сміття" для подальшого перезапису	
	}
	else                                                    // Видалених записів немає
	{
		long indexerSize = INDEXER_SIZE;

		fseek(indexTable, 0, SEEK_END);						// Ставимо курсор у кінець файлу таблички

		if (ftell(indexTable))								// Розмір індексної таблички ненульовий (позиція від початку)
		{
			fseek(indexTable, -indexerSize, SEEK_END);		// Ставимо курсор на останній індексатор
			fread(&indexer, INDEXER_SIZE, 1, indexTable);	// Читаємо останній індексатор

			record.id = indexer.id + 1;						// Нумеруємо запис наступним індексом
		}
		else                                                // Індексна табличка порожня
		{
			record.id = 1;									// Індексуємо наш запис як перший
		}
	}

	record.firstChildAddress = -1;
	record.childCount = 0;

	fwrite(&record, PARENT_SIZE, 1, database);				// Записуємо в потрібне місце БД-таблички передану структуру

	indexer.id = record.id;									// Вносимо номер запису в індексатор
	indexer.address = (record.id - 1) * PARENT_SIZE;		// Вносимо адресу запису в індексатор
	indexer.exists = 1;										// Прапорець існування запису

	printf("Your parent\'s id is %d\n", record.id);

	fseek(indexTable, (record.id - 1) * INDEXER_SIZE, SEEK_SET); 
	fwrite(&indexer, INDEXER_SIZE, 1, indexTable);			// Записуємо індексатор у відповідну табличку, куди треба
	fclose(indexTable);										// Закриваємо файли
	fclose(database);

	return 1;
}

int getParent(struct Parent* parent, int id, char* error)
{
	FILE* indexTable = fopen(PARENT_IND, "rb");				// "rb": відкрити бінарний файл
	FILE* database = fopen(PARENT_DATA, "rb");				// тільки для читання
	
	if (!checkFileExistence(indexTable, database, error))
	{
		return 0;
	}
	
	struct Indexer indexer;

	if (!checkIndexExistence(indexTable, error, id))
	{
		return 0;
	}

	fseek(indexTable, (id - 1) * INDEXER_SIZE, SEEK_SET);	// Отримуємо індексатор шуканого запису
	fread(&indexer, INDEXER_SIZE, 1, indexTable);			// за вказаним номером

	if (!checkRecordExistence(indexer, error))
	{
		return 0;
	}

	fseek(database, indexer.address, SEEK_SET);				// Отримуємо шуканий запис з БД-таблички
	fread(parent, sizeof(struct Parent), 1, database);		// за знайденою адресою
	fclose(indexTable);										// Закриваємо файли
	fclose(database);

	return 1;
}

int updateParent(struct Parent parent, char* error)
{
	FILE* indexTable = fopen(PARENT_IND, "r+b");			// "r+b": відкрити бінарний файл
	FILE* database = fopen(PARENT_DATA, "r+b");				// для читання та запису

	if (!checkFileExistence(indexTable, database, error))
	{	
		return 0;
	}

	struct Indexer indexer;
	int id = parent.id;

	if (!checkIndexExistence(indexTable, error, id))
	{
		return 0;
	}

	fseek(indexTable, (id - 1) * INDEXER_SIZE, SEEK_SET);	// Отримуємо індексатор шуканого запису
	fread(&indexer, INDEXER_SIZE, 1, indexTable);			// за вказаним номером

	if (!checkRecordExistence(indexer, error))
	{
		return 0;
	}

	fseek(database, indexer.address, SEEK_SET);				// Позиціонуємося за адресою запису в БД
	fwrite(&parent, PARENT_SIZE, 1, database);				// Оновлюємо запис
	fclose(indexTable);										// Закриваємо файли
	fclose(database);

	return 1;
}

int deleteParent(int id, char* error)
{
	FILE* indexTable = fopen(PARENT_IND, "r+b");			// "r+b": відкрити бінарний файл
															// для читання та запису	
	if (indexTable == NULL)
	{
		strcpy(error, "database files are not created yet");
		return 0;
	}

	if (!checkIndexExistence(indexTable, error, id))
	{
		return 0;
	}

	struct Parent parent;
    getParent(&parent, id, error);

	struct Indexer indexer;

	fseek(indexTable, (id - 1) * INDEXER_SIZE, SEEK_SET);	// Отримуємо індексатор шуканого запису
	fread(&indexer, INDEXER_SIZE, 1, indexTable);			// за вказаним номером

	indexer.exists = 0;										// Запис логічно не існуватиме...

	fseek(indexTable, (id - 1) * INDEXER_SIZE, SEEK_SET);

	fwrite(&indexer, INDEXER_SIZE, 1, indexTable);			// ...але фізично буде присутній
	fclose(indexTable);										// Закриваємо файл [NB: якщо не закрити, значення не оновиться]

    noteDeletedParent(id);									// Заносимо індекс видаленого запису до "сміттєвої зони"

	
	if (parent.childCount)								// Були поставки, видаляємо всі
	{
		FILE* childDb = fopen(CHILD_DATA, "r+b");
		struct Child child;

		fseek(childDb, parent.firstChildAddress, SEEK_SET);

		for (int i = 0; i < parent.childCount; i++)
		{
			fread(&child, CHILD_SIZE, 1, childDb);
			fclose(childDb);
            deleteChild(parent, child, child.productId, error);

            childDb = fopen(CHILD_DATA, "r+b");
			fseek(childDb, child.nextAddress, SEEK_SET);
		}

		fclose(childDb);
	}
	return 1;
}