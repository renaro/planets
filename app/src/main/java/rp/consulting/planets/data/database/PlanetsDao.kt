package rp.consulting.planets.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlanetsDao {

    @Insert
    fun insert(planets: List<PlanetDatabaseEntity>)
    @Query("SELECT * FROM planetdatabaseentity")
    fun getAll(): List<PlanetDatabaseEntity>
}