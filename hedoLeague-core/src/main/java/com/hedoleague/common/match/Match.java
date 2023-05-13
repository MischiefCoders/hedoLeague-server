package com.hedoleague.common.match;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hedoleague.business.vo.Score;
import com.hedoleague.business.vo.Team;
import java.time.LocalDateTime;
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

}
