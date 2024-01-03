package com.hedoleague.application.table;

import com.hedoleague.domain.table.vo.TableTeam;
import java.util.List;

public interface TableService {

  List<TableTeam> getTableTeamList();
}
