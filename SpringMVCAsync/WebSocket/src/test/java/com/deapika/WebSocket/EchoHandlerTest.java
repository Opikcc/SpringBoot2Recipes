package com.deapika.WebSocket;

import org.junit.Test;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class EchoHandlerTest {

  private final EchoHandler handler = new EchoHandler();
  
  @Test
  public void shouldEchoMessage() throws Exception {
    var mockSession = mock(WebSocketSession.class);
    var msg = new TextMessage("Hello World!");
    handler.handleTextMessage(mockSession, msg);
    
    verify(mockSession, times(1))
            .sendMessage(eq(new TextMessage("RECEIVED: Hello World!")));
  }
}
