@regression 
Feature: Login feature 

    
 @regrsstion
 Scenario: Create account and verify
   Given I open the default browser 
   Given I am on home page 
	When I go to Footer and click on Sign Up link
    And I enter rubel into FirstName text fields on Sign Up Screen 
    And I enter rahman into LastName text fields on Sign Up Screen
    And I enter rubelrahman9876@gmail.com into Email text fields on Sign Up Screen
    And I enter pass123& into Password text fields on Sign Up Screen
    And I enter rubelrahman9876@gmail.com into ReEnterEmail text fields on Sign Up Screen
    And I Select Jan from Month dropdown field of Birthday on Sign Up Screen
    And I Select 1 from Day dropdown field of Birthday on Sign Up Screen 
    And I Select 1999 from Year dropdown field of Birthday on Sign Up Screen
    And Select radio button male on Sign Up Screen
    And Click on Create Account
    Then verify that i am able to create account
    And I close the default browser 
  