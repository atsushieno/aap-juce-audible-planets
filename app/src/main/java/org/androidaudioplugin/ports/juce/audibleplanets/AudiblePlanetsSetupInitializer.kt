package org.androidaudioplugin.ports.juce.audibleplanets

import android.content.Context
import androidx.startup.Initializer
import java.io.File
import java.io.FileOutputStream

class AudiblePlanetsSetupInitializer : Initializer<Any?> {

    private fun xcopyFromAssetsToLocalStorage(context: Context, dst: File, src: String) {
        val list = context.assets.list(src) ?: return
        if (list.any()) {
            if (!dst.exists()) dst.mkdirs()
            for (sub in list)
                xcopyFromAssetsToLocalStorage(context, File(dst, sub), "$src/$sub")
        } else {
            FileOutputStream(dst).use { w ->
                context.assets.open(src).use { r -> r.copyTo(w) }
            }
        }
    }

    override fun create(context: Context): Any {
        // gin::Processor::getProgramDirectory() on Android resolves to:
        // dataDir/<devId>/<pluginName>/programs  (devId="com.void-star", pluginName="Audible Planets")
        val programsDir = File(context.dataDir, "com.void-star/Audible Planets/programs")
        if (programsDir.listFiles()?.isEmpty() != false)
            xcopyFromAssetsToLocalStorage(context, programsDir, "AudiblePlanets/programs")
        return ""
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
