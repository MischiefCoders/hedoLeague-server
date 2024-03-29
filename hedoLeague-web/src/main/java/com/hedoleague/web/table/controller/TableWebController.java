package com.hedoleague.web.table.controller;

import com.hedoleague.application.table.TableService;
import com.hedoleague.domain.table.vo.TableTeam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tables")
public class TableWebController {

  private final TableService tableService;

  @GetMapping("")
  public String list(Model model) {
    List<TableTeam> teams = tableService.getTableTeamList();
    model.addAttribute("teams", teams);

    return "list";
  }

}
