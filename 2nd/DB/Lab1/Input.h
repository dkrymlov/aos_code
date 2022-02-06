#pragma once
#include <stdio.h>
#include <string.h>
#include "Structs.h"
#include "Parent.h"

void readParent(struct Parent* parent)
{
	char name[16];
	int status;

	name[0] = '\0';

	printf("Enter parent\'s name: ");
	scanf("%s", name);

	strcpy(parent->name, name);

	printf("Enter parent\'s status: ");
	scanf("%d", &status);

    parent->status = status;
}

void readChild(struct Child* child)
{
	int x;

	printf("Enter price: ");
	scanf("%d", &x);

    child->price = x;

	printf("Enter amount: ");
	scanf("%d", &x);

    child->amount = x;
}