package com.epam.intellij.plugins.atg.references

import com.epam.intellij.plugins.atg.managers.ATGModuleManager
import com.epam.intellij.plugins.atg.managers.ATGModuleTagNameProvider
import com.epam.intellij.plugins.atg.managers.ATGModuleTagNames
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.util.ProcessingContext
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.patterns.PlatformPatterns
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.lang.manifest.ManifestLanguage
import org.jetbrains.lang.manifest.psi.Header
import org.jetbrains.lang.manifest.psi.ManifestTokenType


/**
 * @author Aleksei Prokofev
 */

class ATGModuleCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(ManifestTokenType.HEADER_NAME).withLanguage(ManifestLanguage.INSTANCE),
                object : CompletionProvider<CompletionParameters>() {
                    public override fun addCompletions(parameters: CompletionParameters,
                                                       context: ProcessingContext,
                                                       resultSet: CompletionResultSet) {
                        for (name in ATGModuleTagNameProvider.names()) {
                            resultSet.addElement(LookupElementBuilder.create(name))
                        }
                    }
                }
        )
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(ManifestTokenType.HEADER_VALUE_PART)
                        .withLanguage(ManifestLanguage.INSTANCE),
                object : CompletionProvider<CompletionParameters>() {
                    public override fun addCompletions(parameters: CompletionParameters,
                                                       context: ProcessingContext,
                                                       resultSet: CompletionResultSet) {
                        val header = PsiTreeUtil.findFirstParent(parameters.position, { it is Header })
                                as? Header ?: return

                        if (header.name == ATGModuleTagNames.ATG_REQUIRED) {
                            val moduleManager = ATGModuleManager.getInstance(parameters.position.project)
                            for (name in moduleManager.modules.keys)
                                resultSet.addElement(LookupElementBuilder.create(name))
                        }
                    }
                }
        )
    }
}