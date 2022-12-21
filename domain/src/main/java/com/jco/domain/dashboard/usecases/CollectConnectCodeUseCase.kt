package com.englishscore.mpp.domain.connect.usecases

import com.englishscore.mpp.domain.connect.models.CodeValidation
import com.englishscore.mpp.domain.core.models.ResultWrapper

interface CollectConnectCodeUseCase {
    /**
     *  This method enables validating connect codes.
     *
     * @param   connectCode: The code supplied by the user to link to an organization.
     * @return  Result<CodeValidation>
     * */
    suspend fun validateConnectCode(connectCode: String): Result<CodeValidation>

    /**
     *  This method will update the name associated with the connect code.
     */
    suspend fun updateConnectName(firstName: String, lastName: String): ResultWrapper<Unit>

    /**
     *  This method will update the Test Taker Reference ID associated with the connect code.
     *
     *  @param ttrid: test taker reference id
     *  @param code: connect code that the test taker reference id should be associated with.
     */
    suspend fun updateTTRID(code:String, ttrid: String): Result<Unit>
}