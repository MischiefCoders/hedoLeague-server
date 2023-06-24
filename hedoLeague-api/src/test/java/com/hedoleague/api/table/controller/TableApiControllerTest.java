package com.hedoleague.api.table.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hedoleague.api.configuration.RestDocsConfiguration;
import com.hedoleague.business.table.service.ApiTableServiceImpl;
import com.hedoleague.business.table.vo.TableTeam;
import com.hedoleague.business.vo.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
class TableApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ApiTableServiceImpl tableService;

  @Test
  @DisplayName("[api] 순위표 조회 API TEST")
  void tableList() throws Exception {
    TableTeam arsenal = TableTeam.builder()
        .position(1)
        .played(1)
        .points(1)
        .won(1)
        .drawn(1)
        .lost(1)
        .goalsFor(1)
        .goalsAgainst(1)
        .goalDifference(1)
        .team(new Team())
        .build();

    TableTeam manCity = TableTeam.builder()
        .position(1)
        .played(1)
        .points(1)
        .won(1)
        .drawn(1)
        .lost(1)
        .goalsFor(1)
        .goalsAgainst(1)
        .goalDifference(1)
        .team(new Team())
        .build();

    List<TableTeam> list = List.of(arsenal, manCity);

    when(tableService.getTableTeamList()).thenReturn(list);

    mockMvc.perform(get("/tables")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(document("list",
            // snippet
            responseHeaders(
                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type")
            ),
            // snippet
            responseFields(
                subsectionWithPath("list[]").description("댓글 리스트")
            ).andWithPrefix("list[].",
                fieldWithPath("position").type(JsonFieldType.NUMBER).description("순위"),
                fieldWithPath("team").type(JsonFieldType.OBJECT).description("팀 정보"),
                fieldWithPath("played").type(JsonFieldType.NUMBER).description("경기수"),
                fieldWithPath("points").type(JsonFieldType.NUMBER).description("승점"),
                fieldWithPath("won").type(JsonFieldType.NUMBER).description("승"),
                fieldWithPath("drawn").type(JsonFieldType.NUMBER).description("무"),
                fieldWithPath("lost").type(JsonFieldType.NUMBER).description("패"),
                fieldWithPath("goalsFor").type(JsonFieldType.NUMBER).description("득점"),
                fieldWithPath("goalsAgainst").type(JsonFieldType.NUMBER).description("실점"),
                fieldWithPath("goalDifference").type(JsonFieldType.NUMBER).description("득실차")
            )
        ));
  }
}