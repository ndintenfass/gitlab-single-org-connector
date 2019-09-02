package com.circleci.gitlab_single_org_connector.resources;

import com.circleci.gitlab_single_org_connector.api.HookResponse;
import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hook")
@Produces(MediaType.APPLICATION_JSON)
public class HookResource {
  private static final Logger LOGGER = LoggerFactory.getLogger(HookResource.class);

  @POST
  @Timed
  public HookResponse processHook(String jsonBody) {
    LOGGER.info("Received a hook: {}", jsonBody);
    return new HookResponse();
  }
}
