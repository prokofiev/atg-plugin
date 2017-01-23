package com.epam.intellij.plugins.atg.managers

import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import java.util.*

/**
 * @author Aleksei Prokofev
 */
class ATGComponentManager private constructor(project: Project): AbstractProjectComponent(project){
    val components: MutableMap<String, PsiFile> = HashMap()

    companion object {
        fun getInstance(project: Project): ATGComponentManager {
            return project.getComponent(ATGComponentManager::class.java)
        }
    }

    override fun getComponentName(): String {
        return "com.epam.intellij.plugins.atg.managers.ATGComponentManager"
    }

    override fun projectOpened() {
        DumbService.getInstance(myProject).smartInvokeLater {
            //TODO: loop by modules
        }
    }
}