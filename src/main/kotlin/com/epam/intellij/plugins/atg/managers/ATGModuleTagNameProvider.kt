package com.epam.intellij.plugins.atg.managers

/**
 * @author Aleksei Prokofev
 */
class ATGModuleTagNameProvider {
    companion object {
        fun names() = listOf(
                ATGModuleTagNames.ATG_PRODUCT,
                ATGModuleTagNames.ATG_REQUIRED
        )
    }
}