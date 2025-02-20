@ProgramFeature
Feature: Program Module

	Background:
		Given Admin is logged in to LMS Portal


  @TC1 @Navigation
  Scenario: Verify navigation to Program page for Admin
  
    Given Admin is on home page after Login
    And Admin clicks "Program" on the navigation bar
    Then Admin should be navigated to Program page

  @TC2 @MenuBar
  Scenario: Verify Logout displayed in menu bar
  
    Given Admin is on home page after Login
    And Admin clicks "Program" on the navigation bar
    Then Admin should see Logout in menu bar
    
     @TC3 @MenuBar
  Scenario: Verify heading in menu bar
  
    Given Admin is on home page after Login
    And Admin clicks "Program" on the navigation bar
    Then Admin should see the heading "LMS - Learning Management System"