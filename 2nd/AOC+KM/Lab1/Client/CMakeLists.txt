cmake_minimum_required(VERSION 3.17)
project(Client)

set(CMAKE_CXX_STANDARD 17)
link_libraries(ws2_32 wsock32)
add_executable(Client main.cpp Addr.h)