package rp.consulting.planets.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rp.consulting.planets.data.api.PlanetsClient
import rp.consulting.planets.ui.main.PlanetData

class PlanetsRepository {

    private val service = PlanetsClient.getService()

    suspend fun getPlanetList(): List<PlanetData> {
        return withContext(Dispatchers.IO) {
            service.getPlanets().map {
                PlanetData(it.name, it.description)
            }
        }
    }
}