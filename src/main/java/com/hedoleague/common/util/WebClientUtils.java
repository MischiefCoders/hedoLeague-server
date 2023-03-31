package com.hedoleague.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  private static final WebClient webClient = WebClient.create();

  public static <T> Mono<T> get(String url, Object request, ParameterizedTypeReference<T> responseType) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder.path(url)
            .queryParams(objectToMultiValueMap(request))
            .build())
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

  private static MultiValueMap<String, String> objectToMultiValueMap(Object request) {
    ObjectMapper objectMapper = new ObjectMapper();

    MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
    objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {}).forEach(multiValueMap::add);

    return multiValueMap;
  }

}
