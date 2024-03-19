package com.hedoleague.domain.table.vo;

import com.hedoleague.common.vo.Team;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TableTeam {

  private Integer position;
  private Team team;
  private Movement movement; // 순위 변동
  private Integer played;
  private Integer points;
  private Integer won;
  private Integer drawn;
  private Integer lost;
  private Integer goalsFor;
  private Integer goalsAgainst;
  private Integer goalDifference;

  private enum Movement {
    UP, NONE, DOWN;
  }

}
