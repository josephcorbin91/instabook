package com.jco.domain.dashboard.repositories

import com.jco.domain.dashboard.models.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfiles(): Flow<List<Profile>>
}