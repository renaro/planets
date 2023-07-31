package rp.consulting.planets.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rp.consulting.planets.data.api.ApiResult
import rp.consulting.planets.data.api.PlanetsService
import rp.consulting.planets.ui.main.PlanetData
import javax.inject.Inject

class PlanetsRepository @Inject constructor(private val service: PlanetsService) {
    suspend fun getPlanetList(): ApiResult<List<PlanetData>> {
        return withContext(Dispatchers.IO) {
            try {
                val planetsData = service.getPlanets().map {
                    PlanetData(it.name, it.description, it.url)
                }
                ApiResult.Success(planetsData)
            } catch (exception: java.lang.Exception) {
                ApiResult.Error(exception)
            }
        }
    }
}