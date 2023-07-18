package rp.consulting.planets.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import rp.consulting.planets.ui.main.PlanetData

class PlanetsRepository {

    suspend fun getPlanetList(): List<PlanetData> {
        return withContext(Dispatchers.IO) {
            delay(4000)
            listOf(
                PlanetData("Terra", "Descricao Terra"),
                PlanetData("Jupiter", "Descricao Jupiter"),
                PlanetData("Mercúrio", "Descricao Mercúrio")
            )
        }
    }
}