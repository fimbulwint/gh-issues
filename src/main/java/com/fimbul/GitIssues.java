package com.fimbul;

import com.fimbul.model.Issue;
import com.fimbul.service.IssueService;
import com.mashape.unirest.http.exceptions.UnirestException;
import spark.utils.StringUtils;

import static spark.Spark.*;

public class GitIssues {

  private IssueService issueService = new IssueService();

  public static void main( String[] args ) {
    new GitIssues();
  }

  public GitIssues() {
    port(8080);

    staticFiles.location("/static");

    get("/createIssue",
        (req, res) -> {
          String title = req.queryParams("title");
          String body = req.queryParams("body");

          if (StringUtils.isEmpty(title) || StringUtils.isEmpty(body)) {
            throw new IllegalArgumentException("Title and body of new issue cannot be empty");
          }

          issueService.createIssue(new Issue(title, body));
          return "Issue created successfully!";
        });

    exception(IllegalArgumentException.class, (e, req, rep) -> {
      rep.body("Error: " + e.getMessage());
      rep.status(400);
    });

    exception(UnirestException.class, (e, req, rep) -> {
      rep.body("Error: Call to Github failed");
      rep.status(503);
    });
  }
}
