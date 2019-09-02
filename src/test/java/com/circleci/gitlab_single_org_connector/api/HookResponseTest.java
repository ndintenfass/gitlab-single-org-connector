package com.circleci.gitlab_single_org_connector.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Test the representation of responses to GitLab hooks. */
class HookResponseTest {
  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  @Test
  void emptyHookResponseSerializes() throws Exception {
    HookResponse hr = new HookResponse();
    assertEquals("{\"errors\":[]}", MAPPER.writeValueAsString(hr));
  }

  @Test
  void hookResponseWithErrorsSerializes() throws Exception {
    List<String> errors = Arrays.asList("foo", "bar");
    assertEquals(
        "{\"errors\":[\"foo\",\"bar\"]}", MAPPER.writeValueAsString(new HookResponse(errors)));
  }
}
