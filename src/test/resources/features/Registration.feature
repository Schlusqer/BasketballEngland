Feature: Registration

  Background: Navigate to the site
    Given I navigate to the site

  Scenario: Successful registration
    Given I have selected my date of birth "05/05/1999"
    * I have entered my name "Linus"
    * I have entered my lastname "Gunnarsson"
    * I have entered my email "123@mail.com"
    * I have confirmed my email "123@mail.com"
    * I have entered my password "password1"
    * I have confirmed my password "password1"
    * I have checked all boxes
    When I click confirm
    Then I create my account


