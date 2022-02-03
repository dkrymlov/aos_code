cmake_minimum_required(VERSION 3.17)
project(Lab1_Krymlov C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(Lab1_Krymlov
        Checks.h
        Input.h
        Main.c
        Master.h
        Output.h
        Slave.h
        Structures.h)
