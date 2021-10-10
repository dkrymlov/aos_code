# cmake_minimum_required(VERSION <specify CMake version here>)
project(Server)

set(CMAKE_CXX_STANDARD 17)
link_libraries(ws2_32 wsock32)
add_executable(Server main.cpp Addr.h utils.h)