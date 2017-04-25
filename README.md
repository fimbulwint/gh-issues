# gh-issues

 Run with an IDE or build from command line using: `mvn clean package`

 Backend runs on port `8080` and the the endpoint's path for issue creation is `/createIssue`

 Required parameters in the environment:
   * &lt;username&gt;: The owner of the repository
   * &lt;pass&gt;: Owner's password (app uses basic authentication)
   * &lt;repo&gt;: The repository where the issue will be created