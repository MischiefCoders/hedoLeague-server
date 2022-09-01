package com.hedoleague.common.configuration.api.properties

interface ClientProperties {

  fun getWriteTimeout():Long

  fun getReadTimeout():Long

  fun getConnectTimeout():Int
}