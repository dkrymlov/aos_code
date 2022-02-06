#pragma once


struct Parent
{
	int id;
	char name[16];
	int status;
	long firstChildAddress;
	int childCount;
};

struct Child
{
	int parentId;
	int productId;
	int price;
	int amount;
	int exists;
	long selfAddress;
	long nextAddress;
};

struct Indexer
{
	int id;	
	int address;
	int exists;
};