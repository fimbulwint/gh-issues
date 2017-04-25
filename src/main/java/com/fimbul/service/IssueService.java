package com.fimbul.service;

import com.fimbul.Credentials;
import com.fimbul.model.Issue;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import spark.utils.StringUtils;

public class IssueService {

  private static final String ISSUE_CREATION_URL = "https://api.github.com/repos/{repoOwner}/{repo}/issues";

  private final Credentials ghCreds;
  private final String repo;

  public IssueService() {
    String username = System.getProperty("username");
    String pass = System.getProperty("pass");
    repo = System.getProperty("repo");

    if (StringUtils.isEmpty(username)|| StringUtils.isEmpty(pass) || StringUtils.isEmpty(repo)) {
      System.err.println("Credentials and repo are required to be defined in the env. Shutting down");
      System.exit(-1);
    }

    ghCreds = new Credentials(username, pass);
  }

  public void createIssue(Issue issue) throws UnirestException {
    Unirest.post(ISSUE_CREATION_URL)
        .header("Accept", "application/vnd.github.v3+json")
        .basicAuth(ghCreds.getUsername(), ghCreds.getPass())
        .routeParam("repoOwner", ghCreds.getUsername())
        .routeParam("repo", repo)
        .body("{\"title\": \""
            + issue.getTitle()
            + "\", \"body\": \""
            + issue.getBody()
            + "\"}")
        .asJson();
  }
}
