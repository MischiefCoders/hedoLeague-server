package com.hedoleague.common.configuration.api

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.hedoleague.business.application.external.properties.ExternalApiDomainProperties
import com.hedoleague.common.configuration.api.properties.ClientProperties
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ClientHttpConnector
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import java.net.URI
import java.util.concurrent.TimeUnit

open class WebClientService protected constructor(clientProperties: ClientProperties) : ApiClientService {

  private val webClient: WebClient
  private val objectMapper = ObjectMapper()
  private val domain = ExternalApiDomainProperties::domain;

  private inline fun <reified T> logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
  }

  private val logger = logger<WebClientService>()

  init {
    val httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, clientProperties.getConnectTimeout())
            .doOnConnected { conn: Connection ->
              conn
                      .addHandlerLast(ReadTimeoutHandler(clientProperties.getReadTimeout(), TimeUnit.MILLISECONDS))
                      .addHandlerLast(WriteTimeoutHandler(clientProperties.getWriteTimeout(), TimeUnit.MILLISECONDS))
            }

    val connector: ClientHttpConnector = ReactorClientHttpConnector(httpClient)

    webClient = WebClient.builder()
            .baseUrl(domain.toString())
            .clientConnector(connector)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
  }

  override fun <T> getThenResponse(uri: URI, request: Any, responseType: ParameterizedTypeReference<T>): Mono<T>? {
    return webClient
            .get()
            .uri { uriBuilder: UriBuilder ->
              objectToMultiValueMap(request)?.let {
                uriBuilder.path(uri.path).queryParams(it)
                        .build()
              }
            }
            .retrieve()
            .onStatus({ obj: HttpStatus -> obj.isError }) { null }
            .bodyToMono(responseType)
            .doOnError { error: Throwable? -> logger.warn(String.format("%s call fail.", uri), error) }
  }


  open fun objectToMultiValueMap(obj: Any): MultiValueMap<String, String>? {
    val map = objectMapper.convertValue(obj, object : TypeReference<Map<String, String>>() {});
    val linkedMultiValueMap = LinkedMultiValueMap<String, String>()
    map.forEach { (key: String, value: String?) -> linkedMultiValueMap.add(key, value) }

    return linkedMultiValueMap
  }
}