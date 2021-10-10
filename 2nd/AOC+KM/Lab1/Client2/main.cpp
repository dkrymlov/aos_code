#include <iostream>
#pragma comment(lib, "ws2_32.lib")
#include <winsock2.h>
#include <fstream>
#include "Addr.h"
#include <ctime>

#define PATH "E:\\Users\\Danil\\Desktop\\KNU2020\\AOC\\FirstLab\\Client2\\clientlog.txt"

using namespace std;

SOCKET Connection;
ofstream clientLog(PATH);

string getActualTime(){
    time_t seconds = time(NULL);
    tm* timeinfo = localtime(&seconds);
    return asctime(timeinfo);
}

void ClientHandler() {
    char msg[256];
    while(true) {
        recv(Connection, msg, sizeof(msg), NULL);
        clientLog << "Client received msg from server: " << msg <<". Time: " << getActualTime() << endl;
        cout << msg << endl;
    }
}

int main(int argc, char* arhv[]) {

    char msg[256];

    WSAData wsaData;
    WORD DLLVersion = MAKEWORD(2, 1);
    if (WSAStartup(DLLVersion, &wsaData) != 0) {
        cout << "Error" << endl;
        exit(1);
    }

    //for keeping internet adress
    Addr init;
    SOCKADDR_IN addr = init.getAddr();
    int sizeOfAddr = sizeof(addr);

    Connection = socket(AF_INET, SOCK_STREAM, NULL);
    if (connect(Connection, (SOCKADDR*)&addr, sizeOfAddr) != 0){
        cout << "Error connecting to the server" << endl;
        return 1;
    }else cout << "Connected succesfully" << endl;

    CreateThread(NULL, NULL, (LPTHREAD_START_ROUTINE)ClientHandler, NULL, NULL, NULL);

    char msg1[256];
    while (true){
        string message;
        cin.getline(msg1, sizeof(msg1));
        message = msg1;
        send(Connection, msg1, sizeof(msg1), NULL);
        clientLog << "Client send msg to server: " << msg1 <<" Time: " << getActualTime() << endl;
        Sleep(10);
        if (message == "exit"){
            break;
        }
    }

    return 0;
}