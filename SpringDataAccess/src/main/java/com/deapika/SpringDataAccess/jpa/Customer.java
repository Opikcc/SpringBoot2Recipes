package com.deapika.SpringDataAccess.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @Column(nullable = false)
  private String name;
  
  @Column(nullable = false)
  private String email;

  @Override
  public String toString() {
    return "Customer{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
  }
}
