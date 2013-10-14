#include <string.h>
#include <jni.h>
#include <android/log.h>
#include <ArkBall.h>
#include <GLES/gl.h>
#include <GLES/glext.h>

#define  LOG_TAG    "libarkanoid:JNI:"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

extern "C" {
JNIEXPORT void JNICALL Java_com_maky_arkanoid_GameLayoutGL_testMethod(JNIEnv * env, jobject obj, jobject bitmap);
JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_init(JNIEnv * env, jobject obj);
JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_draw(JNIEnv * env, jobject obj, jobject bitmap);
JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_moveLeft(JNIEnv * env, jobject obj, jobject bitmap);
JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_moveRight(JNIEnv * env, jobject obj, jobject bitmap);
JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_tick(JNIEnv * env, jobject obj);

}

ArkBall *ball;

JNIEXPORT void JNICALL Java_com_maky_arkanoid_GameLayoutGL_testMethod(
		JNIEnv * env, jobject obj, jobject bitmap) {
	int ret;
	LOGI("testMethod JNI started");
	ball = new ArkBall();
	ball->init();
}

JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_init(JNIEnv * env, jobject obj) {
	LOGI("init JNI started");

}

JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_draw(JNIEnv * env, jobject obj, jobject bitmap) {
	LOGI("draw JNI started");
}

JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_moveLeft(JNIEnv * env, jobject obj, jobject bitmap) {
	LOGI("moveLeft JNI started");
}

JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_moveRight(JNIEnv * env, jobject obj, jobject bitmap) {
	LOGI("moveRight JNI started");
}

JNIEXPORT void JNICALL Java_com_maky_arkanoid_ArkanoidGame_tick(JNIEnv * env, jobject obj) {
	LOGI("tick JNI started");
}
