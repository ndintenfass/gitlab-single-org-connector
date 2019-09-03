package com.circleci.gitlab_single_org_connector.health;

import static org.junit.Assert.assertEquals;

import com.codahale.metrics.health.HealthCheck;
import org.junit.jupiter.api.Test;

public class CircleCiApiHealthCheckTest {
  @Test
  public void healthCheckPassesWhenWeHaveAPIAccess() {
    CircleCiApiHealthCheck healthCheck = new CircleCiApiHealthCheck();
    assertEquals(HealthCheck.Result.healthy(), healthCheck.check());
  }

  @Test
  public void healthCheckPassesWhenWeHaveNoAPIAccess() {
    // TODO: Ensure that the health check fails when we have no API access
  }
}
