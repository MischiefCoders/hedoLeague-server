package com.hedoleague.domain.match.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hedoleague.common.enumeration.TeamEnum;
import com.hedoleague.common.vo.Score;
import com.hedoleague.common.vo.Team;
import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.Data;

@Data
public class Match {

  private int id;
  private LocalDateTime utcDate;
  private String status;

  @JsonProperty("matchday")
  private String matchDay;

  private String stage;

  private Team homeTeam;
  private Team awayTeam;
  private Score score;

  public boolean isHedoLeagueMatch() {
    return Arrays.stream(TeamEnum.values()).anyMatch(teamEnum -> teamEnum.getId() == Integer.parseInt(this.homeTeam.getId())) &&
        Arrays.stream(TeamEnum.values()).anyMatch(teamEnum -> teamEnum.getId() == Integer.parseInt(this.awayTeam.getId()));
  }

}
