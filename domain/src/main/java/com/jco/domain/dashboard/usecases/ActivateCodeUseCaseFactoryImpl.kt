package com.englishscore.mpp.domain.connect.usecases

import com.englishscore.mpp.domain.connect.usecases.ActivateCodeUseCaseType.ACTIVATE_TEST
import com.englishscore.mpp.domain.connect.usecases.ActivateCodeUseCaseType.ACTIVATE_ORDER

interface ActivateCodeUseCaseFactory {
    fun <T : ActivateConnectCodeUseCase> create(type: ActivateCodeUseCaseType): T
}

class ActivateCodeUseCaseFactoryImpl(
    private val activateOrderConnectCodeUseCase: ActivateOrderConnectCodeUseCase,
    private val activateTestConnectCodeUseCase: ActivateTestConnectCodeUseCase
) : ActivateCodeUseCaseFactory {

    override fun <T : ActivateConnectCodeUseCase> create(type: ActivateCodeUseCaseType): T =
        when (type) {
            ACTIVATE_ORDER -> activateOrderConnectCodeUseCase
            ACTIVATE_TEST -> activateTestConnectCodeUseCase
        } as T
}

enum class ActivateCodeUseCaseType {
    ACTIVATE_TEST,
    ACTIVATE_ORDER
}