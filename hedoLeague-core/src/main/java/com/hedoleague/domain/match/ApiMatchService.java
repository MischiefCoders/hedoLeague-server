package com.hedoleague.domain.match;

import com.hedoleague.configuration.properties.UrlProperties;
import com.hedoleague.domain.match.entity.Match;
import com.hedoleague.infrastructure.ApiClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ApiMatchService implements MatchService {

  private final UrlProperties urlProperties;
  private final ApiClient apiClient;

  public List<Match> getMatchesByTeam(int teamId, String season) {
    Map<String, String> params = new HashMap<>();
    params.put("competitions", "PL");
    params.put("season", season);
    params.put("status", "FINISHED");

    Mono<TeamMatchesApiResponse> listMono = apiClient.get(urlProperties.getTeamsMatchesUrl(teamId), params, new ParameterizedTypeReference<>() {
    });

    TeamMatchesApiResponse response = listMono.block();

    return response != null ? response.getMatches() : null;
  }
}
