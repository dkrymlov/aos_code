//
// Created by Danil on 25.09.2021.
//

#ifndef SERVER_ADDR_H
#define SERVER_ADDR_H

#include <winsock2.h>
#include <string>

using namespace std;

class Addr {
private:
    //for keeping internet adress
    SOCKADDR_IN addr;
public:
    Addr(){
        //structure that keep ip-adress //localhost
        addr.sin_addr.s_addr = inet_addr("127.0.0.1");
        //structure that keep port
        addr.sin_port = htons(1029);
        //for keeping internet protocols
        addr.sin_family = AF_INET;
    }

    SOCKADDR_IN getAddr(){
        return addr;
    }

};


#endif //SERVER_ADDR_H
