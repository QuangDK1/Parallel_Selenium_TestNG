package quangtester.com.keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import quangtester.com.drivers.DriverManager;

import java.time.Duration;
import java.util.List;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = 10;
    private static int WAIT_PAGE_LEADED_TIMEOUT = 30;


    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static void logConsole(String message) {
        System.out.println(message);
    }

    public static void hoverOnElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(DriverManager.getDriver());
        action.moveToElement(getWebElement(by));
        logConsole("Hover on element " + by);
    }

    public static WebElement highLightElement(By by) {
        waitForElementVisible(by);
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

    public static void rightClickElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(DriverManager.getDriver());
        action.contextClick(getWebElement(by));
        logConsole("Right click on element " + by);
    }

    public static void openURL(String URL) {
        DriverManager.getDriver().get(URL);
        waitForPageLoaded();
        logConsole("Open URL: " + URL);
    }

    public static String getCurrentUrl() {
        waitForPageLoaded();
        logConsole("Get Current URL: " + DriverManager.getDriver().getCurrentUrl());
        return DriverManager.getDriver().getCurrentUrl();
    }

    public static void clickElement(By by) {
        waitForElementVisible(by);
        highLightElement(by);
        getWebElement(by).click();
        logConsole("Click on element " + by);
        //Report
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value);
        logConsole("Set text " + value + " on element " + by);
    }

    public static String getTextElement(By by) {
        waitForElementVisible(by);
        logConsole("Get text of element " + by);
        logConsole("==> Text: " + getWebElement(by).getText());
        return getWebElement(by).getText();
    }

    public static String getAttributeElement(By by, String attributeName) {
        waitForElementVisible(by);
        logConsole("Get attribute value of element " + by);
        logConsole("==> Attribute value: " + getWebElement(by).getAttribute(attributeName));
        return getWebElement(by).getAttribute(attributeName);
    }

    public static void scrollToElementWithJS(By by) {
        waitForElementPresent(by);
        //Dùng Actions class
        //Robot class
        //Dùng JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        logConsole("Scroll to element " + by);
    }

    public static void scrollToElement(By by) {
        //Dùng Actions class

    }

    public static void scrollToElementWithRobot(By by) {
        //Dùng Robot class

    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForElementVisible( By by, int second) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementPresent(By by, int second) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementClickable(By by, int second) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second));

        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean verifyElementVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean verifyElementNotVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkElementExist(By by) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(by);

        if (listElement.size() > 0) {
            System.out.println("Element " + by + " existing.");
            return true;
        } else {
            System.out.println("Element " + by + " NOT exist.");
            return false;
        }
    }

    public static Boolean checkElementExist(String xpath) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(By.xpath(xpath));

        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + " existing.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist.");
            return false;
        }
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_PAGE_LEADED_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

}
