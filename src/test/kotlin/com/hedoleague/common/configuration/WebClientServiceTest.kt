package com.hedoleague.common.configuration

import com.hedoleague.common.configuration.api.WebClientService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebClientServiceTest(@Autowired val webClientService: WebClientService) {

  @BeforeAll
  fun setUp() {
    println(">> start test...")
  }

  @Test
  fun getThenResponse() {

  }

}