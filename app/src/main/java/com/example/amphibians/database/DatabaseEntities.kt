package com.example.amphibians.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.amphibians.domain.AmphibianDomain

@Entity(tableName = "amphibian_database")
data class AmphibianDatabaseEntity constructor(
        @PrimaryKey
        val name: String,
        val type: String,
        val description: String
)

fun List<AmphibianDatabaseEntity>.asDomainModel(): List<AmphibianDomain> {
        return map {
                AmphibianDomain(
                        name = it.name,
                        description = it.description,
                        type = it.type,

                )
        }
}

fun List<AmphibianDomain>.asDatabaseModel(): List<AmphibianDatabaseEntity> {
        return map {
                AmphibianDatabaseEntity(
                        name = it.name,
                        description = it.description,
                        type = it.type,
                )
        }
}