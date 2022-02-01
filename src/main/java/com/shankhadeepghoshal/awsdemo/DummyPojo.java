package com.shankhadeepghoshal.awsdemo;

import java.util.UUID;
import lombok.Value;

/**
 * @author <a href="mailto:shankhadeepghoshal1996@gmail.com">Shankhadeep Ghoshal</a>
 * @since 1.0
 */
@Value
public class DummyPojo {
  String data = UUID.randomUUID().toString();
}
