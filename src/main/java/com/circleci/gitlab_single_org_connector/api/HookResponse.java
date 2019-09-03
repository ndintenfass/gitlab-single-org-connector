package com.circleci.gitlab_single_org_connector.api;

import com.circleci.gitlab_single_org_connector.resources.HookResource;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/** For responses to the /hook endpoint as implemented by {@link HookResource}. */
public class HookResponse {
  /** Any errors produced during hook processing will end up here. */
  private final List<String> errors;

  public HookResponse() {
    this(new ArrayList<String>());
  }

  public HookResponse(List<String> hookErrors) {
    errors = hookErrors;
  }

  @JsonProperty
  public List<String> getErrors() {
    return errors;
  }
}
