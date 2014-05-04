package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: Sashonok
 * Date: 03.05.14
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class LoadedItemDetailsPage extends BasePage {

    public String XPATH_LOADED_FILE_NAME_LINK = "//p[a[contains(text(),'%s')]]/preceding-sibling::a/span/span[@itemprop='name']";

    public LoadedItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("wiki/File:FILENAME"));
    }

    public void pageIsLoaded() throws Error {
        isLoaded();
    }

    public String getLoadedVideoLabelName(String userName) throws Exception {
        //change the xpath to find loaded video by user name
        userName = userName.replaceFirst(".", userName.substring(0, 1).toUpperCase());
        XPATH_LOADED_FILE_NAME_LINK = String.format(XPATH_LOADED_FILE_NAME_LINK, userName);

        WebElement e = driver.findElement(By.xpath(XPATH_LOADED_FILE_NAME_LINK));
        return getElementLabelNameByText(e);
    }
}
