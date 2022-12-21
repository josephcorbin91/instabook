package com.jco.domain.dashboard.repositories

interface PostRepository {

    suspend fun updateCodeStatus(code: String, newStatus: ConnectCodeStatus): ResultWrapper<Unit>

    suspend fun updateTTRID(code: String, ttrid: String): Result<Unit>

    suspend fun validateCode(connectCode: String): ResultWrapper<CodeValidation>

    suspend fun getConnectInfo(status: ConnectCodeStatus): ResultWrapper<ConnectInfo>

    fun getConnectInfo(code: String): Flow<ConnectInfo?>
}