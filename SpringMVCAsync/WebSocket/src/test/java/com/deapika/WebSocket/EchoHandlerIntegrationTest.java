package com.deapika.WebSocket;

import java.net.URI;
import javax.websocket.ContainerProvider;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EchoHandlerIntegrationTest {
  
  @LocalServerPort
  private int port;
  
  @Test
  public void sendAndReceiveMessage() throws Exception {
    var container = ContainerProvider.getWebSocketContainer();
    var uri = URI.create("ws://localhost:" + port + "/echo");
    var testClient = new SimpleTestClientEndpoint();
    container.connectToServer(testClient, uri);
    
    testClient.sendTextAndWait("Hello World!", 200);
    testClient.closeAndWait(2);
    
    assertThat(testClient.getReceived()).containsExactly("CONNECTION ESTABLISHED", "RECEIVED: Hello World!");
  }
}