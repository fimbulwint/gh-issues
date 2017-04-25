package com.fimbul;

public class Credentials {

  private final String username;
  private final String pass;

  public Credentials(String username, String pass) {
    this.username = username;
    this.pass = pass;
  }

  public String getUsername() {
    return username;
  }

  public String getPass() {
    return pass;
  }
}
