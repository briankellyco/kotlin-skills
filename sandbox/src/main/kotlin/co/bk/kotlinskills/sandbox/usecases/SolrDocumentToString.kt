package co.bk.kotlinskills.sandbox.usecases

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.apache.solr.client.solrj.response.QueryResponse
import org.apache.solr.common.SolrDocument
import org.apache.solr.common.SolrDocumentList
import org.apache.solr.common.util.NamedList

fun main(args: Array<String>) {

    exampleSolrDocumentToString();
}


private fun exampleSolrDocumentToString() {

    val queryResponse = mockQueryResponse()

    stringifySolrDocumentList(queryResponse)
}

private fun stringifySolrDocumentList(queryResponse: QueryResponse) {
    val solrDocuments = queryResponse.results

    val stringBuilder = StringBuilder()

    for (solrDocument in solrDocuments) {
        // Construct the string representation of the SolrDocument
        stringBuilder.append("SolrDocument {")
        for (fieldName in solrDocument.fieldNames) {
            stringBuilder.append("\n  $fieldName: ${solrDocument[fieldName]}")
        }
        stringBuilder.append("\n}\n")
    }

    val response: NamedList<Any> = queryResponse.response // NamedList<Object>

    val responseStringGson = stringifyResponseUsingGson(queryResponse)
    stringBuilder.append("responseString gson: $responseStringGson")
    println(stringBuilder.toString())

    val stringBuilder2 = StringBuilder()
    val responseStringJackson = stringifyResponseUsingJackson(queryResponse)
    stringBuilder2.append("responseString jackson: $responseStringJackson")
    println(stringBuilder2.toString())
}

// Outputs solrDocuments & highlighting
private fun stringifyResponseUsingGson(queryResponse: QueryResponse): String {

    val map = convertNamedListToMap(queryResponse.response)

    val removeSensitiveFields = listOf("contact_email_msteams","contact_phone_numbers","contact_email_primary","role_featured_roles","alternative_contacts","reports_to", "highlighting")
    map.keys.removeAll(removeSensitiveFields)

    // Convert Map to JSON string using Gson
    return Gson().toJson(map)
}

private fun stringifyResponseUsingJackson(queryResponse: QueryResponse): String? {

    var result: String? = null
    try {
        val map = convertNamedListToMap(queryResponse.response)

        result = ObjectMapper().writeValueAsString(map)
    } catch (serializationError: JsonProcessingException) {
        println("stringifyResponse failed creating queryResponse json: ${serializationError.message}")
    }
    return result
}

private fun convertNamedListToMap(namedList: NamedList<Any>): MutableMap<String, Any> {
    val map = mutableMapOf<String, Any>()
    for (i in 0 until namedList.size()) {
        val key = namedList.getName(i)
        val value = namedList.getVal(i)

        if (value is NamedList<*>) {
            map[key] = convertNamedListToMap(value as NamedList<Any>)
        } else {
            map[key] = value
        }
    }
    return map
}

fun mockQueryResponse(): QueryResponse {
    val solrDocumentList = SolrDocumentList().apply {
        // Adding mocked SolrDocuments to the SolrDocumentList
        add(mockSolrDocument("1", "Document 1", 100))
        add(mockSolrDocument("2", "Document 2", 150))
        add(mockSolrDocument("3", "Document 3", 200))
    }

    // Java signature shows NamedList<Object> but of course in Kotlin the base object is "Any" and not "Object"
    val response = NamedList<Any>().apply {
        add("response", solrDocumentList) // Co-incidentally the solrDocumentList is stored in a variable named "response"; not the same as "response" field in SolrResponse object
        add("highlighting", generateMockHighlighting())
    }
    return QueryResponse(response, null)
}

fun mockSolrDocument(id: String, title: String, score: Int): SolrDocument {
    val solrDocument = SolrDocument()
    solrDocument["id"] = id
    solrDocument["title"] = title
    solrDocument["score"] = score

    return solrDocument
}

fun generateMockHighlighting(): NamedList<Any> {
    val highlightingInfo = NamedList<Any>()

    // Mock highlighting data for document 1
    val document1Highlights = NamedList<List<String>>()
    document1Highlights.add("named_entities_txt", listOf("highlight1", "highlight2"))

    highlightingInfo.add("person:291357", document1Highlights) // key is "internal"id"
    return highlightingInfo
}