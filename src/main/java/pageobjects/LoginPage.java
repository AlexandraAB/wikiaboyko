package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: Sashonok
 * Date: 03.05.14
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement loginNameField;

    @FindBy(name = "password")
    private WebElement loginPassField;

    @FindBy(className = "login-button")
    private WebElement submitLoginDialogButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("wiki/Test-homework_Wiki"));
    }

    public void pageIsLoaded() throws Error {
        isLoaded();
    }

    public SelectUnderLoggedUserPage submitFormByClickOnButton(WebElement element) throws Exception {
        element.submit();

       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return PageFactory.initElements(driver, SelectUnderLoggedUserPage.class);
    }

    public void typeUserName(String userName) throws Exception {
        typeTextIntoElement(loginNameField, userName);
    }

    public void typeUserPassword(String userPass) throws Exception {
        typeTextIntoElement(loginPassField, userPass);
    }

    public SelectUnderLoggedUserPage loginUser(String userName, String userPass) throws Exception{
        typeUserName(userName);
        typeUserPassword(userPass);
        return submitFormByClickOnButton(submitLoginDialogButton);
    }
}
