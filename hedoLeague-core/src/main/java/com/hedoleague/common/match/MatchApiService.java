package com.hedoleague.common.match;

import com.hedoleague.common.properties.UrlProperties;
import com.hedoleague.util.WebClientService;
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
  private final WebClientService webClientService;

  public TeamMatchesApiResponse getMatchesByTeam(int teamId) {
    Map<String, String> params = new HashMap<>();
    params.put("competitions", "PL");
    params.put("season", "2022");
    params.put("status", "FINISHED");

    Mono<TeamMatchesApiResponse> listMono = webClientService.get(urlProperties.getTeamsMatchesUrl(teamId), params,
        new ParameterizedTypeReference<TeamMatchesApiResponse>() {});


    return listMono.flux().toStream().findFirst().orElseThrow(IllegalArgumentException::new);
  }
}
