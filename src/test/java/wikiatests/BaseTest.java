package wikiatests;

import junit.framework.TestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.InputStream;
import java.util.Properties;

public class BaseTest extends TestCase{

	private BasePage basePage;
	private WebDriver driver;
    private String user = "wikiaboyko1";
    private String pass = "boyko007";
    private String browser = "firefox";
    private String chrome_driver_path = "chromedriver.exe";
    private String youtubeVideoLink = "http://www.youtube.com/watch?v=h9tRIZyTXTI";
    private String loadedVideoComment = "Best classical music";

    private void initPropeties() {

        Properties props = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("prop.properties");
            props.load(stream);
            browser = props.getProperty("browser").toString();
            user = props.getProperty("user").toString();
            pass = props.getProperty("pass").toString();
            chrome_driver_path = props.getProperty("chrome_path").toString();
            youtubeVideoLink = props.getProperty("video_link").toString();
            loadedVideoComment = props.getProperty("video_comment").toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initWebDriver() {
        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", chrome_driver_path);
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
    }

    @BeforeMethod
	public void setUp() throws Exception {
        // init properties
        initPropeties();
        // init WebDriver
        initWebDriver();

        basePage = PageFactory.initElements(driver, BasePage.class);
        basePage.get();
        basePage.pageIsLoaded();
	}

    @Test
    public void uploadVideoUnderRegisteredUser() throws Exception{

        LoginPage loginPage = basePage.openLoginDialog();
        SelectUnderLoggedUserPage selectPage = loginPage.loginUser(user, pass);
        assertEquals("logged under unknown user name", user.toLowerCase(),selectPage.getLoggedUserNameShowedOnPage());

        VideoAddPage videoAddPage = selectPage.addVideo();
        LoadedItemDetailsPage loadedItemPage = videoAddPage.uploadVideoLink(youtubeVideoLink);

        String videoLink = loadedItemPage.getLoadedVideoLabelName(user);
        assertTrue(videoLink.contains(loadedVideoComment.toLowerCase()));
    }

    @Test
    public void loginUnderSpecifiedUserNameTest() throws Exception{
        LoginPage loginPage = basePage.openLoginDialog();
        SelectUnderLoggedUserPage selectPage = loginPage.loginUser(user, pass);
        assertEquals("logged under unknown user name", user.toLowerCase(),selectPage.getLoggedUserNameShowedOnPage());
    }
	
	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

}
