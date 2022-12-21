package com.englishscore.mpp.domain.connect.results

import com.englishscore.mpp.domain.core.models.ResultWrapper

class FailedSaveApplyCodeResult : ResultWrapper.Error(Exception("Save apply code failed"))

class FailedApplySavedCodeResult : ResultWrapper.Error(Exception("Failed to apply saved codes"))