package com.epam.intellij.plugins.atg.references

import com.epam.intellij.plugins.atg.managers.ATGModuleManager
import com.intellij.psi.*

/**
 * @author Aleksei Prokofev
 */
class ATGModuleReference(element: PsiElement, val moduleName: String) : PsiReferenceBase<PsiElement>(element) {
    override fun getVariants(): Array<out Any> {
        return arrayOf()
    }

    override fun resolve(): PsiElement? {
        return ATGModuleManager.getInstance(myElement.project).modules[moduleName]
    }
}