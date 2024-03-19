package com.hedoleague.common.match;

import static org.assertj.core.api.Assertions.assertThat;

import com.hedoleague.common.enumeration.TeamEnum;
import com.hedoleague.domain.match.ApiMatchService;
import com.hedoleague.domain.match.entity.Match;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MatchApiServiceTest {

  @Autowired private ApiMatchService matchApiService;

  @Test
  void getMathListByTeam() {
    int arsenalTeamId = TeamEnum.ARSENAL.getId();

    List<Match> result = matchApiService.getMatchesByTeam(arsenalTeamId, "2023");
    assertThat(result).isNotNull();
  }

}