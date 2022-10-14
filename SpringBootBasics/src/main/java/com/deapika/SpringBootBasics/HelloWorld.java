package com.deapika.SpringBootBasics;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {
  
  @PostConstruct
  public void sayHello() {
    System.out.println("Hello World, from Spring Boot 2 Basics!!!");
  }
}
