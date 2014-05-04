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
public class VideoAddPage extends BasePage {


    public static final String XPATH_ADD_BUTTON = "//input[@type='submit' and @value='Add']";
    public static final String XPATH_LOGIN_LEBEL = "//a[contains(@href,'wiki/User:')]";

    @FindBy(id = "wpWikiaVideoAddUrl")
    private WebElement addVideoUrlField;

    @FindBy(xpath = XPATH_ADD_BUTTON)
    private WebElement addButton;

    @FindBy(xpath = XPATH_LOGIN_LEBEL)
    private WebElement loggedUserNameLabel;

    public VideoAddPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("wiki/Special:WikiaVideoAdd"));
    }

    public void pageIsLoaded() throws Error {
        isLoaded();
    }

    public LoadedItemDetailsPage uploadVideoLink(String link) throws Exception {
        addVideoUrlField.sendKeys(link);
        addButton.click();
        return PageFactory.initElements(driver, LoadedItemDetailsPage.class);
    }
}
