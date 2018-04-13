Feature: Ing test automation code Challenge
  Scenario: Go to google page and search for something
    Given I go to the Google search page
    When I insert some string in the search input field
    And I click on the Google Search button
    Then The browers displays list o search results

  Scenario: Go to ING homepage and check that the Create Account link is present and can be clicked
   Given I accessed the ING Homebank link as first link from Google search
   When I click the Create Account link, I get to see the Create Account page
   And the Create Account page contains some expected fields
