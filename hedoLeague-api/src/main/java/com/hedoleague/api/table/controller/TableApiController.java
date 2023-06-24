package com.hedoleague.api.table.controller;

import com.hedoleague.api.table.entity.TableListResponse;
import com.hedoleague.business.table.service.TableService;
import com.hedoleague.business.table.vo.TableTeam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
@Slf4j
public class TableApiController {

  private final TableService tableService;

  @GetMapping("")
  public ResponseEntity<TableListResponse> tableList() {

    List<TableTeam> list = tableService.getTableTeamList();

    if (list.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    TableListResponse response = TableListResponse.builder()
        .list(list)
        .build();
    return ResponseEntity.ok(response);
  }

}
