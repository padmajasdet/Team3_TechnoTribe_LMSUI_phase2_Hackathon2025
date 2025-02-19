@TTLPH2-7 @homePage
Feature: Home Page

  Background: Admin gives the correct LMS portal URL
  
    Given Admin gives the correct LMS portal URL


  @TTLPH2-86
  Scenario: Verify LMS title
    Given Admin lands on login page
    When Admin enter valid data in all field and  clicks login button
    Then Admin should see LMS -Learning management system as title
    
  @TTLPH2-93
  Scenario: Verify LMS title alignment
    Given Admin lands on login page
    When Admin enter valid data in all field and  clicks login button
    Then LMS title should be on the top left corner of page
    