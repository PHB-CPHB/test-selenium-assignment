# Automated System Testing with Selenium 2
This is a school assignment with the intenstion of "Demonstrate how to use Selenium to test a modern interactive (JavaScript driven) web application, and some of the problems involved with automated GUI testing"

## Instalation
1. Git clone this project https://github.com/Lars-m/seleniumExCompiled.git
  - Open a terminal and navigate into the root of the project, and type npm install
  - Open a new terminal (still in the root of the project) and type: npm run backend .This will run the REST backend for web site to be tested
  - In the original terminal, type: npm run client This will run the server hosting the client (GUI)
  - Open a browser with: localhost:3000/ and start testing ;-)
  
2. Git clone my project PS. Part one must be running in the background and you must have a Browers installed
  - Open a terminal and navigate into the root of the project, and type mvn install
  - Open intellij
  - Import the maven project
  - Change the "System.setProperty" to the directory of you selenium driver (Installtion guide to Selenium can be found [here](https://www.seleniumhq.org/download/) 
  - Run the tests
  
## Question to the assignment
- Discuss Pros and Cons with manual versus automated tests
- Explain about the Test Pyramid and whether this exercise supports the ideas in the Test Pyramid
- Discuss some of the problems with automated GUI tests and what makes such tests "vulnerable"
- Demonstrate details in how to create a Selenium Test using the code for the exercise
- Explain shortly about the DOM, and how you have read/manipulated DOM-elements in your test
- Explain how (and why it was necessary) you have solved "waiting" problems in your test

### Test Results
![Test Result][Result]

[Result]: https://github.com/philliphb/test-selenium-assignment/blob/master/Test%20Result.png "Test Results"
