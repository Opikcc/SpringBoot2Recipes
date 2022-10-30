package SpringJMXBean;

import java.io.IOException;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JMXApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JMXApplication.class, args);
    
    System.out.println("Press [ENTER] to quit:");
    System.in.read();
	}
  
  @Bean
  public ApplicationRunner startupRunner(HelloWorld hello) {
    return (args) -> {
      hello.printMessageAsync();
      hello.printMessage();
      hello.printMessageScheduled();
    };
  }
}
