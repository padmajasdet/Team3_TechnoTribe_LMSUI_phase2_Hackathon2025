@ProgramFeature
Feature: Program Module

  Background: 
    Given Admin is logged in to LMS Portal

  @TC1 @Navigation
  Scenario: Verify navigation to Program page for Admin
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should be navigated to Program page

  @TC2 @MenuBar
  Scenario: Verify Logout displayed in menu bar
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see Logout in menu bar

  @TC3 @MenuBar
  Scenario: Verify heading in menu bar
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see the heading "LMS - Learning Management System"

  @TC4 @MenuBar
  Scenario: Verify other pages name displayed in menu bar
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see the page names as in order on menu bar

  @TC5 @AddNewProgram @sanity
  Scenario Outline: Verify Admin is able to save the new program details and search validation
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    And Admin is on program details form
    When Admin enters details for "<testcase>" for mandatory fields and Click on save button
    Then Admin gets message 'Program Created Successfully'

    Examples: 
      | testcase       |
      | validInputData |

  @TC6 @ManageProgramPageValidation
  Scenario: Verify heading in manage program
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see the Manage Program "Manage Program" Heading

  @TC7 @ManageProgramPageValidation
  Scenario: Verify view details of programs
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should able to see Program name, description, and status for each program

  @TC8 @ManageProgramPageValidation
  Scenario: Verify the Multiple Delete button state
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see a Delete button in left top is disabled

  @TC9 @SearchProgramValidation
  Scenario: Verify edited Program details
    Given Admin is on Program page
    When Admin searches with newly created Program "Name"
    Then Records of the newly created  "Program Name" is displayed and match the data entered

  @TC10 @EditProgramValidation
  Scenario Outline: Verify edited Program Name
    Given Admin is on Program page
    When Admin edits the program "Name" and click on save button for "<testcase>"
    Then Updated program name is seen by the Admin

    Examples: 
      | testcase           |
      | validInputEditData |
