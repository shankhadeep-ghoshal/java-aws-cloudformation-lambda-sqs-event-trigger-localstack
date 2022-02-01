package com.shankhadeepghoshal.awsdemo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import lombok.SneakyThrows;

/**
 * @author <a href="mailto:shankhadeepghoshal1996@gmail.com">Shankhadeep Ghoshal</a>
 * @since 1.0
 */
public class LambdaHandler implements RequestHandler<SQSEvent, String> {
  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public String handleRequest(SQSEvent input, Context context) {
    final var logger = context.getLogger();
    handleLambdaWork(input, logger);
    return null;
  }

  private void handleLambdaWork(final SQSEvent input, final LambdaLogger logger) {
    input.getRecords().stream()
        .map(SQSMessage::getBody)
        .filter(Objects::nonNull)
        .map(this::deserializeJsonIntoObj)
        .forEach(obj -> logger.log(obj.toString()));
  }

  @SneakyThrows
  private DummyPojo deserializeJsonIntoObj(String jsonString) {
    return mapper.readValue(jsonString, new TypeReference<>() {});
  }
}
