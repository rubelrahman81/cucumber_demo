package framework;

import org.openqa.selenium.By;

public class CreateAccountPage extends BasePage{ 
	
	private By emailTextField = By.id("u_0_7");
	private By reEnterEmailTextField = By.id("u_0_a");
	private By passwordTextField = By.id("u_0_e");
	private By fNameField = By.id("u_0_2");
	private By lNameField = By.id("u_0_4");
	private By maleGenderButton = By.id("u_0_i");
	private By creatAccountButtion = By.id("u_0_m");
	private By dayOfBirth = By.id("day");
	private By monthOfBirth = By.id("month");
	private By yearOfBirth = By.id("year");


	public void enterEmail(String enterEmail) {
		sendText(emailTextField, enterEmail);
	}
	public void reEnterEmail(String reEnterEmail) {
		sendText(reEnterEmailTextField, reEnterEmail);
	}
	public void enterPassword(String enterPassword) {
		sendText(passwordTextField, enterPassword);
	}

    public void enterFirstName(String fName){
        sendText(fNameField, fName);
    }

    public void enterLastName(String lName){
        sendText(lNameField, lName);
    }

    public void clickOnMale(){
        clickOn(maleGenderButton);
    }
    
    public void clickOnCreatAccount(){
        clickOn(creatAccountButtion);
    }
    
    public void selectDateOfBirth(String day){
    clickOnDropdown (day, dayOfBirth);
    }

    public void selectMonthOfBirth(String month){
    clickOnDropdown(month, monthOfBirth);
    }
    
    public void selectYearOfBirth(String year){
    	clickOnDropdown(year, yearOfBirth);
	
}
}
