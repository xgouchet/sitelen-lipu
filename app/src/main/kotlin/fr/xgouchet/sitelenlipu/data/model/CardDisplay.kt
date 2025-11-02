package fr.xgouchet.sitelenlipu.data.model

data class CardDisplay(
    val sitelenPona: Boolean,
    val sitelenPilin: Boolean,
    val tokiPona: Boolean,
    val tokiInli: Boolean,
    val tokiKanse: Boolean,
//    val tokiTosi: Boolean,
//    val tokiEpelanto: Boolean,
) {
    companion object {
        val VIEW_ALL = CardDisplay(
            sitelenPona = true,
            sitelenPilin = true,
            tokiPona = true,
            tokiInli = true,
            tokiKanse = true,
        )

        val VIEW_TOKI_AND_SITELEN = CardDisplay(
            sitelenPona = true,
            sitelenPilin = true,
            tokiPona = true,
            tokiInli = false,
            tokiKanse = false,
        )
    }
}
