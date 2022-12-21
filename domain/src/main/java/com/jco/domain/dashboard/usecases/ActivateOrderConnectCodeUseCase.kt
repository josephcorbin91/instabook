package com.englishscore.mpp.domain.connect.usecases

import com.englishscore.kmp.core.domain.providers.UserIdProvider
import com.englishscore.mpp.domain.analytics.usecases.AnalyticsConnectLogger
import com.jco.domain.dashboard.repositories.ConnectRepository
import com.englishscore.mpp.domain.payment.repositories.OrdersRepository
import kotlinx.coroutines.flow.firstOrNull

interface ActivateOrderConnectCodeUseCase : ActivateConnectCodeUseCase {

    /**
     * Activates connect code using the orders api.
     */
    suspend fun activate(
        sittingId: String,
        connectCode: String
    ): Result<Unit>
}

class ActivateOrderConnectCodeUseCaseImpl(
    private val ordersRepository: OrdersRepository,
    private val connectRepository: ConnectRepository,
    private val userIdProvider: UserIdProvider,
    private val analyticsConnectLogger: AnalyticsConnectLogger
) : ActivateOrderConnectCodeUseCase {

    override suspend fun activate(
        sittingId: String,
        connectCode: String
    ): Result<Unit> {
        val userId = userIdProvider.getUserId()
            .getOrElse { return Result.failure(Throwable("Unable to get user id")) }
        val testTakerReferenceID =
            connectRepository.getConnectInfo(connectCode).firstOrNull()?.testTakerReferenceId
        val result = ordersRepository.activateConnectCode(
            userId = userId,
            sittingId = sittingId,
            testTakerReferenceID = testTakerReferenceID,
            connectCode = connectCode
        )
        if (result.isSuccess) {
            analyticsConnectLogger.logConnectCodeCompleted(connectCode)
        } else {
            analyticsConnectLogger.logConnectCodeFailure(
                connectCode,
                reason = result.exceptionOrNull()?.message
                    ?: "Failed with non-retrievable exception"
            )
        }
        return result
    }
}