@CanvasNavigation
Feature: CanvasNavigation

  Scenario: Student navigates through all course sections to make sure they are working as they should be
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