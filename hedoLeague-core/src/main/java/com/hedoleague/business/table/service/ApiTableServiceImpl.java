package com.hedoleague.business.table.service;

import com.hedoleague.business.table.vo.TableTeam;
import com.hedoleague.business.vo.Score;
import com.hedoleague.business.vo.Team;
import com.hedoleague.common.match.Match;
import com.hedoleague.common.enumeration.TeamEnum;
import com.hedoleague.common.match.MatchApiService;
import com.hedoleague.util.PlComparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 외부 API로 모든 경기 결과 받아와서 순위표 생성
 * TODO : DBTableServiceImpl.. DB 구축 후 Batch 로 경기결과 받아와서 업데이트..
 */
@Service
@RequiredArgsConstructor
public class ApiTableServiceImpl implements TableService {

  private final MatchApiService matchApiService;

  public List<TableTeam> getTableTeamList() {
    List<TableTeam> result = new ArrayList<>();

    for (TeamEnum team : TeamEnum.values()) {
      List<Match> matches = matchApiService.getMatchesByTeam(team.getId()).getMatches()
          .stream().filter(match -> isHedoLeagueMatch(match.getHomeTeam(), match.getAwayTeam()))
          .collect(Collectors.toList());

      result.add(calculateTableTeam(team, matches));
    }

    result.sort(new PlComparator());

    setPosition(result);

    return result;
  }

  private void setPosition(List<TableTeam> result) {
    for (int i = 1; i <= result.size(); i++) {
      result.get(i - 1).setPosition(i);
    }
  }

  private TableTeam calculateTableTeam(TeamEnum teamEnum, List<Match> matches) {
    Team team = new Team();
    int won = 0;
    int drawn = 0;
    int lost = 0;

    int goalsFor = 0;
    int goalsAgainst = 0;

    for (Match match : matches) {
      Score score = match.getScore();

      if (isHomeTeam(match, teamEnum.getId())) {
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
          if (isHomeTeam(match, teamEnum.getId())) {
            won++;
          } else {
            lost++;
          }
          break;
        case AWAY_TEAM:
          if (isHomeTeam(match, teamEnum.getId())) {
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

  private boolean isHedoLeagueMatch(Team homeTeam, Team awayTeam) {
    return Arrays.stream(TeamEnum.values()).anyMatch(teamEnum -> teamEnum.getId() == Integer.parseInt(homeTeam.getId())) &&
        Arrays.stream(TeamEnum.values()).anyMatch(teamEnum -> teamEnum.getId() == Integer.parseInt(awayTeam.getId()));
  }

  private boolean isHomeTeam(Match match, int teamId) {
    return Integer.parseInt(match.getHomeTeam().getId()) == teamId;
  }

}
