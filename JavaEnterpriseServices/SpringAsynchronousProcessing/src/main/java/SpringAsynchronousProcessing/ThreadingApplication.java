package SpringAsynchronousProcessing;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ThreadingApplication implements SchedulingConfigurer {
  
  @Autowired
  private HelloWorld helloWorld;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ThreadingApplication.class, args);
    
    System.out.println("Press [ENTER] to quit:");
    System.in.read();
	}
  
  @Bean
  public ApplicationRunner startupRunner(HelloWorld hello) {
    return (args) -> {
      hello.printMessageAsync();
      hello.printMessage();
    };
  }
  
  @Bean
  public TaskExecutor customTaskExecutor(TaskExecutorBuilder builder) {
    return builder.corePoolSize(4)
            .maxPoolSize(16)
            .queueCapacity(125)
            .threadNamePrefix("sbr-exec-").build();
  }

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.addFixedRateTask(() -> {
      try {
        helloWorld.printMessage();
      }
      catch (InterruptedException ex) {
        Logger.getLogger(ThreadingApplication.class.getName()).log(Level.SEVERE, null, ex);
      }
    }, 2000);
  }
}
