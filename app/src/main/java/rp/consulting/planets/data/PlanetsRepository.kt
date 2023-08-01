package rp.consulting.planets.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rp.consulting.planets.data.api.ApiResult
import rp.consulting.planets.data.api.PlanetsService
import rp.consulting.planets.data.database.PlanetDatabaseEntity
import rp.consulting.planets.data.database.PlanetsDao
import rp.consulting.planets.ui.main.PlanetData
import javax.inject.Inject

class PlanetsRepository @Inject constructor(
    private val service: PlanetsService,
    private val dao: PlanetsDao
) {
    suspend fun getPlanetList(): ApiResult<List<PlanetData>> {
        return withContext(Dispatchers.IO) {
            try {
                val apiEntities = service.getPlanets()
                val planetsData = apiEntities.map {
                    PlanetData(it.name, it.description, it.url)
                }
                val databaseEntities = apiEntities.map {
                    PlanetDatabaseEntity(it.id, it.name, it.description, it.url)
                }
                dao.insert(databaseEntities)
                ApiResult.Success(planetsData)
            } catch (exception: java.lang.Exception) {
                val databaseEntities = dao.getAll()
                if (databaseEntities.isEmpty()) {
                    ApiResult.Error(exception)
                } else {
                    ApiResult.Success(
                        databaseEntities.map {
                            PlanetData(it.name, it.description, it.url)
                        }
                    )
                }
            }
        }
    }
}