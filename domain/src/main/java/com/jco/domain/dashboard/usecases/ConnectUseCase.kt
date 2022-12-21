package com.englishscore.mpp.domain.connect.usecases

import com.englishscore.kmp.core.domain.models.ConnectCodeStatus.ACTIVE
import com.englishscore.kmp.core.domain.usecases.GetConnectInfoUseCase
import com.englishscore.mpp.domain.analytics.usecases.AnalyticsConnectLogger
import com.englishscore.mpp.domain.connect.models.CodeValidation
import com.jco.domain.dashboard.repositories.ConnectRepository
import com.englishscore.mpp.domain.connect.results.ExpiredConnectCodeException
import com.englishscore.mpp.domain.connect.results.InvalidConnectCodeException
import com.englishscore.mpp.domain.core.models.ApiSuccess
import com.englishscore.mpp.domain.core.models.ResultWrapper
import com.englishscore.mpp.domain.core.models.toResult
import com.englishscore.mpp.domain.core.models.toSuccess
import com.englishscore.mpp.domain.core.repositories.UserRepository

/**
 * UseCases combine repositories from the data layer to provide business logic to the presentation layer
 */
class ConnectCodeUseCaseImpl(
    private val userRepository: UserRepository,
    private val connectRepository: ConnectRepository,
    private val analyticsConnectLogger: AnalyticsConnectLogger
) : CollectConnectCodeUseCase, GetConnectInfoUseCase {
    override suspend fun getConnectInfo() = connectRepository.getConnectInfo(ACTIVE).toResult()

    override fun getConnectInfo(connectCode: String) = connectRepository.getConnectInfo(connectCode)

    override suspend fun validateConnectCode(connectCode: String): Result<CodeValidation> {
        return when (val result = connectRepository.validateCode(connectCode.uppercase().trim())) {
            is ApiSuccess -> {
                val valid = result.data.isValid
                analyticsConnectLogger.logConnectCodeValidated(connectCode, valid)
                if (valid) result.toResult() else Result.failure(ExpiredConnectCodeException())
            }
            else -> Result.failure(InvalidConnectCodeException())
        }
    }

    override suspend fun updateTTRID(code: String, ttrid: String) =
        connectRepository.updateTTRID(code, ttrid)

    override suspend fun updateConnectName(
        firstName: String,
        lastName: String
    ): ResultWrapper<Unit> {
        val trimmedFirstName = firstName.trim()
        val trimmedLastName = lastName.trim()
        val fullName = "$trimmedFirstName $trimmedLastName"
        return when (val result = userRepository.updateUser(fullName, trimmedLastName)) {
            is ResultWrapper.Success -> Unit.toSuccess()
            is ResultWrapper.Error -> result
        }
    }
}