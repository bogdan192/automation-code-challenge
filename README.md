## ING code challenge

Prove your coding and automation skills by completing the following challenge.


### Minimal Requirements
* Implement the first scenario from the CodeChallenge.feature
* Make the feature runnable when executing the maven command ```mvn test```
* Implement a second scenario where you :
    * Chose a  page from the list of Google search results and navigate to it 
    * Execute specific actions on the given page in accordance with it's functionality . For example you search for
    Amazon , you chose the first result from the list , on the Amazon page,  you search for java book , 
    select the result with smallest number of ratings and add it to  your cart (this only servers as an example you are 
    free to choose any other website and scenario)

    
### Bonus Requirements
* Use the Page Object design pattern http://www.seleniumhq.org/docs/06_test_design_considerations.jsp
* Use Spring for dependency injection
* Inject variable data like page url's (https://ww.google.com) from an external source (for example properties files ) 
* Implement the ability to use different browsers (FireFox / Chrome)
* Implement a mechanism that will trigger a test report to be generated after all the tests are executed
 
 
### Work methodology 
* Checkout the code from the master branch 
* Implement the requirements in your own branch 
* When you are finished push the new branch to this repository