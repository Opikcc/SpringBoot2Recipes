package com.bpn.SpringBootThymeleaf.lib;

import java.util.*;

public class Book {

  private String isbn;
  private String title;
  private List<String> authors = new ArrayList<>();

  public Book() {}

  public Book(String isbn, String title, String... authors) {
    this.isbn = isbn;
    this.title = title;
    this.authors = Arrays.asList(authors);
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getAuthors() {
    return Collections.unmodifiableList(authors);
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(isbn, book.isbn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn);
  }

  @Override
  public String toString() {
    return String.format("Book [isbn=%s, title=%s, authors=%s]", this.isbn, this.title, this.authors);
  }
}
