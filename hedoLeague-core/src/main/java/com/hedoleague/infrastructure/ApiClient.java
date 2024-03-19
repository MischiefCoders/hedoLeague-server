package com.hedoleague.infrastructure;


import com.hedoleague.configuration.properties.UrlProperties;
import java.net.URI;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/***
 * 외부 API 통신을 위한 api client
 */
@Service
public class ApiClient {

  // FIXME : 인증키 관리 어떻게?
  private static final String X_AUTH_TOKEN = "89fcb776fb454ad3b8f5d1187b43a220";
  private final WebClient webClient;

  public ApiClient(UrlProperties urlProperties) {
    final ClientHttpConnector connector = new ReactorClientHttpConnector(HttpClient.create());

    webClient = WebClient.builder()
        .baseUrl(urlProperties.getDomain())
        .clientConnector(connector)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  public <T> Mono<T> get(URI url, Map<String, String> requestParams, ParameterizedTypeReference<T> responseType) {
    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
    multiValueMap.setAll(requestParams);

    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path(url.getPath())
            .queryParams(multiValueMap)
            .build())
        .header("X-Auth-Token", X_AUTH_TOKEN)
        .retrieve()
        .bodyToMono(responseType);
  }

}
