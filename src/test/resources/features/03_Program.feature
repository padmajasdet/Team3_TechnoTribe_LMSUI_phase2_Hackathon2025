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

  @TC5 @ManageProgramPageValidation
  Scenario: Verify heading in manage program
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see the Manage Program "Manage Program" Heading

  @TC6 @ManageProgramPageValidation
  Scenario: Verify view details of programs
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should able to see Program name, description, and status for each program

  @TC7 @ManageProgramPageValidation
  Scenario: Verify the Multiple Delete button state
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see a Delete button in left top is disabled

  @TC8 @ManageProgramPageValidation
  Scenario: Verify the Search button in manage program
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see Search bar with text as "Search..."

  @TC9 @ManageProgramPageValidation
  Scenario: Verify column header name of data table
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see data table with column header on the Manage Program Page as  Program Name, Program Description, Program Status, Edit/Delete

  @TC10 @ManageProgramPageValidation
  Scenario: Verify checkbox default state beside Program Name column header
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see checkbox default state as unchecked beside Program Name column header

  @TC11 @ManageProgramPageValidation
  Scenario: Verify checkboxes default state beside each Program names in the data table
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see check box default state as unchecked on the left side in all rows against program name

  @TC12 @ManageProgramPageValidation
  Scenario: Verify Sort icon in manage program
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see the sort arrow icon beside to each column header except Edit and Delete

  @TC13 @ManageProgramPageValidation
  Scenario: Verify edit and delete icon in manage program
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see the Edit and Delete buttons on each row of the data table

  @TC14 @ManageProgramPageValidation
  Scenario: Verify pagination icons below data table in manage program
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see the text along with Pagination icon below the table

  @TC15 @ManageProgramPageValidation
  Scenario: Verify footer message in manage program
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    Then Admin should see the footer with total programs

  # --------------------------------Add New Program------------------------------------------------------
  @TC16 @ManageProgramPageValidation
  Scenario: Verify sub menu displayed in program menu bar
    Given Admin is on Program page
    When Admin clicks "Program" on the navigation bar
    Then Admin should see sub menu in menu bar as "Add New Program"
    
  @TC17 @AddNewProgram @smoke @try
  Scenario Outline: Verify Admin is able to save the new program details and search validation
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    And Admin is on program details form
    When Admin enters details for "<testcase>" for mandatory fields and Click on save button
    Then Admin gets message 'Program Created Successfully'

    Examples: 
      | testcase       |
      | validInputData |

  @TC18 @SearchProgramValidation
  Scenario: Verify created Program details
    Given Admin is on Program page
    When Admin searches with newly created Program "Name"
    Then Records of the newly created  "Program Name" is displayed and match the data entered

  @TC19 @EditProgramValidation @rerun @try
  Scenario Outline: Verify Edit option and edited Program Details
    Given Admin is on Program page
    When Admin edits the program "Name" and click on save button for "<testcase>"
    Then Updated program "Name" and "Desc" and "Status" is seen by the Admin

    Examples: 
      | testcase           |
      | validInputEditData |

  @TC20 @AddNewProgram
  Scenario: Verify add New Program
    Given Admin is on Program page
    When Admin clicks Add New Program under Program menu bar
    Then Admin should see pop up window for program details

  @TC21 @AddNewProgram
  Scenario: Verify title of the pop up window
    Given Admin is on Program page
    When Admin clicks Add New Program under Program menu bar
    Then Admin should see window title as "Program Details"

  @TC22 @AddNewProgram
  Scenario: Verify empty form submission
    Given Admin is on Program details form
    When Admin clicks save button without entering mandatory fields
    Then Admin gets message for mandatory field required

  @TC23 @AddNewProgram
  Scenario: Verify Cancel button
    Given Admin is on Program details form
    When Admin clicks Cancel button
    Then Admin can see Program Details form disappears

  @TC24 @AddNewProgram
  Scenario: Verify Cancel button
    Given Admin is on Program details form
    When Admin clicks X button on pop up window
    Then Admin can see Program Details form disappears

  #----------------------------Edit Program-----------------------------
  @TC25 @EditProgram
  Scenario: Verify Edit option
    Given Admin is on Program page
    When Admin clicks on Edit option for particular program name "LMSUI"
    And Admin clicks Cancel button
    Then Admin can see Program Details form disappears

  #----------------------------Delete Program-----------------------------
  @TC26 @DeleteProgram
  Scenario: Verify Admin is able to delete program
    Given Admin is on Confirm deletion form for program "Name"
    When Admin clicks on Yes button
    Then Admin can see "Successful Program Deleted" message

  @TC27 @DeleteProgram
  Scenario: Verify Admin is able to click 'No'
    Given Admin is on Confirm deletion form for program "Name"
    When Admin clicks on No button
    Then Admin can see Confirm deletion form disappears

  @TC28 @DeleteProgram
  Scenario: Verify Admin is able to close the window with "X"
    Given Admin is on Confirm deletion form for program "Name"
    When Admin clicks on close X button
    Then Admin can see Confirm deletion form disappears

  @TC29 @DeleteProgram
  Scenario: Verify Admin does not find deleted program
    Given Admin is on Program page
    When Admin Searches for "Micro services"
    Then There should be zero results

  #----------------------------Delete Multiple Programs-----------------------------
  @TC30 @DeleteMultipleProgram
  Scenario: Verify Admin is able to select multiple programs
    Given Admin is on Program page
    Then Admin selects more than one program by clicking on the checkbox

  @TC31 @DeleteMultipleProgram
  Scenario: Verify Admin is able to delete Multiple programs
    Given Admin is on Program page
    When Admin clicks on the delete button on the left top of the program page
    Then Admin will get confirm deletion popup

  @TC32 @DeleteMultipleProgram
  Scenario: Verify Admin is able to select multiple programs
    Given Admin is on Program page
    When Admin clicks on the delete button on the left top of the program page
    And Admin clicks on Yes button
    Then Admin can see "Successful Program Deleted" message

  @TC33 @DeleteMultipleProgram
  Scenario: Verify Admin is able to click 'No'
    Given Admin is on Program page
    When Admin clicks on the delete button on the left top of the program page
    And Admin clicks on No button
    Then Admin can see Confirm deletion form disappears

  @TC34 @DeleteMultipleProgram
  Scenario: Verify Admin is able to close the window with "X"
    Given Admin is on Program page
    When Admin clicks on the delete button on the left top of the program page
    And Admin clicks on close X button
    Then Admin can see Confirm deletion form disappears

  #----------------------------Negative Scenarios Programs-----------------------------
  @TC35 @AddNewProgramNeagtive
  Scenario Outline: Verify Admin is able to save the new program details and search validation
    Given Admin is on home page after Login
    When Admin clicks "Program" on the navigation bar
    And Admin is on program details form
    When Admin enters details for "<testcase>" for mandatory fields and Click on save button
    Then Admin gets error message

    Examples: 
      | testcase                |
      | InvalidProgName         |
      | InvalidProgDesc         |
      | InvalidExistingProgBug  |
      | InvalidExistingProgName |
  #@TC36
  #Scenario Outline: Verify Add New Program with already existing Program Name
    #Given Admin is on Program details form
    #When Admin enters details for "<testcase>" for mandatory fields and Click on save button
    #Then Admin gets message "Program name is already exist." on Program Details Pop up
#
    #Examples: 
      #| testcase            |
      #| InvalidExistingProgName |
