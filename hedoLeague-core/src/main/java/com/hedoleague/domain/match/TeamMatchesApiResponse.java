package com.hedoleague.domain.match;

import com.hedoleague.domain.match.entity.Match;
import java.util.List;
import lombok.Data;

@Data
public class TeamMatchesApiResponse {

  List<Match> matches;

}
