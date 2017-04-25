package com.fimbul.model;

public class Issue {

  private String title;
  private String body;

  public Issue(String title, String body) {
    this.title = title;
    this.body = body;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }
}
