package com.epam.intellij.plugins.atg.references

import com.epam.intellij.plugins.atg.managers.ATGModuleManager
import com.epam.intellij.plugins.atg.managers.ATGModuleTagNames
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.icons.AllIcons
import org.jetbrains.lang.manifest.psi.Header


/**
 * @author Aleksei Prokofev
 */
class ATGModuleDependencyLineMarker : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(element: PsiElement,
                                          result: MutableCollection<in RelatedItemLineMarkerInfo<*>>) {
        if (element !is Header || element.name != ATGModuleTagNames.ATG_REQUIRED)
            return
        val project = element.getProject()
        val relatedName = element.headerValue?.unwrappedText ?: return
        val manager = ATGModuleManager.getInstance(project)
        val relatedFile = manager.modules[relatedName] ?: return
        val builder = NavigationGutterIconBuilder
                .create(AllIcons.Nodes.Ejb)
                .setTargets(relatedFile)
                .setTooltipText("Navigate to related module")
        result.add(builder.createLineMarkerInfo(element))
    }
}