package com.deapika.SpringDataAccess.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("JdbcCustomerRepository")
public class JdbcCustomerRepository implements CustomerRepository {
  
  private static final String ALL_QUERY = "SELECT id, name, email FROM customer";
  private static final String BY_ID_QUERY = "SELECT id, name, email FROM customer WHERE id=?";
  private static final String INSERT_QUERY = "INSERT INTO customer (name, email) VALUES (?, ?)";
  private final JdbcTemplate jdbc;

  public JdbcCustomerRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }
  
  @Override
  public List<Customer> findAll() {
    return jdbc.query(ALL_QUERY, (rs, rowNum) -> toCustomer(rs));
  }

  @Override
  public Customer findById(long id) {
    return jdbc.queryForObject(BY_ID_QUERY, (rs, rowNum) -> toCustomer(rs));
  }

  @Override
  public Customer save(Customer customer) {
    var keyHolder = new GeneratedKeyHolder();
    jdbc.update(con -> {
      var ps = con.prepareStatement(INSERT_QUERY);
      ps.setString(1, customer.getName());
      ps.setString(2, customer.getEmail());
      return ps;
    }, keyHolder);
    
    return new Customer(keyHolder.getKey().longValue(), customer.getName(), customer.getEmail());
  }
  
  private Customer toCustomer(ResultSet rs) throws SQLException {
    var id = rs.getLong(1);
    var name = rs.getString(2);
    var email = rs.getString(3);
    return new Customer(id, name, email);
  }
}
