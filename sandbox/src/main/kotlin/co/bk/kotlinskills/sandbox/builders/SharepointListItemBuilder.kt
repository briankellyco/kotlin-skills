package co.bk.kotlinskills.sandbox.builders

import com.microsoft.graph.models.FieldValueSet
import com.microsoft.graph.models.ListItem
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/*
 * Builder pattern -> https://github.com/iluwatar/java-design-patterns/tree/master/builder
 */
class SharepointListItemBuilder private constructor() {

    private var createdDateTime: OffsetDateTime? = null
    private var fields: FieldValueSet? = null
    private var id: String? = null
    private var name: String? = null
    private var lastModifiedDateTime: OffsetDateTime? = null
    private var webUrl: String? = null
    fun withCreatedDateTime(createdDateTime: String?): SharepointListItemBuilder {

        var asOffsetDateTime = OffsetDateTime.parse(createdDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        this.createdDateTime = asOffsetDateTime
        return this
    }

    fun withFields(fields: FieldValueSet?): SharepointListItemBuilder {
        this.fields = fields
        return this
    }
    fun withId(id: String?): SharepointListItemBuilder {
        this.id = id
        return this
    }

    fun withLastModifiedDateTime(lastModifiedDateTime: String?): SharepointListItemBuilder {

        var asOffsetDateTime = OffsetDateTime.parse(lastModifiedDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        this.lastModifiedDateTime = asOffsetDateTime
        return this
    }

    fun withName(name: String?): SharepointListItemBuilder {
        this.name = name
        return this
    }
    fun build(): ListItem {
        val listItem = ListItem()
        listItem.createdDateTime = createdDateTime
        listItem.fields = fields
        listItem.lastModifiedDateTime = lastModifiedDateTime
        listItem.webUrl = webUrl
        listItem.id = id
        listItem.name = name
        return listItem
    }

    companion object {
        fun create(): SharepointListItemBuilder {
            return SharepointListItemBuilder()
        }
    }
}