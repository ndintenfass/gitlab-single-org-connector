package com.circleci.gitlab_single_org_connector;

import com.circleci.gitlab_single_org_connector.health.CircleCiApiHealthCheck;
import com.circleci.gitlab_single_org_connector.resources.HookResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Main entry point for the GitLab Single Org Connector. See Dropwizard documentation for details of
 * how any of this works.
 */
public class ConnectorApplication extends Application<ConnectorConfiguration> {
  /**
   * Main entry point for the GitLab Single Org Connector. See Dropwizard documentation for details
   * of how any of this works.
   */
  public void run(ConnectorConfiguration config, Environment environment) throws Exception {
    environment.healthChecks().register("CircleCI API", new CircleCiApiHealthCheck());
    environment.jersey().register(new HookResource());
  }

  public static void main(String[] args) throws Exception {
    new ConnectorApplication().run(args);
  }
}
