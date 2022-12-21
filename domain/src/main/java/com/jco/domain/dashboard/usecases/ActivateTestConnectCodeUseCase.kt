package com.englishscore.mpp.domain.connect.usecases

import com.englishscore.kmp.core.domain.models.ConnectCodeStatus
import com.jco.domain.dashboard.repositories.ConnectRepository
import com.englishscore.mpp.domain.core.models.toResult

interface ActivateTestConnectCodeUseCase : ActivateConnectCodeUseCase {
    /**
     * Activates connect code by caching it in the local storage and
     * then retrieving it again once the user is ready to submit results.
     */
    suspend fun activate(
        connectCode: String,
    ): Result<Unit>
}

class ActivateTestConnectCodeUseCaseImpl(
    private val connectRepository: ConnectRepository,
) : ActivateTestConnectCodeUseCase {

    override suspend fun activate(
        connectCode: String,
    ) = connectRepository.updateCodeStatus(connectCode, ConnectCodeStatus.ACTIVE).toResult()
}