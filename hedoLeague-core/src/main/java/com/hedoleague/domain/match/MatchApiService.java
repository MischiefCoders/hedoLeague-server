package com.hedoleague.domain.match;

import com.hedoleague.configuration.properties.UrlProperties;
import com.hedoleague.infrastructure.ApiClient;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MatchApiService {

  private final UrlProperties urlProperties;
  private final ApiClient apiClient;

  // FIXME : 새시즌 돌입!
  public TeamMatchesApiResponse getMatchesByTeam(int teamId) {
    Map<String, String> params = new HashMap<>();
    params.put("competitions", "PL");
    params.put("season", "2023");
    params.put("status", "FINISHED");

    Mono<TeamMatchesApiResponse> listMono = apiClient.get(urlProperties.getTeamsMatchesUrl(teamId), params, new ParameterizedTypeReference<>() {});


    return listMono.flux().toStream().findFirst().orElseThrow(IllegalArgumentException::new);
  }
}
