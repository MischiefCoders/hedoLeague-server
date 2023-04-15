package com.hedoleague.business.table.controller;

import com.hedoleague.business.table.service.ApiTableServiceImpl;
import com.hedoleague.business.table.vo.TableTeam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class TableController {

  private final ApiTableServiceImpl tableService;

  @GetMapping("")
  public String list(Model model) {
    List<TableTeam> teams = tableService.getTableTeamList();
    model.addAttribute("teams", teams);

    return "list";
  }

}
