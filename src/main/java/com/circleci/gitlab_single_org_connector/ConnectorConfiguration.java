package com.circleci.gitlab_single_org_connector;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.Range;

public class ConnectorConfiguration extends Configuration {
  private Statsd statsd;

  public ConnectorConfiguration() {}

  Statsd getStatsd() {
    return statsd;
  }

  void setStatsd(Statsd s) {
    statsd = s;
  }

  class Statsd {
    private String host;

    @Range(min = 0, max = 65535)
    private int port = 8125;

    @Range(min = 1)
    private int refreshPeriodSeconds = 10;

    public Statsd() {}

    @JsonProperty
    String getHost() {
      return host;
    }

    @JsonProperty
    void setHost(String hostname) {
      host = hostname;
    }

    @JsonProperty
    int getPort() {
      return port;
    }

    @JsonProperty
    void setPort(int p) {
      port = p;
    }

    @JsonProperty
    int getRefreshPeriodSeconds() {
      return refreshPeriodSeconds;
    }

    @JsonProperty
    void setRefreshPeriodSeconds(int seconds) {
      refreshPeriodSeconds = seconds;
    }
  }
}
