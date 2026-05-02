package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CanvasNavigationSteps {
    WebDriver driver;
    WebDriverWait wait;
    String courseUrl;

    @Before
    public void setUp() {
        System.out.println("=== Starting Canvas Navigation Test ===");
    }

    @Given("the browser is open")
    public void the_browser_is_open() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        System.out.println("✓ Browser is open and maximized");
    }

    @When("user is on the canvas login page")
    public void user_is_on_the_canvas_login_page() {
        driver.get("https://uispringfield.instructure.com/");
        System.out.println("✓ Navigated to Canvas login page");
    }

    @And("user enters username")
    public void user_enters_username() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                .sendKeys("jkamw");
        System.out.println("✓ Entered username");
    }

    @And("user enters password")
    public void user_enters_password() {
        driver.findElement(By.id("password")).sendKeys("Cap1Jay2kay12");
        System.out.println("✓ Entered password");
    }

    @And("user clicks login button")
    public void user_clicks_login_button() {
        driver.findElement(By.name("_eventId_proceed")).click();
        System.out.println("✓ Clicked login button");
    }

    @Then("user selects the course")
    public void user_selects_the_course() {
        wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@class,'ic-DashboardCard__link') and contains(.,'INTRO TO MACHINE LEARNING')]")))
                .click();
        courseUrl = driver.getCurrentUrl();
        System.out.println("✓ Selected INTRO TO MACHINE LEARNING course");
        System.out.println("  Course URL: " + courseUrl);
    }

    @Then("user selects announcements")
    public void user_selects_announcements() {
        driver.navigate().to(courseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("announcements-link"))).click();
        System.out.println("✓ Announcements - " + driver.getCurrentUrl());
        pause(1000);
    }

    @Then("user selects assignments")
    public void user_selects_assignments() {
        driver.navigate().to(courseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("assignments-link"))).click();
        System.out.println("✓ Assignments - " + driver.getCurrentUrl());
        pause(1000);
    }

    @Then("user selects modules")
    public void user_selects_modules() {
        driver.navigate().to(courseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("modules-link"))).click();
        System.out.println("✓ Modules - " + driver.getCurrentUrl());
        pause(1000);
    }

    @Then("user selects quizzes")
    public void user_selects_quizzes() {
        driver.navigate().to(courseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("quizzes-link"))).click();
        System.out.println("✓ Quizzes - " + driver.getCurrentUrl());
        pause(1000);
    }

    @Then("user selects grades")
    public void user_selects_grades() {
        driver.navigate().to(courseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("grades-link"))).click();
        System.out.println("✓ Grades - " + driver.getCurrentUrl());
        pause(1000);
    }

    @Then("user selects zoom")
    public void user_selects_zoom() {
        driver.navigate().to(courseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("context_external_tool_139-link"))).click();
        System.out.println("✓ Zoom - " + driver.getCurrentUrl());
        pause(1000);
    }

    @Then("user selects files")
    public void user_selects_files() {
        driver.navigate().to(courseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("files-link"))).click();
        System.out.println("✓ Files - " + driver.getCurrentUrl());
        pause(1000);
    }

    @Then("user selects my media")
    public void user_selects_my_media() {
        driver.navigate().to(courseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("context_external_tool_1704-link"))).click();
        System.out.println("✓ My Media - " + driver.getCurrentUrl());
        pause(1000);
    }

    @Then("user selects my materials")
    public void user_selects_my_materials() {
        driver.navigate().to(courseUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("context_external_tool_1740-link"))).click();
        System.out.println("✓ My Materials - " + driver.getCurrentUrl());
        pause(1000);
    }

    private void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            System.out.println("\n=== Test Complete: All course sections visited ===");
            pause(3000);
            driver.quit();
            System.out.println("✓ Browser closed");
        }
    }
}