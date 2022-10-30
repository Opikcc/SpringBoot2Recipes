package SpringMailSender;

import java.io.IOException;
import java.util.Collections;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@SpringBootApplication
public class MailSenderApplication {

  public static void main(String[] args) throws IOException {
    SpringApplication.run(MailSenderApplication.class, args);

    System.out.println("Press [ENTER] to quit:");
    System.in.read();
  }

  /*
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
   */
  // Using Thymeleaf
  public ApplicationRunner startupMailSender(
          JavaMailSender mailSender,
          SpringTemplateEngine templateEngine) {
    return (args) -> {
      mailSender.send((msg) -> {
        var helper = new MimeMessageHelper(msg);
        helper.setTo("dederiswan101@gmail.com");
        helper.setFrom("taufikbdg@gmail.com");
        helper.setSubject("Status message");
        var context = new Context(
                LocaleContextHolder.getLocale(),
                Collections.singletonMap("msg", "All is well!"));
        var body = templateEngine.process("email.html", context);
        helper.setText(body, true);
      });
    };
  }

}
