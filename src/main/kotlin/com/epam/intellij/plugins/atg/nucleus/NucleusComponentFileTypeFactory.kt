package com.epam.intellij.plugins.atg.nucleus

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory

/**
 * @author Aleksei Prokofev
 */
class NucleusComponentFileTypeFactory : FileTypeFactory(){
    override fun createFileTypes(consumer: FileTypeConsumer) {
        consumer.consume(NucleusComponentFileType.INSTANCE, "properties")
    }
}