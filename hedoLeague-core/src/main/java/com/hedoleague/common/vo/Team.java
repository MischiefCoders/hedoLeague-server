package com.hedoleague.common.vo;

import com.hedoleague.domain.match.entity.Match;
import com.hedoleague.domain.table.vo.TableTeam;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Team {

  private String id;
  private String name;
  private String shortName;
  private String tla;
  private String crest;

  public TableTeam makeTableTeamByMatches(List<Match> matches) {
    Team team = new Team();
    int won = 0;
    int drawn = 0;
    int lost = 0;

    int goalsFor = 0;
    int goalsAgainst = 0;

    for (Match match : matches) {
      Score score = match.getScore();

      if (isHomeTeam(match)) {
        team = match.getHomeTeam();
        goalsFor += score.getFullTime().getHome();
        goalsAgainst += score.getFullTime().getAway();
      } else {
        team = match.getAwayTeam();
        goalsFor += score.getFullTime().getAway();
        goalsAgainst += score.getFullTime().getHome();
      }

      switch (score.getWinner()) {
        case HOME_TEAM:
          if (isHomeTeam(match)) {
            won++;
          } else {
            lost++;
          }
          break;
        case AWAY_TEAM:
          if (isHomeTeam(match)) {
            lost++;
          } else {
            won++;
          }
          break;
        case DRAW:
          drawn++;
          break;
      }
    }

    return TableTeam.builder()
        .team(team)
        .played(won + drawn + lost)
        .points(won * 3 + drawn)
        .won(won)
        .drawn(drawn)
        .lost(lost)
        .goalsFor(goalsFor)
        .goalsAgainst(goalsAgainst)
        .goalDifference(goalsFor - goalsAgainst)
        .build();
  }


  private boolean isHomeTeam(Match match) {
    return StringUtils.equals(match.getHomeTeam().getId(), this.getId());
  }

}
