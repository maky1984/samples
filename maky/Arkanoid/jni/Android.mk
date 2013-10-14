LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := arkanoid
### Add all source file names to be included in lib separated by a whitespace
LOCAL_SRC_FILES := arkanoid.cpp \
				   ArkBall.h \
				   ArkBall.cpp
LOCAL_LDLIBS    := -lm -llog -lGLESv1_CM

include $(BUILD_SHARED_LIBRARY)
