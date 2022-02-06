#pragma once

#include <stdio.h>
#include "Parent.h"
#include "Structs.h"

void getParentInfo(struct Parent parent)
{
	printf("Parent\'s name: %s\n", parent.name);
	printf("Parent\'s status: %d\n", parent.status);
}

void getChildInfo(struct Child child, struct Parent parent)
{
	printf("Parent: %s, %d scores\n", parent.name, parent.status);
	printf("Price: %d\n", child.price);
	printf("Amount: %d\n", child.amount);
}