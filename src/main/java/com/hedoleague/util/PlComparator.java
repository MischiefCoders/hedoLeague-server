package com.hedoleague.util;

import com.hedoleague.business.table.vo.TableTeam;
import java.util.Comparator;

/**
 * PL 순위 계산
 * 승점 > 골 득실차 > 골 다득점 순
 */
public class PlComparator implements Comparator<TableTeam> {

  @Override
  public int compare(TableTeam t1, TableTeam t2) {

    if (t1.getPoints() < t2.getPoints()) return 1;
    else if (t1.getPoints().equals(t2.getPoints())) {
      if (t1.getGoalDifference() < t2.getGoalDifference()) return 1;
      else if (t1.getGoalDifference().equals(t2.getGoalsAgainst())) {
        if (t1.getGoalsFor() < t2.getGoalsFor()) return 1;
        else if (t1.getGoalsFor().equals(t2.getGoalsFor())) return 0;
        else return -1;
      } else {
        return -1;
      }
    }
    return -1;
  }

}
