package com.hedoleague.domain.match;

import com.hedoleague.domain.match.entity.Match;
import java.util.List;

// TODO : DBMatchService.. DB 구축 후 Batch 로 경기결과 받아와서 업데이트..
public interface MatchService {

  List<Match> getMatchesByTeam(int teamId, String season);

}
