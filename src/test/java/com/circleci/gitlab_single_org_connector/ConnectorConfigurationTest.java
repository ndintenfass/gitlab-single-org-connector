package com.circleci.gitlab_single_org_connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;
import org.junit.jupiter.api.Test;

public class ConnectorConfigurationTest {
  @Test
  public void weDoNotNPEWhenSomePartsOfTheConfigAreMissing() {
    ConnectorConfiguration cfg = new ConnectorConfiguration();
    assertNotNull(cfg.getGitlab());
    assertNotNull(cfg.getStatsd());
  }

  @Test
  public void weCanSetEverythingInAConfigFileAndItStillWorks() throws Exception {
    ObjectMapper mapper = Jackson.newObjectMapper(new YAMLFactory());
    ConnectorConfiguration cfg =
        mapper.readValue(
            FixtureHelpers.fixture("complete-config.yml"), ConnectorConfiguration.class);
    assertEquals("super-secret", cfg.getGitlab().getSharedSecretForHooks());
  }
}
