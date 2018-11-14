# git-test-serenity
sample git api tests

AUT: redmart.com
Language: Java
Framework: Serenity BDD open source Library with page objects
API Tests: using Rest assured Library
UI tests: Selenium 3.X
Build Tool: Maven
Curret browser support: Chrome

In order to execute the project:

Open command prompt
Go to where POM.xml is located
Then type "mvn clean verify"

Notes: 
1) For First time run, Maven will download all the dependencies, Go and Grab some coffee..
2) Once the download completes, your execution will start.

Test results:
Detailed and fluid test results are found at: target/site/serenity/index.html
Excel format results: target/site/serenity/results.csv
XML format results for CI parsing: target/surefire/*.*xml



//For Better test management we can create profile for regression, smoke or release specific tests 
Eg:
REGRESSION SUITE: "mvn clean verify -P regression -Dwebdriver.diver=chrome"
SMOKE TEST SUITE: "mvn clean verify -P smoketests"
so on....

//** You can pass the URLs and WebDrivers from command prompt itself with -D prefix
//** Data driven is not implemented, so some part of methods are hard coded

