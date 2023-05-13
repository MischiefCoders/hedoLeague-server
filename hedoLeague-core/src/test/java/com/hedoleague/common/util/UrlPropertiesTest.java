package com.hedoleague.common.util;


import static org.assertj.core.api.Assertions.assertThat;

import com.hedoleague.common.util.properties.UrlProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlPropertiesTest {

  @Autowired
  private UrlProperties urlProperties;

  @Test
  void propertiesTest() {
    String teamsMatchesUrl = urlProperties.getDomain() + "/" + urlProperties.getApiVersion() + "/teams/{id}/matches";
    assertThat(urlProperties.getTeamsMatchesUrl()).isEqualTo(teamsMatchesUrl);
  }

}