package framework;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

public class HomePage extends BasePage{

	private By signupButtion = By.linkText("Sign Up");

	public void scrollOnhHomePage(){
		scrollOnThePage();
	}
	public void clickOnSignupButton(){
        clickOn(signupButtion);
    }

	
}
