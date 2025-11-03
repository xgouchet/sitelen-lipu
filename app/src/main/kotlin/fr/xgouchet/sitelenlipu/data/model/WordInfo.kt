package fr.xgouchet.sitelenlipu.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordInfo(
    val id: String,
    @SerialName("pu_verbatim") val puVerbatim: Map<String, String> = emptyMap(),
    @SerialName("toki_inli") val tokiInli: Map<String, String> = emptyMap(),
    val representations: Map<String, String> = emptyMap(),
    @SerialName("see_also") val seeAlso: List<String> = emptyList(),
    @SerialName("usage_category") val usageCategory: String = "unknown",
    val deprecated: Boolean?
) {

    fun tokiPona(): String {
        return representations[KEY_REPR_TOKI_PONA] ?: id
    }

    fun sitelenPilin(): String {
        return representations[KEY_REPR_SITELEN_PILIN].orEmpty()
    }

    fun sitelenJelo(): String {
        return representations[KEY_REPR_SITELEN_JELO].orEmpty()
    }

    fun tokiJan(): Map<String, String> {
        // TODO Get device / app locale
        if (tokiInli.isNotEmpty()) return tokiInli

        return puVerbatim["en"].orEmpty()
            .lines().associate {
                it.substringBefore(' ').lowercase() to it.substringAfter(' ')
            }
    }

    companion object {
        val KEY_REPR_TOKI_PONA = "ligatures"
        val KEY_REPR_SITELEN_PILIN = "sitelen_emosi"
        val KEY_REPR_SITELEN_JELO = "sitelen_jelo"
        val KEY_REPR_UCSUR = "ucsur"

        val TOKI_PONA = WordInfo(
            id="toki pona",
            representations = mapOf(
                KEY_REPR_TOKI_PONA to "toki pona"
            ),
            usageCategory = "core",
            deprecated = false
        )
    }
}
