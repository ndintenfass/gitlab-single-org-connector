package com.circleci.gitlab_single_org_connector.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.circleci.gitlab_single_org_connector.api.HookResponse;
import com.circleci.gitlab_single_org_connector.api.PushHook;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class HookResourceTest {
  @Test
  public void weCanProcessTheHookFromGitlabDocs() throws Exception {
    // Load a hook from fixtures
    String hookJson = FixtureHelpers.fixture("gitlab-push-hook-from-docs.json");
    assertNotNull(hookJson);
    PushHook hook = Jackson.newObjectMapper().readValue(hookJson, PushHook.class);

    // Now process that hook
    HookResource hr = new HookResource(null);
    HookResponse response = hr.processHook(hook, null);
    assertEquals(new ArrayList<String>(), response.getErrors());
  }

  @Test
  public void weCanProcessTheHookFromGitlabDocsWhenItSuppliesATokenAndWeAgree() throws Exception {
    // Load a hook from fixtures
    String hookJson = FixtureHelpers.fixture("gitlab-push-hook-from-docs.json");
    assertNotNull(hookJson);
    PushHook hook = Jackson.newObjectMapper().readValue(hookJson, PushHook.class);

    // Now process that hook
    HookResource hr = new HookResource("super-secret");
    HookResponse response = hr.processHook(hook, "super-secret");
    assertEquals(new ArrayList<String>(), response.getErrors());
  }

  @Test
  public void weCanProcessTheHookFromGitlabDocsWhenItSuppliesATokenAndWeDoNotCare()
      throws Exception {
    // Load a hook from fixtures
    String hookJson = FixtureHelpers.fixture("gitlab-push-hook-from-docs.json");
    assertNotNull(hookJson);
    PushHook hook = Jackson.newObjectMapper().readValue(hookJson, PushHook.class);

    // Now process that hook
    HookResource hr = new HookResource(null);
    HookResponse response = hr.processHook(hook, "token-for-us-to-ignore");
    assertEquals(new ArrayList<String>(), response.getErrors());
  }

  @Test
  public void weThrowA403IfTheGitlabTokenIsNotSupplied() throws Exception {
    HookResource hr = new HookResource("super-secret");
    org.junit.jupiter.api.Assertions.assertThrows(
        WebApplicationException.class,
        new Executable() {
          @Override
          public void execute() throws Throwable {
            hr.processHook(new PushHook(), null);
          }
        });
  }

  @Test
  public void weThrowA403IfTheGitlabTokenDoesNotMatch() throws Exception {
    HookResource hr = new HookResource("super-secret");
    org.junit.jupiter.api.Assertions.assertThrows(
        WebApplicationException.class,
        new Executable() {
          @Override
          public void execute() throws Throwable {
            hr.processHook(new PushHook(), "wrong-token");
          }
        });
  }
}
