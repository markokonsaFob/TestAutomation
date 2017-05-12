Feature: Simple UI Selenium test

  Scenario: User search from dictionary
    Given user opens the "http://aare.edu.ee/dictionary.html" with browser
    Then dictionary page should be visible
    And estonian language should be selected
    When user enters "infotehnoloogia" into search bar
    And user clicks submit button
    Then "IT" should be visible as a result
