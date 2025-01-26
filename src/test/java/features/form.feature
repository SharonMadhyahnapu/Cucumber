Feature: Form Submission

  Scenario Outline: Successfull form submission
    Given User is on the form submission page
    When User enters "<First Name>" and "<Last Name>" and "<Phone>" and "<Email>"
    Then User should redirected to the secure area

    Examples:
      | First Name | Last Name     | Phone         | Email | 
      | Sharon     | Madhyahnapu   | 1234567890    | Test123@gmail.com  |
     


  Scenario: Form submission with invalid email
    Given User is on the form submission page
    When User enters "Yashu" and "Vinny" and "3455678998" and "invalid-email"
    Then An error message should displayed for the invalid email
