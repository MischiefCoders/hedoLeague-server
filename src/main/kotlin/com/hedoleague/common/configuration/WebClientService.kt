package com.hedoleague.common.configuration

import com.hedoleague.common.configuration.api.ApiClientService
import com.hedoleague.common.configuration.api.properties.ClientProperties
import com.hedoleague.common.configuration.api.properties.DomainProperties
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ClientHttpConnector
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import java.util.concurrent.TimeUnit

open class WebClientService protected constructor(domainProperties: DomainProperties, clientProperties: ClientProperties) :
  ApiClientService {
  private val webClient: WebClient

  init {
    val httpClient = HttpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, clientProperties.getConnectTimeout())
      .doOnConnected { conn: Connection -> conn
        .addHandlerLast(ReadTimeoutHandler(clientProperties.getReadTimeout(), TimeUnit.MILLISECONDS))
        .addHandlerLast(WriteTimeoutHandler(clientProperties.getWriteTimeout(), TimeUnit.MILLISECONDS))
      }

    val connector: ClientHttpConnector = ReactorClientHttpConnector(httpClient)

    webClient = WebClient.builder()
      .baseUrl(domainProperties.getDomain())
      .clientConnector(connector)
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .build()
  }

  override fun getThenResponse() {

  }

  override fun postThenResponse() {

  }
}