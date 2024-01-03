package com.hedoleague.common.match;

import static org.assertj.core.api.Assertions.assertThat;

import com.hedoleague.common.enumeration.TeamEnum;
import com.hedoleague.domain.match.MatchApiService;
import com.hedoleague.domain.match.TeamMatchesApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MatchApiServiceTest {

  @Autowired private MatchApiService matchApiService;

  @Test
  void getMathListByTeam() {
    int arsenalTeamId = TeamEnum.ARSENAL.getId();

    TeamMatchesApiResponse result = matchApiService.getMatchesByTeam(arsenalTeamId);
    assertThat(result).isNotNull();
  }

}