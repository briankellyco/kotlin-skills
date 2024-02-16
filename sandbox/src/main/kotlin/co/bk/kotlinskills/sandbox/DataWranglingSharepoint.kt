package co.bk.kotlinskills.sandbox

import co.bk.kotlinskills.sandbox.builders.SharepointFieldValueSetBuilder
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.microsoft.graph.models.ListItem
import co.bk.kotlinskills.sandbox.builders.SharepointListItemBuilder
import com.microsoft.graph.models.FieldValueSet
import java.util.*


fun main(args: Array<String>) {

    jsonToSharepointListItemBuilderPatternMapping(); // fields mapped correctly.
    jsonToSharepointListItemObjectMapperApproach();  // fields not mapped. use case essential.
}

fun jsonToSharepointListItemBuilderPatternMapping() {

    val jsonString = object {}.javaClass.classLoader.getResourceAsStream("sharepoint-drive-item-property-via-insomnia-07.json")?.bufferedReader()?.readText()

    val jsonElement: JsonElement = Gson().fromJson(jsonString, JsonElement::class.java)

    val fieldValueSet: FieldValueSet = SharepointFieldValueSetBuilder.create()
            .withJsonElement(jsonElement.asJsonObject.get("fields"))
            .build()

    // Using the Builder pattern to convert JsonElement to ListItem
    val listItem: ListItem = SharepointListItemBuilder.create()
            .withCreatedDateTime(jsonElement.asJsonObject.get("createdDateTime").asString)
            .withFields(fieldValueSet)
            .withId(jsonElement.asJsonObject.get("id").asString)
            .withLastModifiedDateTime(jsonElement.asJsonObject.get("lastModifiedDateTime").asString)
            .build()

    println("jsonToSharepointListItemBuilderPatternMapping... id: ${listItem?.id}")
}

fun jsonToSharepointListItemObjectMapperApproach() {

    println(Date().toInstant())
    // Approach works anywhere. Avoid File.readText(). https://discuss.kotlinlang.org/t/whats-the-intended-way-to-read-a-resource-file/23689
    var jsonString = object {}.javaClass.classLoader.getResourceAsStream("sharepoint-drive-item-property.json")?.bufferedReader()?.readText()

    /*
     * Sharepoint issues:
     * 1. Unknown properties and throws "com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException:"
     *    Solution:
     *      https://www.baeldung.com/jackson-deserialize-json-unknown-properties
     * 2. Exception in thread "main" com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
     *    Java 8 date/time type `java.time.OffsetDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling
     *    Solution:
     *      https://howtodoinjava.com/jackson/java-8-date-time-type-not-supported-by-default/
     * 3. Apparent performance problem only if debugging code (6 secs per mapping).
     *    Issue disappears if code run normally.
     * 4. Accuracy problem:
     *      object mapper not able to map json "fields" structure which is essential for use case.
     */

    val objectMapper = ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(JavaTimeModule());

    val listItem: ListItem = objectMapper.readValue(jsonString, ListItem::class.java)
    println("jsonToSharepointListItemObjectMapperApproach... id: ${listItem?.id}")
    println(Date().toInstant())
}
