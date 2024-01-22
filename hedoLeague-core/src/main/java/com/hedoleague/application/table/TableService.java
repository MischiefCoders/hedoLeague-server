package com.hedoleague.application.table;

import com.hedoleague.common.enumeration.TeamEnum;
import com.hedoleague.common.util.PlComparator;
import com.hedoleague.common.vo.Score;
import com.hedoleague.common.vo.Team;
import com.hedoleague.domain.match.MatchService;
import com.hedoleague.domain.match.entity.Match;
import com.hedoleague.domain.table.vo.TableTeam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TableService {

  private final MatchService matchService;

  public List<TableTeam> getTableTeamList() {
    List<TableTeam> tableTeams = new ArrayList<>();

    for (TeamEnum team : TeamEnum.values()) {
      List<Match> matches = matchService.getMatchesByTeam(team.getId(), "2023")
          .stream()
          .filter(Match::isHedoLeagueMatch)
          .collect(Collectors.toList());

      tableTeams.add(makeTableTeam(team, matches));
    }

    tableTeams.sort(new PlComparator());

    setPosition(tableTeams);

    return tableTeams;
  }

  private void setPosition(List<TableTeam> result) {
    for (int i = 1; i <= result.size(); i++) {
      result.get(i - 1).setPosition(i);
    }
  }

  private TableTeam makeTableTeam(TeamEnum teamEnum, List<Match> matches) {

  }


}
