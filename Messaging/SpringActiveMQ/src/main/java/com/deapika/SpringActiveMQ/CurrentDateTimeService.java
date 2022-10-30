package com.deapika.SpringActiveMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CurrentDateTimeService {
  
  /*
  @JmsListener(destination = "time-queue")
  public void handle(Message msg) throws JMSException {
    Assert.state(msg instanceof TextMessage, "Can only handle TextMessage.");
    System.out.println("[RECEIVED] - " + ((TextMessage) msg).getText());
  }
  */
  
  @JmsListener(destination = "time-queue")
  public void handle(String msg) {
    System.out.println("[RECEIVED] - " + msg);
  }
}
