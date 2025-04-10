Feature: Registration

  Scenario: Successful registration
    Given I navigate to the site
    And I have selected my date of birth "05/05/1999"
    * I have entered my name "Linus"
    * I have entered my lastname "Gunnarsson"
    * I have entered my email "12@mail.com"
    * I have confirmed my email "12@mail.com"
    * I have entered my password "password1"
    * I have confirmed my password "password1"
    * I have checked all boxes
    When I click confirm
    Then I create my account

  Scenario Outline: Failed registrations
    Given I open a <browser> and navigate to the site
    And I have selected my date of birth "05/05/1999"
    * I have entered my name "Linus"
    * I enter my <lastName>
    * I write my <emailAddress>
    * I confirm my <secondEmailAddress>
    * I have entered my password "password1"
    * I rewrite <secondPassword>
    * I check <boxes>
    When I click confirm
    Then I get the <error> message I <expected>

    Examples:
      | lastName   |  | emailAddress | secondEmailAddress | secondPassword | boxes | error    | expected                                                                  | browser |
      |            |  | 123@mail.com | 123@mail.com       | password1      | all   | name     | Last Name is required                                                     | firefox |
      | Gunnarsson |  | 123@mail.com | 123@mail.com       | password2      | all   | password | Password did not match                                                    | edge    |
      | Gunnarsson |  | 123@mail.com | 123@mail.com       | password1      | some  | ToS      | You must confirm that you have read and accepted our Terms and Conditions | edge    |