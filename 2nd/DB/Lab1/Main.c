#include <stdio.h>
#include "Structs.h"
#include "Parent.h"
#include "Input.h"
#include "Output.h"


int main()
{
	struct Parent parent;
	struct Child child;

	while (1)
	{  
		int choice;
		int id;
		char error[51];

		printf("Choose action:\n0 - Exit\n1 - Create Parent\n2 - Get Parent\n3 - Update Parent\n4 - Delete Parent\n5 - Create Child\n6 - Get Child\n7 - Update Child\n8 - Delete Child\n9 - DB Info\n");
		scanf("%d", &choice);

		switch (choice)
		{
			case 0:
				return 0;

			case 1:
                readParent(&parent);
                insertParent(parent);
				break;
				
			case 2:
				printf("Enter Parent`s ID: ");
				scanf("%d", &id);
                getParent(&parent, id, error) ? getParentInfo(parent) : printf("Error: %s\n", error);
				break;

			case 3:
				printf("Enter Parent`s ID: ");
				scanf("%d", &id);

                parent.id = id;

                readParent(&parent);
                updateParent(parent, error) ? printf("Update success!\n") : printf("Error: %s\n", error);
				break;

			case 4:
				printf("Enter ID: ");
				scanf("%d", &id);
                deleteParent(id, error) ? printf("Delete success!\n") : printf("Error: %s\n", error);
				break;

			case 5:
				printf("Enter Parent`s ID: ");
				scanf("%d", &id);

				if (getParent(&parent, id, error))
				{
                    child.parentId = id;
					printf("Enter product ID: ");
					scanf("%d", &id);

                    child.productId = id;
                    readChild(&child);
                    insertChild(parent, child, error);
					printf("Created successfully. To access, use parent\'s and product\'s IDs\n");
				}
				else
				{
					printf("Error: %s\n", error);
				}
				break;

			case 6:
				printf("Enter Parent\'s ID: ");
				scanf("%d", &id);

				if (getParent(&parent, id, error))
				{
					printf("Enter product ID: ");
					scanf("%d", &id);
                    getChild(parent, &child, id, error) ? getChildInfo(child, parent) : printf("Error: %s\n", error);
				}
				else
				{
					printf("Error: %s\n", error);
				}
				break;

			case 7:
				printf("Enter Parent\'s ID: ");
				scanf("%d", &id);

				if (getParent(&parent, id, error))
				{
					printf("Enter product ID: ");
					scanf("%d", &id);
					
					if (getChild(parent, &child, id, error))
					{
                        readChild(&child);
                        updateChild(child, id);
						printf("Update success!\n");
					}
					else
					{
						printf("Error: %s\n", error);
					}
				}
				else
				{
					printf("Error: %s\n", error);
				}
				break;

			case 8:
				printf("Enter Parent\'s ID: ");
				scanf("%d", &id);

				if (getParent(&parent, id, error))
				{
					printf("Enter product ID: ");
					scanf("%d", &id);

					if (getChild(parent, &child, id, error))
					{
                        deleteChild(parent, child, id, error);
						printf("Delete success!\n");
					}
					else
					{
						printf("Error: %s\n", error);
					}
				}
				else
				{
					printf("Error: %s\n", error);
				}
				break;

			case 9:
                getInfo();
				break;

			default:
				printf("Incorrect input data!\n");
		}

		printf("---------\n");
	}

	return 0;
}
