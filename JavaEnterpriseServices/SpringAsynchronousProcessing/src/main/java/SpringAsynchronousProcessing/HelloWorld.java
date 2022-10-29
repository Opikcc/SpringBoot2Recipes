package SpringAsynchronousProcessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {
 
  private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
  
  @Async
  public void printMessage() throws InterruptedException {
    Thread.sleep(500);
    logger.info("Hello World, From Spring Boot 2 Asynchronous Processing!!!");
  }
  
  @Async
  public void printMessageAsync() throws InterruptedException {
    Thread.sleep(1500);
    logger.info("Asynchronous Processing!!!");
  }
}
