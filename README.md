wikiaboyko
==========

wikia tests from boyko1

1. properties file is located in "/resources" folder of the project, and names as "prop.properties"
2. change the "chrome_path" parameter in prop.properties file to the correct one to be able to run tests under chrome browser.
3. leave "user" and "pass" parameters as is if you dont know any other registered user 
4. if required change youtube link and description
5. if required change parameter "browser" to preferable one: "firefox" or "chrome"

to run tests under command line- go to project directory and type 
1. "mvn clean install" 
or 
2a. "mvn clean compile" 
2b. "mvn test"
