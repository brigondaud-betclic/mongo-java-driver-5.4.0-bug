package org.example

import com.mongodb.kotlin.client.model.filteredPosOp
import com.mongodb.kotlin.client.model.path
import org.bson.types.ObjectId

data class Document(
    val _id: ObjectId,
    val elements: List<String>,
)

fun main() {
    var incorrectPathFound = false
    var index = 0

    while (!incorrectPathFound) {
        val path = Document::elements.filteredPosOp("identifier$index").path()
        val expectedPath = "elements.$[identifier$index]"

        incorrectPathFound = path != expectedPath

        if (incorrectPathFound) {
            println("Incorrect path for index $index: path is $path but should be $expectedPath")
        }
        index++
    }
}