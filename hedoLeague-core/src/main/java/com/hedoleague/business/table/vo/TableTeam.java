package com.hedoleague.business.table.vo;

import com.hedoleague.business.vo.Team;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TableTeam {

  private Team team;
  private Integer played;
  private Integer points;
  private Integer won;
  private Integer drawn;
  private Integer lost;
  private Integer goalsFor;
  private Integer goalsAgainst;
  private Integer goalDifference;

}
