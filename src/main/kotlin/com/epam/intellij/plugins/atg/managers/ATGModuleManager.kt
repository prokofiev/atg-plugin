package com.epam.intellij.plugins.atg.managers


import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.lang.manifest.psi.Header
import org.jetbrains.lang.manifest.psi.ManifestFile
import java.util.*

/**
 * @author Aleksei Prokofev
 */
class ATGModuleManager private constructor(project: Project) : AbstractProjectComponent(project) {
    val modules: MutableMap<String, ManifestFile> = HashMap()

    companion object {
        const val MANIFEST_FILE = "MANIFEST.MF"
        fun getInstance(project: Project): ATGModuleManager {
            return project.getComponent(ATGModuleManager::class.java)
        }
    }

    override fun getComponentName(): String {
        return "com.epam.intellij.plugins.atg.managers.ATGModuleManager"
    }

    override fun projectOpened() {
        DumbService.getInstance(myProject).smartInvokeLater {
            val files = FilenameIndex.getFilesByName(myProject, MANIFEST_FILE, GlobalSearchScope.allScope(myProject))
            for (file in files) {
                processingManifestFile(file)
            }
        }
    }

    private fun processingManifestFile(file: PsiFile?) {
        if (file == null || file !is ManifestFile)
            return
        val headers = PsiTreeUtil.findChildrenOfType(file, Header::class.java)
        for (header in headers) {
            if(header.name.equals(ATGModuleTagNames.ATG_PRODUCT, true) && header.headerValue != null) {
                modules.put(header.headerValue!!.unwrappedText, file)
                break
            }
        }
    }
}


