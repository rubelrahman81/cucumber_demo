package stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.CreateAccountPage;
import framework.HomePage;
import framework.LoginPage;
import framework.TestRunner;
import org.testng.Assert;

/**
 * Created by mohammadmuntakim on 6/9/17.
 */
public class LoginSD {

    private HomePage homePage = new HomePage();
    private CreateAccountPage createAccountPage = new CreateAccountPage();
    
    @Given("^I am on home page$")
    public void iAmOnHomePage() {
        Assert.assertEquals(SharedSD.getDriver().getTitle(), "Facebook - Log In or Sign Up", "Invalid Home Page");
    }

    @When("^I go to Footer and click on Sign Up link$")
    public void goToFooterAndClickOnSignUpLink() {
    	homePage.scrollOnhHomePage();
        homePage.clickOnSignupButton();
    }

   @When("^I enter(.+)into (FirstName|LastName|Email|Password|ReEnterEmail) text fields on Sign Up Screen$")
    public void inSignUpScreenEnterFirstNameLastNameEmailPassword(String input, String inputFields){
        switch(inputFields){
        case "FirstName":
            createAccountPage.enterFirstName(input);
            break;
        case "LastName":
            createAccountPage.enterLastName(input);
            break;
        case "Email":
            createAccountPage.enterEmail(input);
            break;
        case "Password":
            createAccountPage.enterPassword(input);
            break;
        case "ReEnterEmail":
        	createAccountPage.reEnterEmail(input);
        	break;
        
       }

   }
   

   @When("^I Select (.+) from (Month|Day|Year) dropdown field of Birthday on Sign Up Screen$")
    public void selectBirthday(String bdInput, String birthDay){
        switch(birthDay){
        case "Month" :
            createAccountPage.selectMonthOfBirth(bdInput);
            break;
        case "Day" :
            createAccountPage.selectDateOfBirth(bdInput);
            break;
        case "Year" :
            createAccountPage.selectYearOfBirth(bdInput);
            break;
        
       }
      
    }
   @When("^Select radio button male on Sign Up Screen$")
    public void selectGender() {
        createAccountPage.clickOnMale();
    }

   @When("^Click on Create Account$")
    public void clickOnCreateAccount(){
        createAccountPage.clickOnCreatAccount();
    }
   @Then("^verify that i am able to create account$")
   public void verifyCreateAccount() {
       Assert.assertEquals(SharedSD.getDriver().getTitle(), "Sign Up for Facebook | Facebook", "Invalid title found");
   }
    
}

