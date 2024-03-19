package com.hedoleague.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class Score {

  private Winner winner;
  private String duration;

  private TimeScore halfTime;
  private TimeScore fullTime;

  @Getter
  @AllArgsConstructor
  public enum Winner {
    HOME_TEAM("HOME_TEAM"),
    AWAY_TEAM("AWAY_TEAM"),
    DRAW("DRAW")
    ;

    private final String name;
  }

  @Data
  public static class TimeScore {
    private int home;
    private int away;
  }

}
