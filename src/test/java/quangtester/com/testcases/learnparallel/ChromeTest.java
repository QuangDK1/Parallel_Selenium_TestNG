package quangtester.com.testcases.learnparallel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class ChromeTest {

      @Test
      public void ChromeTest1() throws InterruptedException {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://anhtester.com");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//h3[normalize-space()='API Testing']")).click();
            driver.quit();
      }

      @Test
      public void ChromeTest2() throws InterruptedException {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://anhtester.com");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//h3[normalize-space()='API Testing']")).click();
            driver.quit();
      }

      @Test
      public void ChromeTest3() throws InterruptedException {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("https://anhtester.com");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//h3[normalize-space()='API Testing']")).click();
            driver.quit();
      }


}