package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Config;
import utils.Constants_Vars;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();


    public static WebDriver getDriver() {

        if (webDriver.get() == null) {
            if(Boolean.parseBoolean(Config.get(Constants_Vars.SELENIUM_GRID_ENABLED))){
                webDriver.set(getRemoteDriver());
            }else {
                webDriver.set(createDriver());
            }
        }
        return webDriver.get();
    }

    private static WebDriver createDriver() {
        WebDriver driver = null;

        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/driver/drivers/Chrome/" + pathDirDriverChromeOS());
        WebDriverManager.chromedriver().setup();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));
        driver.manage().window().maximize();


        return driver;
    }

    private static WebDriver getRemoteDriver() {
        Capabilities capabilities;
        capabilities = new ChromeOptions();
        WebDriver remoteDriver = null;
        String urlFormat = Config.get(Constants_Vars.SELENIUM_GRID_URL_FORMAT);
        String hubHost = Config.get(Constants_Vars.SELENIUM_GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);

        try {
            remoteDriver = new RemoteWebDriver(new URL(url), capabilities);
            remoteDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            remoteDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));
            remoteDriver.manage().window().maximize();

        }catch (MalformedURLException ex){
            System.out.println(ex);
        }
        return remoteDriver;
    }

    public static void cleanupDriver() {
        webDriver.get().quit();
        webDriver.remove();
    }

}
