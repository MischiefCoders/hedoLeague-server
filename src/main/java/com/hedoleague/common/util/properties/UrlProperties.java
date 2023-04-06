package com.hedoleague.common.util.properties;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.util.StringUtils;

@Data
@Configuration
@ConfigurationProperties("football-data")
public class UrlProperties {

  private String domain;
  private String apiVersion;

  private URI teamsMatchesUrl;
  private URI teamsParticularUrl;
  private URI personMatchesUrl;
  private URI personParticularUrl;
  private URI matchParticularUrl;

  public URI getTeamsMatchesUrl(int id) {
    return URI.create(makeDecodedURL(this.getTeamsMatchesUrl().toString(), id));
  }

  public URI getTeamsParticularUrl(int id) {
    return URI.create(makeDecodedURL(this.getTeamsParticularUrl().toString(), id));
  }

  public URI getPersonMatchesUrl(int id) {
    return URI.create(makeDecodedURL(this.getPersonMatchesUrl().toString(), id));
  }

  public URI getPersonParticularUrl(int id) {
    return URI.create(makeDecodedURL(this.getPersonParticularUrl().toString(), id));
  }

  public URI getMatchParticularUrl(int id) {
    return URI.create(makeDecodedURL(this.getMatchParticularUrl().toString(), id));
  }

  private String makeDecodedURL(String url, int id) {
    return StringUtils.replace(URLDecoder.decode(url, Charset.defaultCharset()), "{id}", String.valueOf(id));
  }
  
}
