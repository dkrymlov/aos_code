cmake_minimum_required(VERSION 3.17)
project(Client2)

set(CMAKE_CXX_STANDARD 17)
link_libraries(ws2_32 wsock32)
add_executable(Client2 main.cpp Addr.h)