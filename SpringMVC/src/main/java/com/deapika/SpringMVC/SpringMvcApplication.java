package com.deapika.SpringMVC;

import com.deapika.SpringMVC.library.Book;
import com.deapika.SpringMVC.library.BookService;
import com.deapika.SpringMVC.library.CustomizedErrorAttributes;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class SpringMvcApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(SpringMvcApplication.class, args);
  }

  @Bean
  public ApplicationRunner booksInitializer(BookService bookService) {
    return args -> {
      bookService.create(new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"));
      bookService.create(new Book("9780451524935", "1984", "George Orwell"));
      bookService.create(new Book("9780618260300", "The Hobbit", "J.R.R. Tolkien"));
    };
  }

  @Bean
  public CustomizedErrorAttributes errorAttributes() {
    return new CustomizedErrorAttributes();
  }

  // Resolving locale by an HTTP Request Header
  /*
  @Bean
  public LocaleResolver localeResolver() {
    return new AcceptHeaderLocaleResolver();
  }
   */
  // Resolving locale by a Session Attribute
  /*
  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(new Locale("en"));
    return localeResolver;
  }
   */
  // Resolving locale by a Cookie
  /*
  @Bean
  public LocaleResolver localeResolver() {
    return new CookieLocaleResolver();
  }
   */
  // Resolving locale by a Cookie
  /*
  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
    cookieLocaleResolver.setCookieName("language");
    cookieLocaleResolver.setCookieMaxAge(3600);
    cookieLocaleResolver.setDefaultLocale(new Locale("en"));
    return new CookieLocaleResolver();
  }
   */
  // Resolving locale Using a Fixed Locale
  /*
  @Bean
  public LocaleResolver localeResolver() {
    FixedLocaleResolver cookieLocaleResolver = new FixedLocaleResolver();
    cookieLocaleResolver.setDefaultLocale(new Locale("en"));
    return cookieLocaleResolver;
  }
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    return new LocaleChangeInterceptor();
  }

  @Bean
  public LocaleResolver localeResolver() {
    return new CookieLocaleResolver();
  }

  @Bean
  public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
    var factory = new TomcatServletWebServerFactory();
    factory.addAdditionalTomcatConnectors(httpConnector());
    factory.addContextCustomizers(securityCustomizer());
    return factory;
  }

  // If Using BeanProcessor
  /*
  public BeanPostProcessor addHttpConnectorProcessor() {
    return new BeanPostProcessor() {
      @Override
      public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TomcatServletWebServerFactory) {
          var factory = (TomcatServletWebServerFactory) bean;
          factory.addAdditionalTomcatConnectors(httpConnector());
        }
        return bean;
      }
    };
  }
   */
  private Connector httpConnector() {
    var connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
    connector.setScheme("http");
    connector.setPort(8080);
    connector.setSecure(false);
    connector.setRedirectPort(8443);
    return connector;
  }

  private TomcatContextCustomizer securityCustomizer() {
    return context -> {
      var securityConstraint = new SecurityConstraint();
      securityConstraint.setUserConstraint("CONFIDENTIAL");
      var collection = new SecurityCollection();
      collection.addPattern("/*");
      securityConstraint.addCollection(collection);
      context.addConstraint(securityConstraint);
    };
  }
}
