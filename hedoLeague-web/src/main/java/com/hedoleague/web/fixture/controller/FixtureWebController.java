//package com.hedoleague.web.fixture.controller;
//
//import com.hedoleague.business.fixture.service.FixtureService;
//import com.hedoleague.domain.match.entity.Match;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/fixtures")
//public class FixtureWebController {
//
//  private final FixtureService fixtureService;
//
//  @GetMapping("")
//  public String fixtures() {
//    List<Match> hedoLeagueMatches = fixtureService.getHedoLeagueMatches();
//
//    return "fixtures";
//  }
//
//}
