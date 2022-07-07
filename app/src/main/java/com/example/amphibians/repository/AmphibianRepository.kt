package com.example.amphibians.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.amphibians.database.AmphibianDatabase
import com.example.amphibians.database.asDatabaseModel
import com.example.amphibians.database.asDomainModel
import com.example.amphibians.domain.AmphibianDomain
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AmphibianRepository(private val database: AmphibianDatabase) {


    suspend fun refreshAmphibians() {
        withContext(Dispatchers.IO) {
            //получили данные из сети
            val amphibians = AmphibianApi.retrofitService.getAmphibians()

            //внесли их в базу в виде объектов AmphibianDatabaseEntity
            database.amphibianDao.insertAll(amphibians.asDatabaseModel())

        }
    }

    // запрашиваем данные из базы и приводим их к модели AmphibianDomain
    // данный результат запрашиваем во OverviewViewModel
    val amphibians: LiveData<List<AmphibianDomain>> =
        Transformations.map(database.amphibianDao.getAmphibians()) {
            it.asDomainModel()
        }

}

