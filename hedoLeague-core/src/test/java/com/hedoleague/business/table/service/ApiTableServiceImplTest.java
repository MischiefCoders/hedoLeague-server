package com.hedoleague.business.table.service;

import com.hedoleague.application.table.ApiTableServiceImpl;
import com.hedoleague.domain.table.vo.TableTeam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ApiTableServiceImplTest {

  @Autowired
  ApiTableServiceImpl apiTableService;

  @Test
  void getTableTeamList() {
    List<TableTeam> result = apiTableService.getTableTeamList();

    for (TableTeam tableTeam : result) {
      log.info("{}", tableTeam);
    }
  }

}