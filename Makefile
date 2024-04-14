
PWD=$(shell pwd)
AAP_JUCE_DIR=$(PWD)/external/aap-juce

APP_NAME=AudiblePlanets

APP_BUILD_DIR=$(PWD)
APP_SRC_DIR=$(PWD)/external/AudiblePlanets
JUCE_DIR=$(APP_SRC_DIR)/JUCE

# For metadata updates, relative to build-desktop
APP_SHARED_CODE_LIBS="$(APP_NAME)_artefacts/lib$(APP_NAME)_SharedCode.a"

PATCH_FILE=$(shell pwd)/aap-juce-support.patch
PATCH_DEPTH=1

# JUCE patches if any
JUCE_PATCHES= \
	$(PWD)/external/aap-juce/juce-patches/7.0.6/export-jni-symbols.patch
JUCE_PATCH_DEPTH=1

include $(AAP_JUCE_DIR)/Makefile.cmake-common

