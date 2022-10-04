package com.hedoleague.business.application.external.properties

import lombok.Getter
import lombok.Setter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated

@Validated
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "team-id")
class TeamIdProperties {

  lateinit var arsenal: String
  lateinit var chelsea: String
  lateinit var liverpool: String
  lateinit var mancity: String
  lateinit var manunited: String
  lateinit var tottenham: String
}