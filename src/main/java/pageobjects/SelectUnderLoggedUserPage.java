package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: Sashonok
 * Date: 03.05.14
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class SelectUnderLoggedUserPage extends BasePage {

    public static final String XPATH_CONTRIBUTE_BUTTON = "//div[@class='buttons']/nav/span[@class='drop']";
    public static final String XPATH_CONTRIBUTE_MENU_BUTTONS = "//div[@class='buttons']/nav/ul/li/a";
    public static final String XPATH_LOGIN_LABEL = "//a[contains(@href,'wiki/User:')]";


    @FindBy(xpath = XPATH_CONTRIBUTE_BUTTON)
    private WebElement contributeButton;

    @FindBy(xpath = XPATH_LOGIN_LABEL)
    private WebElement loggedUserNameLabel;

    public SelectUnderLoggedUserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("wiki/User:"));
    }

    public void pageIsLoaded() throws Error {
        isLoaded();
    }

    public String getLoggedUserNameShowedOnPage() throws Exception{
         return loggedUserNameLabel.getText().toLowerCase();
    }

    public void selectItem(WebElement mainElement, final String xpath, String text) throws Exception{
        mainElement.click();

        List<WebElement> elementsList = driver.findElements(By.xpath(xpath));
        for(WebElement element : elementsList){
            if(element.getText().toLowerCase().contains(text.toLowerCase())){
                element.click();
                break;
            }
        }
    }

    public VideoAddPage addVideo() throws Exception{
        selectItem(contributeButton, XPATH_CONTRIBUTE_MENU_BUTTONS, "Add a video");
        return PageFactory.initElements(driver, VideoAddPage.class);
    }


}
