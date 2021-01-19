/*
 * Native implementation of Native.beginCountdown().
 *
 * This is native code, so it must be compiled differently
 * on every platform.
 *
 * On Linux, compile it with the following command:
 *   gcc -shared Native.c -o libNative.so
 *
 * Run the example like this:
 *   LD_LIBRARY_PATH=. scala Native
 */

#include "Native.h"

JNIEXPORT void JNICALL Java_Native_beginCountdown (JNIEnv *env, jobject obj) 
{
     int i;
     for (i=3; i>0; i--) {
	  printf("%d...\n", i);
	  sleep(1);
     }
     printf("BOOM!\n");
}
