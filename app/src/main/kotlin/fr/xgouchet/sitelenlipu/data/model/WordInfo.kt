package fr.xgouchet.sitelenlipu.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordInfo(
    val id: String,
    @SerialName("pu_verbatim") val puVerbatim: Map<String, String> = emptyMap(),
    val representations: Map<String, String> = emptyMap(),
    @SerialName("see_also") val seeAlso: List<String> = emptyList(),
    @SerialName("usage_category") val usageCategory: String = "unknown",
    val deprecated: Boolean?
)
