package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: Sashonok
 * Date: 03.05.14
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class BasePage extends LoadableComponent<BasePage> {

    protected final WebDriver driver;
    public static final Integer TIME_OUT = 50;
    public static final String XPATH_LOGIN_BUTTON = "//a[@class='ajaxLogin' and @data-id='login']";

    @FindBy(xpath = XPATH_LOGIN_BUTTON)
    private WebElement openLoginDialogButton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get("http://testhomework.wikia.com/");
    }

    @Override
    protected void isLoaded() throws Error {
       String url = driver.getCurrentUrl();
       assertTrue(url.contains("/wiki/Test-homework_Wiki"));
    }

    public void pageIsLoaded() throws Error {
        isLoaded();
    }

    public WebElement elementIsDisplayedAndAvailable(final String xpath) throws Exception{

        (new WebDriverWait(driver, TIME_OUT)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement e = d.findElement(By.xpath(xpath));
                return e.isDisplayed() &&  e.isEnabled();
            }
        });
        return driver.findElement(By.xpath(xpath));
    }

    public boolean elementIsDisplayedAndEnabled(WebElement element) throws Exception{
        return element.isDisplayed() && element.isEnabled();
    }

    public String getElementLabelName(WebElement element) throws Exception{
        return element.getAttribute("value").toString().toLowerCase();
    }

    public String getElementLabelNameByText(WebElement element) throws Exception{
        return element.getText().toLowerCase();
    }

    public void typeTextIntoElement(WebElement element, String text) throws Exception{
        element.clear();
        element.sendKeys(text);
    }

    public LoginPage openLoginDialog() throws Exception {
        elementIsDisplayedAndEnabled(openLoginDialogButton);
        openLoginDialogButton.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }

}
