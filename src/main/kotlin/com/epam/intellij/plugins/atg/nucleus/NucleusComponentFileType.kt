package com.epam.intellij.plugins.atg.nucleus

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

/**
 * @author Aleksei Prokofev
 */
class NucleusComponentFileType : LanguageFileType(NucleusComponentLanguage.INSTANCE) {
    companion object {
        val INSTANCE = NucleusComponentFileType()
    }

    override fun getIcon(): Icon? {
        return AllIcons.FileTypes.Properties
    }

    override fun getName(): String {
        return "Nucleus Component"
    }

    override fun getDefaultExtension(): String {
        return "properties"
    }

    override fun getDescription(): String {
        return "ATG Nucleus component definition file."
    }
}