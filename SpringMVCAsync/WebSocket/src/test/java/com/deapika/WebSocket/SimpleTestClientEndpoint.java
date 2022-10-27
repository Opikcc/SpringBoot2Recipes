package com.deapika.WebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class SimpleTestClientEndpoint {
  
  private List<String> received = new ArrayList<>();
  private Session session;
  private CloseReason closeReason;
  private boolean closed = false;
  
  @OnOpen
  public void onOpen(Session session) {
    this.session = session;
  }
  
  @OnClose
  public void onClose(Session session, CloseReason reason) {
    this.closeReason = reason;
    this.closed = true;
  }
  
  @OnMessage
  public void onMessage(String message) {
    this.received.add(message);
  }
  
  public void sendTextAndWait(String text, long timeout) throws IOException, InterruptedException {
    var current = received.size();
    session.getBasicRemote().sendText(text);
    wait(() -> received.size() == current, timeout);
  }
  
  public void closeAndWait(long timeout) throws IOException, InterruptedException {
    if (session != null && !closed) {
      session.close();
    }
    
    wait(() -> closeReason == null, timeout);
  }
  
  private void wait(Supplier<Boolean> condition, long timeout) throws InterruptedException {
    var waited = 0;
    while (condition.get() && waited < timeout) {
      Thread.sleep(1);
      waited += 1;
    }
  }
  
  public CloseReason getCloseReason() {
    return closeReason;
  }
  
  public List<String> getReceived() {
    return this.received;
  }
  
  public boolean isClosed() {
    return closed;
  }
}
