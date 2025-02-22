#Author: Tohfatul SDET126
@TTLPH2-1 @login @regression
Feature: Login Page

	Background:
		Given The browser is open
	  And Admin gives the correct LMS portal URL
	
	@TTLPH2-16 @smoke
  Scenario: Verify Admin is able to land on login page
    Then Admin lands on login page

	@TTLPH2-12 @smoke
  Scenario: Validate login with valid data in all field
    Given Admin lands on login page
    When Admin enter valid data in all field and clicks login button
    Then Admin should land on home page
  
  @TTLPH2-2 
  Scenario: Verify login button action through keyboard
    Given Admin lands on login page
    When Admin enter valid credentials  and clicks login button through keyboard
    Then Admin should land on home page

	@TTLPH2-9
  Scenario: Verify login button action through mouse
    Given Admin lands on login page
    When Admin enter valid credentials and clicks login button through mouse
    Then Admin should land on home page
  
  
  @TTLPH2-13  @defectToBeRaised
  Scenario Outline: Validate login with invalid data - "<TestCase>"
    Given Admin lands on login page
    When Admin enter invalid "<Username>" and/or "<Password>", and clicks login button
    Then Error message "Invalid username and password Please try again"
    
    Examples:
    |TestCase                         |Username                  |Password|
    | Password_Incorrect              |sdetnumpyninja@gmail.com  |Feb@2005|
    | Username_IncorrectLocalPart     |sdetnumpy22ninja@gmail.com|Feb@2025|
    | Username_MissingAtSymbol        |sdetnumpyninjagmail.com   |Feb@2025|
    | Username_IncorrectSubdomain     |sdetnumpyninja@hotmail.com|Feb@2025|
    | Username_IncorrectTopLevelDomain|sdetnumpyninja@gmail.net  |Feb@2025|
    
    
  @TTLPH2-14
  Scenario: Validate login credentials with null user name
    Given Admin lands on login page
    When Admin enter value only in password and clicks login button
    Then Error message "Please enter your user name"
   
  @TTLPH2-15
  Scenario: Validate login credentials with null password
    Given Admin lands on login page
    When Admin enter value only in user name and clicks login button
    Then Error message "Please enter your password"   
    

################## ADDITIONAL SCENARIOS ################################
		@TTLPH2-103
		Scenario: Additional_Validate login with Empty credentials and No Role selected
		Given Admin lands on login page
		When Admin enters no credentials, selects no role and clicks on login button
		Then Admin sees 3 error messages "Please enter your user name", "Please enter your password" and  "Please select your Role"
    
    @TTLPH2-104
    Scenario: Additional_Validate login with Empty Credentials only
    Given Admin lands on login page
		When Admin only selects role as Admin and clicks on login button
		Then Admin sees 2 error messages "Please enter your user name" and "Please enter your password"
    
    
    @TTLPH2-105
    Scenario: Additional_Validate login with valid Credentials but No role selected
    Given Admin lands on login page
    When Admin enters valid credentials but selects no role
		Then Error message "Please select your Role"
    
    ################## NON-FUNCTIONAL SCENARIOS ################################
    
    @TTLPH2-30 @TTLPH2-131
    Scenario: Verify dropdown option to select role
    Given Admin lands on login page
		Then Admin should see "Admin","Staff" and "Student" options in dropdown
		
    @TTLPH2-22 @TTLPH2-133
    Scenario: Validate sign in content
    Given Admin lands on login page
		Then Admin should see "Please login to LMS application"
		
		@TTLPH2-18 @TTLPH2-136
		Scenario: Verify for broken link
		Then If HTTP response >= 400, then the link is broken
		
		
		@TTLPH2-17 @TTLPH2-138
		Scenario Outline: Verify Admin is not able to land on home page with invalid URL - "<TestCase>"
		When Admin gives the invalid LMS portal URL for test case "<TestCase>"
		Then Admin should receive application error 
		
		 Examples:
    |TestCase                 |
    | Incorrect_DomainName    |
    | Misspelled_DomainName   |
    | Incorrect_Protocol      |
    | Incorrect_Endpoint      |
    | Misspelled_Endpoint     |    
    | Incorrect_TopLevelDomain|
    | Missing_TopLevelDomain  |
    | NonExistenSubDomain     |
    | InvalidCharacters       |

    
       
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
          