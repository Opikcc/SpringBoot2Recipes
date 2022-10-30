package SpringMailSender;

import java.io.IOException;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootApplication
public class MailSenderApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MailSenderApplication.class, args);
    
    System.out.println("Press [ENTER] to quit:");
    System.in.read();
	}
  
  @Bean
  public ApplicationRunner startupMailSender(JavaMailSender mailSender) {
    return (args) -> {
      mailSender.send((msg) -> {
        var helper = new MimeMessageHelper(msg);
        helper.setTo("dederiswan101@gmail.com");
        helper.setFrom("taufikbdg@gmail.com");
        helper.setSubject("Status Message");
        helper.setText("All is well.");
      });
    };
  }
}
