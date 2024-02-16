package co.bk.kotlinskills.sandbox.builders

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.microsoft.graph.models.FieldValueSet
import com.microsoft.graph.serializer.AdditionalDataManager
import com.microsoft.graph.serializer.IJsonBackedObject
import com.microsoft.graph.serializer.ISerializer
import javax.annotation.Nullable

class SharepointFieldValueSetBuilder private constructor() {
    private var jsonElement: JsonElement? = null

    fun withJsonElement(jsonElement: JsonElement): SharepointFieldValueSetBuilder {
        this.jsonElement = jsonElement
        return this
    }

    fun build(): FieldValueSet {
        val fieldValueSet = FieldValueSet()
        val jsonObject = jsonElement?.asJsonObject

        val additionalDataManager = AdditionalDataManager(object : IJsonBackedObject {
            override fun setRawObject(serializer: ISerializer, json: JsonObject) {
                // Not used in this example
            }

            @Nullable
            override fun additionalDataManager(): AdditionalDataManager? {
                return null // Avoiding recursion for AdditionalDataManager
            }
        })

        jsonObject?.let {
            for ((key, value) in it.entrySet()) {
                when (key) {
                    "Stichw_x00f6_rter" -> {
                        val termsArray = value.asJsonArray

                        val labelsArray = mutableListOf<String>()
                        termsArray.map { termElement ->
                            val termObject = termElement.asJsonObject
                            val label = termObject.get("Label").asString
                            labelsArray.add(label)
                        }
                        fieldValueSet.additionalDataManager().put(key, convertListToJsonElement(labelsArray))
                    }
                    else -> {
                        fieldValueSet.additionalDataManager().put(key, value)
                    }
                }
            }
        }
        return fieldValueSet
    }

    private fun convertListToJsonElement(stringList: MutableList<String>): JsonElement {
        val jsonArray = JsonArray()
        stringList.forEach { item ->
            jsonArray.add(item)
        }
        return jsonArray
    }

    companion object {
        fun create(): SharepointFieldValueSetBuilder {
            return SharepointFieldValueSetBuilder()
        }
    }
}