package com.epam.intellij.plugins.atg.nucleus

import com.epam.intellij.plugins.atg.managers.ATGComponentManager
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

/**
 * @author Aleksei Prokofev
 */
class NucleusComponentLineMarker : RelatedItemLineMarkerProvider() {
    override fun collectNavigationMarkers(element: PsiElement, result: MutableCollection<in RelatedItemLineMarkerInfo<PsiElement>>) {
        if(element.javaClass.name != "com.intellij.lang.properties.psi.impl.PropertyValueImpl")
            return
        val value = element.text
        if(!value.startsWith("/"))
            return
        val relatedComponent = ATGComponentManager.getInstance(element.project).components[value] ?: return
        val builder = NavigationGutterIconBuilder
                .create(AllIcons.Nodes.Ejb)
                .setTargets(relatedComponent)
                .setTooltipText("Navigate to component")
        result.add(builder.createLineMarkerInfo(element))
    }
}