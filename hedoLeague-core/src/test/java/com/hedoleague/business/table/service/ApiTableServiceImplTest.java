package com.hedoleague.business.table.service;

import com.hedoleague.business.table.vo.TableTeam;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiTableServiceImplTest {

  @Autowired ApiTableServiceImpl apiTableService;

  @Test
  void getTableTeamList() {
    List<TableTeam> result = apiTableService.getTableTeamList();

    for (TableTeam tableTeam : result) {
      System.out.println(tableTeam);
    }
  }

}