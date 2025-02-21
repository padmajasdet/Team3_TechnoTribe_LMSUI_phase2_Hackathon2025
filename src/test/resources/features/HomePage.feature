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
  Scenario: Validate navigation bar text
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see correct spelling in navigation bar text

  @Scenario4_homePage
  Scenario: Validate LMS title has correct spelling and space
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see correct spelling and space in LMS title

  @Scenario5_homePage
  Scenario: Validate alignment for navigation bar
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see the navigation bar text on the top right side

  @Scenario6_homePage
  Scenario: Validate navigation bar order 1st home
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see home in the 1st place

  @Scenario7_homePage
  Scenario: Validate navigation bar order 2nd Program
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see program in the 2nd place

  @Scenario8_homePage
  Scenario: Validate navigation bar order 3rd  batch
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see batch in the  3rd place

  @Scenario9_homePage
  Scenario: Validate navigation bar order 4th  class
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see class in the 4th place

  @Scenario10_homePage
  Scenario: validate navigation bar order 5th logout
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see class in the 5th place

  @Scenario11_homePage
  Scenario: verify pie-chart presence
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see piechart

  @Scenario12_homePage
  Scenario: Verify user details
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see welcome message with user name and role

  @Scenario13_homePage
  Scenario: Verify bar chart
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see bar chart for Active and inactive user

  @Scenario14_homePage
  Scenario: Verify user count
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see user count

  @Scenario15_homePage
  Scenario: Verify staff count
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see staff count

  @Scenario16_homePage
  Scenario: verify program count
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see Program count

  @Scenario17_homePage
  Scenario: Verify batch count
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see batch count

  @Scenario18_homePage
  Scenario: Verify staff table pagination
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see staff table with pagination icons

  @Scenario19_homePage
  Scenario: verify previous page icon disable
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then admin should see previous page icon disabled

  @Scenario20_homePage
  Scenario: verify first page icon disabled
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then admin should see first page icon disabled
    
     @Scenario21_homePage
  Scenario: Verify staff data page split
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should see 5 staff data in a page
