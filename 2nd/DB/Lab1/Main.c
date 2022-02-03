#include <stdio.h>
#include "Structures.h"
#include "Master.h"
#include "Input.h"
#include "Output.h"


int main()
{
	struct Master master;
	struct Slave slave;

	while (1)
	{  
		int choice;
		int id;
		char error[51];

		printf("Choose option:\n0 - Quit\n1 - Insert Parent\n2 - Get Parent\n3 - Update Parent\n4 - Delete Parent\n5 - Insert Related Entity\n6 - Get Related Entity\n7 - Update Related Entity\n8 - Delete Related Entity\n9 - Entities Info\n");
		scanf("%d", &choice);

		switch (choice)
		{
			case 0:
				return 0;

			case 1:
				readMaster(&master);
				insertMaster(master);
				break;
				
			case 2:
				printf("Enter ID: ");
				scanf("%d", &id);
				getMaster(&master, id, error) ? printMaster(master) : printf("Error: %s\n", error);
				break;

			case 3:
				printf("Enter ID: ");
				scanf("%d", &id);

				master.id = id;
				
				readMaster(&master);
				updateMaster(master, error) ? printf("Updated successfully\n") : printf("Error: %s\n", error);
				break;

			case 4:
				printf("Enter ID: ");
				scanf("%d", &id);
				deleteMaster(id, error) ? printf("Deleted successfully\n") : printf("Error: %s\n", error);
				break;

			case 5:
				printf("Enter master\'s ID: ");
				scanf("%d", &id);

				if (getMaster(&master, id, error))
				{
					slave.masterId = id;
					printf("Enter product ID: ");
					scanf("%d", &id);

					slave.productId = id;
					readSlave(&slave);
					insertSlave(master, slave, error);
					printf("Inserted successfully. To access, use master\'s and product\'s IDs\n");
				}
				else
				{
					printf("Error: %s\n", error);
				}
				break;

			case 6:
				printf("Enter master\'s ID: ");
				scanf("%d", &id);

				if (getMaster(&master, id, error))
				{
					printf("Enter product ID: ");
					scanf("%d", &id);
					getSlave(master, &slave, id, error) ? printSlave(slave, master) : printf("Error: %s\n", error);
				}
				else
				{
					printf("Error: %s\n", error);
				}
				break;

			case 7:
				printf("Enter master\'s ID: ");
				scanf("%d", &id);

				if (getMaster(&master, id, error))
				{
					printf("Enter product ID: ");
					scanf("%d", &id);
					
					if (getSlave(master, &slave, id, error))
					{
						readSlave(&slave);
						updateSlave(slave, id);
						printf("Updated successfully\n");
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
				printf("Enter master\'s ID: ");
				scanf("%d", &id);

				if (getMaster(&master, id, error))
				{
					printf("Enter product ID: ");
					scanf("%d", &id);

					if (getSlave(master, &slave, id, error))
					{
						deleteSlave(master, slave, id, error);
						printf("Deleted successfully\n");
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
				info();
				break;

			default:
				printf("Invalid input, please try again\n");
		}

		printf("---------\n");
	}

	return 0;
}
