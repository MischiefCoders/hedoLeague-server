package com.hedoleague.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/***
 * WebClient 관련 유틸
 */
public class WebClientUtils {

  // FIXME : 인증키 관리 어떻게?
  private static final String X_AUTH_TOKEN = "89fcb776fb454ad3b8f5d1187b43a220";
  private static final WebClient webClient = WebClient.create();

  public static <T> Mono<T> get(URI url, Map<String, String> requestParams, ParameterizedTypeReference<T> responseType) {
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

  public static <T> Mono<T> post(String url, Object request, ParameterizedTypeReference<T> responseType) {
    return webClient.post()
        .uri(url)
        .body(BodyInserters.fromValue(request))
        .retrieve()
        .bodyToMono(responseType);
  }

}
