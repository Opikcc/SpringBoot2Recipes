package SpringJMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ManagedResource
public class HelloWorld {
 
  private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
  
  @Async
  @ManagedOperation
  public void printMessage() throws InterruptedException {
    Thread.sleep(50000);
    logger.info("Hello World, From Spring Boot 2 Asynchronous Processing!!!");
  }
  
  @Async
  @ManagedOperation
  public void printMessageAsync() throws InterruptedException {
    Thread.sleep(1500);
    logger.info("Asynchronous Processing!!!");
  }
  
  @Scheduled(fixedRate = 4000)
  @ManagedOperation
  public void printMessageScheduled() {
    logger.info("Hello World, From Spring Boot 2 Scheduled!!!");
  }
}
