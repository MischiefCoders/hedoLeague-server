package com.hedoleague.common.configuration.api

import org.springframework.core.ParameterizedTypeReference
import reactor.core.publisher.Mono
import java.net.URI

interface ApiClientService {

  fun <T> getThenResponse(uri: URI, request: Any, responseType: ParameterizedTypeReference<T>): Mono<T>?
}