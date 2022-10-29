package com.deapika.SpringDataAccess.jdbc;

import java.util.Objects;

public class Customer {

  private final long id;
  private final String name;
  private final String email;

  public Customer(long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (obj == null) {
      return false;
    }
    
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    final Customer other = (Customer) obj;
    
    if (this.id != other.id) {
      return false;
    }
    
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    
    return Objects.equals(this.email, other.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email);
  }

  @Override
  public String toString() {
    return "Customer{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
  }
  
}
