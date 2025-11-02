package fr.xgouchet.sitelenlipu.data.model

data class CardDisplay(
    val sitelenPona: Boolean,
    val sitelenPilin: Boolean,
    val sitelenJelo: Boolean,
    val tokiPona: Boolean,
    val tokiJan: Boolean,
) {
    companion object {
        val VIEW_ALL = CardDisplay(
            sitelenPona = true,
            sitelenPilin = true,
            sitelenJelo = true,
            tokiPona = true,
            tokiJan = true,
        )

        val VIEW_TOKI_AND_SITELEN = CardDisplay(
            sitelenPona = true,
            sitelenPilin = true,
            sitelenJelo = true,
            tokiPona = true,
            tokiJan = false,
        )
    }
}
