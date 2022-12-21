package com.englishscore.mpp.domain.connect.results


class ExpiredConnectCodeException : Exception("That connect code has expired")

class InvalidConnectCodeException : Exception("That connect code is invalid")