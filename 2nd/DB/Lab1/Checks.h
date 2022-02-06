#pragma once

#include <stdio.h>
#include <stdlib.h>
#include "Child.h"

int getParent(struct Parent* parent, int id, char* error);

//перевірка існування файлів
int checkFileExistence(FILE* indexTable, FILE* database, char* error)
{
	//Файли бази данних ще не існують
	if (indexTable == NULL || database == NULL)
	{
		strcpy(error, "database files are not created yet");
		return 0;
	}

	return 1;
}

//преревірка існування індексів
int checkIndexExistence(FILE* indexTable, char* error, int id)
{
	fseek(indexTable, 0, SEEK_END);

	long indexTableSize = ftell(indexTable);

	if (indexTableSize == 0 || id * sizeof(struct Indexer) > indexTableSize)
	{
		strcpy(error, "no such ID in table");
		return 0;
	}

	return 1;
}

//перевірка існування записів
int checkRecordExistence(struct Indexer indexer, char* error)
{
	// запис був видалений
	if (!indexer.exists)
	{
		strcpy(error, "the record you\'re looking for has been removed");
		return 0;
	}

	return 1;
}

//отримати інфу
void getInfo()
{
	FILE* indexTable = fopen("parent.ind", "rb");

	if (indexTable == NULL)
	{
		printf("Error: database files are not created yet\n");
		return;
	}

	int parentCount = 0;
	int childCount = 0;

	fseek(indexTable, 0, SEEK_END);
	int indAmount = ftell(indexTable) / sizeof(struct Indexer);

	struct Parent parent;

	char dummy[51];

	for (int i = 1; i <= indAmount; i++)
	{
		if (getParent(&parent, i, dummy))
		{
			parentCount++;
            childCount += parent.childCount;

			printf("Parent #%d has %d child(s)\n", i, parent.childCount);
		}
	}

	fclose(indexTable);

	printf("Total parent count: %d\n", parentCount);
	printf("Total child count: %d\n", childCount);
}
