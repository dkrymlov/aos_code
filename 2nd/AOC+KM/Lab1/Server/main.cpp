#include <iostream>
#include <winsock2.h>
#include "Addr.h"
#include <fstream>
#include "utils.h"

#define N1 100
#define PATH "E:\\Users\\Danil\\Desktop\\KNU2020\\AOC\\FirstLab\\Server\\serverlog.txt"

using namespace std;

int Counter = 0;
const char AUTHOR[] = "Krymlov Danil, 4th, Client chat";

ofstream serverlog(PATH);

SOCKET Connections[N1];

void ClientHandler(int index) {
    char msg[256];
    while (true) {
        recv(Connections[index], msg, sizeof(msg), NULL);
        string message = msg;
        if (message == "exit") {
            serverlog << "Client " << index << ": " << message << ". Command. Time: " << getActualTime() << endl;
            break;
        }

        for (int i = 0; i < Counter; i++) {
            if (i == index) {
                continue;
            }
            if (message == "Who") {
                send(Connections[index], AUTHOR, sizeof(AUTHOR), NULL);
                serverlog << "Client " << index << ": " << message << ". Command. Time: " << getActualTime() << endl;
            }else {
                send(Connections[i], msg, sizeof(msg), NULL);
                serverlog << "Client " << index << ": " << message << ". Message. Time: " << getActualTime() << endl;
            }
        }
    }
}

int main(int argc, char *arhv[]) {

    Addr init;
    SOCKADDR_IN addr = init.getAddr();
    int sizeOfAddr = sizeof(addr);

    WSAData wsaData;
    WORD DLLVersion = MAKEWORD(2, 1);
    if (WSAStartup(DLLVersion, &wsaData) != 0) {
        cout << "Error" << endl;
        exit(1);
    }

    //create socket with name sListen
    SOCKET sListen = socket(AF_INET, SOCK_STREAM, NULL);
    bind(sListen, (SOCKADDR *) &addr, sizeOfAddr);
    listen(sListen, SOMAXCONN);

    SOCKET newConnection;
    for (int i = 0; i < 100; ++i) {
        newConnection = accept(sListen, (SOCKADDR *) &addr, &sizeOfAddr);

        if (newConnection == 0) {
            cout << "Error connecting to server" << endl;
        } else {
            cout << "Client " << i << " connected" << endl;
            serverlog << "Client " << i << " connected. Time: " << getActualTime() << endl;
            Connections[i] = newConnection;
            Counter++;
            CreateThread(NULL, NULL, (LPTHREAD_START_ROUTINE) ClientHandler, (LPVOID) (i), NULL, NULL);
        }
    }

    return 0;
}
