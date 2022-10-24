package com.deapika.AsyncRequestHandler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldCompleteableFutureController {
  
  private final TaskExecutor taskExecutor;
  
  public HelloWorldCompleteableFutureController(TaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }
 
  @GetMapping("/future")
  public CompletableFuture<String> hello() {
    
    return CompletableFuture.supplyAsync(() -> {
      randomDelay();
      return "Hello World, From Spring Boot 2 MVC Async Completable Future!!!";
    }, taskExecutor);
  }
  
  private void randomDelay() {
    try {
      Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
