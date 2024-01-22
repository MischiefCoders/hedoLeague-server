package com.hedoleague.common.util;

import com.hedoleague.domain.table.vo.TableTeam;
import com.hedoleague.common.vo.Team;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlComparatorTest {

  private List<TableTeam> teams;

  @BeforeEach
  public void setUp() {
    teams = new ArrayList<>();
    teams.add(makeTableTeam("A", 11, 5, 9, 4));
  }

  @Test
  @DisplayName("승점 다른 케이스")
  void test_comparator_difference_points() {
    teams.add(makeTableTeam("B", 15, 10, 15, 5));
    teams.sort(new PlComparator());

    Assertions.assertThat(teams.get(0).getTeam().getName()).isEqualTo("B");
  }

  @Test
  @DisplayName("골득실 다른 케이스")
  void test_comparator_different_goal_difference() {
    teams.add(makeTableTeam("B", 11, 10, 15, 5));
    teams.sort(new PlComparator());

    Assertions.assertThat(teams.get(0).getTeam().getName()).isEqualTo("B");
  }

  @Test
  @DisplayName("득점 다른 케이스")
  void test_comparator_different_goal_against() {
    teams.add(makeTableTeam("B", 11, 5, 10, 5));
    teams.sort(new PlComparator());

    Assertions.assertThat(teams.get(0).getTeam().getName()).isEqualTo("B");
  }

  private TableTeam makeTableTeam(String teamName, int points, int goalDifference, int goalsFor, int goalsAgainst) {
    return TableTeam.builder()
        .team(Team.builder().name(teamName).build())
        .points(points)
        .goalDifference(goalDifference)
        .goalsFor(goalsFor)
        .goalsAgainst(goalsAgainst)
        .build();
  }

}