package com.bpn.SpringBootThymeleaf.dao;

import com.bpn.SpringBootThymeleaf.model.Customer;
import com.bpn.SpringBootThymeleaf.repository.CustomerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDao implements CustomerRepository {

  private static final String ALL_QUERY =
          "SELECT id, name, email FROM customer";
  private static final String BY_ID_QUERY =
          "SELECT id, name, email FROM customer WHERE id=?";
  private static final String INSERT_QUERY =
          "INSERT INTO customer (name, email) VALUES (?,?)";
  private final JdbcTemplate jdbc;

  CustomerDao(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public List<Customer> findAll() {
    return jdbc.query(ALL_QUERY, (rs, rowNum) -> toCustomer(rs));
  }

  @Override
  public Customer findById(long id) {
    return jdbc.queryForObject(BY_ID_QUERY, (rs, rowNum) -> toCustomer(rs), id);
  }

  @Override
  public Customer save(Customer customer) {
    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    jdbc.update(con -> {
      PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
      ps.setString(1, customer.getName());
      ps.setString(2, customer.getEmail());
      return ps;
    }, keyHolder);

    return new Customer(keyHolder.getKey().longValue(),
            customer.getName(), customer.getEmail());
  }

  private Customer toCustomer(ResultSet rs) throws SQLException {
    long id = rs.getLong(1);
    String name = rs.getString(2);
    String email = rs.getString(3);

    return new Customer(id, name, email);
  }
}
