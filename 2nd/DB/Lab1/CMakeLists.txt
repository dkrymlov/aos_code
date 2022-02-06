cmake_minimum_required(VERSION 3.17)
project(Lab1_Krymlov C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(Lab1_Krymlov
        Checks.h
        Input.h
        Main.c
        Parent.h
        Output.h
        Child.h
        Structs.h)
