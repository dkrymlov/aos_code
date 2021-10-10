//
// Created by Danil on 30.09.2021.
//

#ifndef SERVER_UTILS_H
#define SERVER_UTILS_H

#include <string>
#include <ctime>


using namespace std;

string getActualTime(){
    time_t seconds = time(NULL);
    tm* timeinfo = localtime(&seconds);
    return asctime(timeinfo);
}

#endif //SERVER_UTILS_H
