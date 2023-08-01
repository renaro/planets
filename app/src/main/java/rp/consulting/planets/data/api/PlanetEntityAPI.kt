package rp.consulting.planets.data.api

@kotlinx.serialization.Serializable
data class PlanetEntityAPI(
    val id : Int,
    val name : String,
    val description : String,
    val url : String,
)
