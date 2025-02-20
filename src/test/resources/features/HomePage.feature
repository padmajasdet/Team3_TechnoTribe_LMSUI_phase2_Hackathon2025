@homePage
Feature: Home Page

  Background: Admin gives the correct LMS portal URL
    Given Admin gives the correct LMS portal URL

  @Scenario1_homePage
  Scenario: Verify LMS title
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see LMS -Learning management system as title

  @Scenario2_homePage
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then LMS title should be on the top left corner of page

  @Scenario3_homePage
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see correct spelling in navigation bar text

  @Scenario4_homePage
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see correct spelling and space in LMS title

  @Scenario5_homePage
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see the navigation bar text on the top right side

  @Scenario6_homePage
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see home in the 1st place

  @Scenario7_homePage
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see program in the 2nd place

  @Scenario8_homePage
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see batch in the  3rd place

  @Scenario9_homePage
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see class in the 4th place

  @Scenario10_homePage
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see class in the 5th place
