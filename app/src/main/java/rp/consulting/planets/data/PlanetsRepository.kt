package rp.consulting.planets.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rp.consulting.planets.data.api.PlanetsService
import rp.consulting.planets.ui.main.PlanetData
import javax.inject.Inject

class PlanetsRepository @Inject constructor(private val service: PlanetsService) {
    suspend fun getPlanetList(): List<PlanetData> {
        return withContext(Dispatchers.IO) {
            service.getPlanets().map {
                PlanetData(it.name, it.description)
            }
        }
    }
}