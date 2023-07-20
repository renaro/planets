package rp.consulting.planets.data.api

@kotlinx.serialization.Serializable
data class PlanetEntity(
    val id : Int,
    val name : String,
    val description : String,
    val url : String,
)
