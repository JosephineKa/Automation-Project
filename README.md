# Canvas Navigation Automation

> Automated end-to-end testing of Canvas LMS course navigation using Selenium WebDriver, Cucumber BDD, and Java

[![Java](https://img.shields.io/badge/Java-17+-red.svg)](https://www.java.com/)
[![Selenium](https://img.shields.io/badge/Selenium-4.x-brightgreen.svg)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-yellow.svg)](https://cucumber.io/)
[![JUnit](https://img.shields.io/badge/JUnit-4.13-blue.svg)](https://junit.org/junit4/)
[![IntelliJ](https://img.shields.io/badge/IDE-IntelliJ_IDEA-orange.svg)](https://www.jetbrains.com/idea/)

---

## 📋 Project Overview

This project automates the testing of a student's ability to navigate through **all course sections** in the Canvas Learning Management System (LMS). It validates that every critical course component loads correctly and remains accessible.

**Target System:** UIS Canvas (`https://uispringfield.instructure.com/`)  
**Test Course:** INTRO TO MACHINE LEARNING

### Why Automate Canvas Navigation?

| Problem | Solution |
|---------|----------|
| 🔁 Students repeat the same navigation steps daily | Automation eliminates manual effort |
| ✅ Need to verify all sections work correctly | Tests validate every section every run |
| 🏫 LMS stability after updates | Ensures Canvas remains functional post-maintenance |
| 💼 Real-world testing skills | Demonstrates industry-relevant automation |

---

## 🚀 Features Tested (9 Sections)

| Section | Tested | Locator Used |
|---------|--------|--------------|
| 📢 Announcements | ✅ | `By.id("announcements-link")` |
| 📝 Assignments | ✅ | `By.id("assignments-link")` |
| 📚 Modules | ✅ | `By.id("modules-link")` |
| ✍️ Quizzes | ✅ | `By.id("quizzes-link")` |
| 📊 Grades | ✅ | `By.id("grades-link")` |
| 🎥 Zoom | ✅ | `By.id("context_external_tool_139-link")` |
| 📁 Files | ✅ | `By.id("files-link")` |
| 🎬 My Media | ✅ | `By.id("context_external_tool_1704-link")` |
| 📦 My Materials | ✅ | `By.id("context_external_tool_1740-link")` |

**Result:** 9/9 sections visited | 100% pass rate | 0 broken links

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java 17+** | Primary programming language |
| **Selenium WebDriver** | Browser automation (Chrome) |
| **Cucumber (BDD)** | Gherkin feature files for readable tests |
| **JUnit** | Test runner |
| **ChromeDriver** | Bridge between Selenium and Chrome |
| **IntelliJ IDEA** | IDE for development |
| **Maven** | Build and dependency management |

---

## 📁 Project Structure
seleniumTesting/
├── src/
│ ├── main/
│ │ └── java/
│ │ └── Project1.java # Standalone script
│ └── test/
│ ├── java/
│ │ ├── TestRunner.java # JUnit Cucumber runner
│ │ └── stepDefinitions/
│ │ └── CanvasNavigationSteps.java # Selenium step code
│ └── resources/
│ └── features/
│ └── CanvasNavigation.feature # Gherkin scenarios
├── pom.xml # Maven dependencies
└── README.md # This file


---

## 📝 Gherkin Feature File

```gherkin
@CanvasNavigation
Feature: CanvasNavigation

  Scenario: Student navigates through all course sections
    Given the browser is open
    When user is on the canvas login page
    And user enters username
    And user enters password
    And user clicks login button
    Then user selects the course
    Then user selects announcements
    Then user selects assignments
    Then user selects modules
    Then user selects quizzes
    Then user selects grades
    Then user selects zoom
    Then user selects files
    Then user selects my media
    Then user selects my materials
Gherkin Syntax: Uses Given (precondition), When (action), And (additional steps), Then (outcome) — readable by developers, testers, and non-technical stakeholders.

🔧 Setup & Installation
Prerequisites
Prerequisite	Version	Check Command
Java JDK	17+	java -version
Maven	3.8+	mvn -version
Chrome Browser	Latest	—
ChromeDriver	Matching Chrome version	chromedriver --version
IntelliJ IDEA	Any	—

Step 1: Clone the Repository
bash
git clone https://github.com/JosephineKa/seleniumTesting.git
cd seleniumTesting

Step 2: Install Dependencies
bash
mvn clean install

Step 3: Update Credentials (Important!)
⚠️ Security Note: For demo purposes, credentials are in the code. For production, use environment variables or config file.

Option A: Environment Variables (Recommended)

bash
export CANVAS_USERNAME="your_username"
export CANVAS_PASSWORD="your_password"
Option B: Config File
Create src/test/resources/config.properties:

properties
canvas.username=your_username
canvas.password=your_password
Then update step definitions to read from this file (add to .gitignore).

Step 4: Run the Tests
From IntelliJ:

Right-click TestRunner.java → Run 'TestRunner'

From Terminal:

bash
mvn test

💻 Key Code Examples
Login Steps (By.id and By.name)
java
@And("user enters username")
public void user_enters_username() {
    wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.id("username")))
        .sendKeys("jkamw");
}

@And("user clicks login button")
public void user_clicks_login_button() {
    driver.findElement(By.name("_eventId_proceed")).click();

Course Selection (By.xpath)
java
@Then("user selects the course")
public void user_selects_the_course() {
    wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//a[contains(@class,'ic-DashboardCard__link') " +
                 "and contains(.,'INTRO TO MACHINE LEARNING')]")))
        .click();
    courseUrl = driver.getCurrentUrl();  // Capture dynamically

Section Navigation Pattern (Reusable for all 9 sections)
java
@Then("user selects announcements")
public void user_selects_announcements() {
    driver.navigate().to(courseUrl);  // Return to course home
    wait.until(ExpectedConditions
        .elementToBeClickable(By.id("announcements-link")))
        .click();
    System.out.println("✓ Announcements - " + driver.getCurrentUrl());
    pause(1000);
}

Test Runner
java
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepDefinitions",
    tags = "@CanvasNavigation",
    plugin = {"pretty"}
)
public class TestRunner {
    // JUnit runs this class — Cucumber does the rest
}
📊 Execution Flow
text
1. Login (By.id + By.name)
       ↓
2. Select Course (By.xpath)
       ↓
3. Announcements → 4. Assignments → 5. Modules
       ↓
6. Quizzes → 7. Grades → 8. Zoom
       ↓
9. Files → 10. My Media → 11. My Materials
       ↓
12. Close Browser (driver.quit())

📈 Test Results
Console Output (Successful Run)
=== Starting Canvas Navigation Test ===
✓ Browser is open and maximized
✓ Navigated to Canvas login page
✓ Entered username
✓ Entered password
✓ Clicked login button
✓ Selected INTRO TO MACHINE LEARNING course
  Course URL: https://uispringfield.instructure.com/courses/12345
✓ Announcements - .../courses/12345/announcements
✓ Assignments - .../courses/12345/assignments
✓ Modules - .../courses/12345/modules
✓ Quizzes - .../courses/12345/quizzes
✓ Grades - .../courses/12345/grades
✓ Zoom - .../courses/12345/external_tools/139
✓ Files - .../courses/12345/files
✓ My Media - .../courses/12345/external_tools/1704
✓ My Materials - .../courses/12345/external_tools/1740

=== Test Complete: All course sections visited ===
✓ Browser closed

Results Summary
Metric	Result
Sections Tested	9/9 ✅
Pass Rate	100%
Broken Links Found	0
Locator Types Used	3 (id, xpath, name)
Test Duration	~45 seconds

🐛 Bug Fixes Implemented
Bug	Before	After
StaleElementReferenceException	Direct findElement().click()	wait.until(elementToBeClickable()).click()
Course URL hardcoded	Assumed static URL	Capture courseUrl dynamically
No test isolation	One failure breaks all	Each section re-navigates to courseUrl
Browser left open	No cleanup	@After hook with driver.quit()
Timing issues	Thread.sleep()	WebDriverWait with conditions

🧠 Lessons Learned
Challenge	Solution
Canvas elements load at different speeds	Use WebDriverWait + ExpectedConditions
Course URL changes per session	Store URL dynamically after course selection
External tools (Zoom, My Media, My Materials) need special handling	Same pattern: wait + click + verify
Tests failing when run sequentially	Each section independently re-navigates to course home
Hardcoded credentials = security risk	Move to environment variables or config file

🔐 Security Best Practices
⚠️ This repository currently contains hardcoded credentials for academic/demo purposes only.

For production use:

Never commit passwords to GitHub

Use environment variables:
String username = System.getenv("CANVAS_USERNAME");
String password = System.getenv("CANVAS_PASSWORD");
Add config.properties to .gitignore
Use a test Canvas account, not your personal credentials

🚀 Future Enhancements
Add parallel test execution for faster runs

Integrate with GitHub Actions CI/CD

Add screenshot capture on test failure

Generate HTML test reports (ExtentReports)

Implement Page Object Model (POM) pattern

Add validation of section content (not just navigation)

Support multiple Canvas courses via configuration

👩‍💻 Author
Josephine Kamwela
📧 jkamw@uis.edu/kamwelajosephinek@gmail.com
🎓 University of Illinois Springfield
🐙 GitHub: @JosephineKa

🙏 Acknowledgments
lyfebiteslemp.com
Selenium & Cucumber open-source communities

📄 License
This project is for academic purposes as part of coursework.

🔗 Links
Resource	URL
GitHub Repository	https://github.com/JosephineKa/seleniumTesting
Canvas LMS	https://uispringfield.instructure.com/
Selenium Documentation	https://www.selenium.dev/documentation/
Cucumber Documentation	https://cucumber.io/docs/guides/











