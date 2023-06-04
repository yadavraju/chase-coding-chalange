package com.relayapp.live.presentation.model

data class Pokemon(
    var page: Long = 0,
    val name: String,
    val url: String
) {

    private val number
        get() =
            url.split("/").dropLast(1).last()

    val numberString
        get() = when (number.length) {
            1 -> "#00$number"
            2 -> "#0$number"
            else -> "#$number"
        }

    val imageUrl
        get() =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$number.png"
}

val list = listOf<Pokemon>(
    Pokemon(
        name = "Raju",
        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
    ),
    Pokemon(
        name = "Raju1",
        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"
    ),
    Pokemon(
        name = "Abhijeet",
        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"
    ),
    Pokemon(
        name = "Raju3",
        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"
    ),
    Pokemon(
        name = "RajuYadav",
        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png"
    ),
    Pokemon(
        name = "Raju5",
        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png"
    ),
    Pokemon(
        name = "Raju7",
        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"
    )
)
