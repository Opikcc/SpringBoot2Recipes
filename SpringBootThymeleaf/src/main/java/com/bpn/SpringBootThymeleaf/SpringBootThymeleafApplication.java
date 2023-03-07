package com.bpn.SpringBootThymeleaf;

import com.bpn.SpringBootThymeleaf.lib.Book;
import com.bpn.SpringBootThymeleaf.lib.BookService;
import com.bpn.SpringBootThymeleaf.lib.CustomizedErrorAttributes;
import com.bpn.SpringBootThymeleaf.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class SpringBootThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymeleafApplication.class, args);
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

	/*
	@Component
	class TableLister implements ApplicationRunner {

		private final Logger logger = LoggerFactory.getLogger(getClass());
		private final DataSource dataSource;

		TableLister(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		@Override
		public void run(ApplicationArguments args) throws Exception {
			try (Connection con = dataSource.getConnection();
					 ResultSet rs = con.getMetaData().getTables(null, null, "%", null)) {
				while (rs.next()) {
					logger.info("{}", rs.getString(3));
				}
			}
		}
	}

	@Component
	class CustomerLister implements ApplicationRunner {

		private final Logger logger = LoggerFactory.getLogger(getClass());
		private final DataSource dataSource;

		CustomerLister(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		@Override
		public void run(ApplicationArguments args) throws Exception {
			String query = "SELECT id, name, email FROM customer";
			try (Connection con = dataSource.getConnection();
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					logger.info("Customer [id={}, name={}, email={}]", rs.getLong(1),
									rs.getString(2),
									rs.getString(3));
				}
			}
		}
	}

	@Component
	class CustomerListerJdbcTemplate implements ApplicationRunner {

		private final Logger logger = LoggerFactory.getLogger(getClass());

		private final JdbcTemplate jdbc;

		CustomerListerJdbcTemplate(JdbcTemplate jdbc) {
			this.jdbc = jdbc;
		}

		@Override
		public void run(ApplicationArguments args) {

			String query = "SELECT id, name, email FROM customer";
			jdbc.query(query, rs -> {
				logger.info("Customer [id={}, name={}, email={}]", rs.getLong(1),
								rs.getString(2),
								rs.getString(3));
			});
		}
	}
	*/

	@Component
	class CustomerListener implements ApplicationRunner {

		private final Logger logger = LoggerFactory.getLogger(getClass());

		@Autowired
		private CustomerRepository customers;

		CustomerListener(CustomerRepository customers) {
			this.customers = customers;
		}

		@Override
		public void run(ApplicationArguments args) {
			customers.findAll().forEach( customer -> logger.info("{}", customer));
		}

	}

}
