package com.epam.intellij.plugins.atg.managers

import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.project.Project
import org.jetbrains.lang.manifest.psi.ManifestFile

import java.util.*

/**
 * @author Aleksei Prokofev
 */
class ATGComponentManager private constructor(project: Project): AbstractProjectComponent(project){
    val component: MutableMap<String, ManifestFile> = HashMap()

    companion object {
        fun getInstance(project: Project): ATGComponentManager {
            return project.getComponent(ATGComponentManager::class.java)
        }
    }

    override fun getComponentName(): String {
        return "com.epam.intellij.plugins.atg.managers.ATGModuleManager"
    }
}