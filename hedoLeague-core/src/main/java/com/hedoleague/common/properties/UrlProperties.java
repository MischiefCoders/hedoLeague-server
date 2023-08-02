package com.hedoleague.common.properties;

import java.net.URI;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Setter
@Getter
@Configuration
@ConfigurationProperties("football-data")
public class UrlProperties {

  private String domain;
  private String apiVersion;

  private String teamsMatchesUrl;
  private String teamsParticularUrl;
  private String personMatchesUrl;
  private String personParticularUrl;
  private String matchParticularUrl;

  public URI getTeamsMatchesUrl(int id) {
    return URI.create(StringUtils.replace(this.getTeamsMatchesUrl(), "{id}", String.valueOf(id)));
  }

  public URI getTeamsParticularUrl(int id) {
    return URI.create(StringUtils.replace(this.getTeamsParticularUrl(), "{id}", String.valueOf(id)));
  }

  public URI getPersonMatchesUrl(int id) {
    return URI.create(StringUtils.replace(this.getPersonMatchesUrl(), "{id}", String.valueOf(id)));
  }

  public URI getPersonParticularUrl(int id) {
    return URI.create(StringUtils.replace(this.getPersonParticularUrl(), "{id}", String.valueOf(id)));
  }

  public URI getMatchParticularUrl(int id) {
    return URI.create(StringUtils.replace(this.getMatchParticularUrl(), "{id}", String.valueOf(id)));
  }

}
