package com.epam.intellij.plugins.atg.nucleus

import com.intellij.lang.Language

/**
 * @author Aleksei Prokofev
 */
class NucleusComponentLanguage private constructor(): Language("NucleusComponent", "text/properties") {
    companion object {
        val INSTANCE = NucleusComponentLanguage()
    }
}