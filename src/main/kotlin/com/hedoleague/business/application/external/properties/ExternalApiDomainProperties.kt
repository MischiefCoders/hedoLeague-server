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
@ConfigurationProperties(prefix = "football-data")
class ExternalApiDomainProperties {

 lateinit var domain: String
 lateinit var teamsMatches: String

}