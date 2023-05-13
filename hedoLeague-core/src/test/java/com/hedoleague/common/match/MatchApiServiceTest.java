package com.hedoleague.common.match;


import com.hedoleague.common.enumeration.TeamEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MatchApiServiceTest {

  @Autowired
  private MatchApiService matchApiService;

  @Test
  void getMathListByTeam() {
    int arsenalTeamId = TeamEnum.ARSENAL.getId();

    TeamMatchesApiResponse result = matchApiService.getMatchesByTeam(arsenalTeamId);
    log.info("result : {}", result);
  }

}