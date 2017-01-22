package com.epam.intellij.plugins.atg.references

import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import org.jetbrains.lang.manifest.psi.HeaderValuePart

/**
 * @author Aleksei Prokofev
 */

class ATGModuleReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(HeaderValuePart::class.java),
                object : PsiReferenceProvider() {
                    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<out PsiReference> {
                        val valuePart = element as HeaderValuePart
                        return arrayOf(ATGModuleReference(valuePart, valuePart.unwrappedText))
                    }
                }
        )
    }

}



