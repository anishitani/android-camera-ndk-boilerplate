cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -Wall -Werror")

set(OPENCV_DIR $ENV{HOME}/tools/android/opencv/3.1.0)

include_directories(${OPENCV_DIR}/sdk/native/jni/include)

add_library( native
             SHARED
             native.cpp )

# include libraries needed for hello-jni lib
target_link_libraries(native
                      android
                      atomic
                      log
                      m
                      z
                      ${OPENCV_DIR}/sdk/native/libs/${ANDROID_ABI}/libopencv_imgproc.a
                      ${OPENCV_DIR}/sdk/native/libs/${ANDROID_ABI}/libopencv_core.a
                      ${OPENCV_DIR}/sdk/native/3rdparty/libs/${ANDROID_ABI}/libtbb.a)
