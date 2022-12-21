package com.englishscore.mpp.domain.connect.results

import com.englishscore.mpp.domain.core.models.ResultWrapper

class FailedApplyConnectCodeResult : ResultWrapper.Error(Exception("Apply connect code failed."))