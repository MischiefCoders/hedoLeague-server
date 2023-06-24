package com.hedoleague.api.table.entity;

import com.hedoleague.business.table.vo.TableTeam;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TableListResponse {

  private List<TableTeam> list;

}
