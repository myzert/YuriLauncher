package com.movtery.zalithlauncher.renderer.renderers

import com.movtery.zalithlauncher.renderer.RendererInterface

class MobileGluesRenderer : RendererInterface {
    override fun getRendererId(): String = "opengles3"

    override fun getUniqueIdentifier(): String = "mobileglues-builtin-yurilauncher"

    override fun getRendererName(): String = "MobileGlues (OpenGL 4.0, Built-in)"

    override fun getRendererEnv(): Lazy<Map<String, String>> = lazy {
        mapOf(
            "LIBGL_EGL" to "libmobileglues.so",
            "POJAVEXEC_EGL" to "libmobileglues.so",
            "LIBGL_ES" to "3",
            "POJAV_RENDERER" to "opengles3",
            "POJAV_ZINK_PREFER_SYSTEM_DRIVER" to "1",
            "MG_MULTIDRAW" to "auto"
        )
    }

    override fun getDlopenLibrary(): Lazy<List<String>> = lazy { emptyList() }

    override fun getRendererLibrary(): String = "libmobileglues.so"
}
