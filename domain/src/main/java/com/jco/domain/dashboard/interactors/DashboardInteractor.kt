package com.jco.domain.dashboard.interactors

interface DashboardInteractor {

    suspend fun getProfiles(): Lis

    suspend fun getPosts()
}