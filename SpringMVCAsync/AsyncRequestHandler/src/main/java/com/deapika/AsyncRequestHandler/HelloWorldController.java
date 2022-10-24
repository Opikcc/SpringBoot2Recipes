package com.deapika.AsyncRequestHandler;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
 
  @GetMapping
  public Callable<String> hello() {
    return () -> {
      Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
      return "Hello World, From Spring Boot 2 MVC Async Request Handler!!!";
    };
  }
}
