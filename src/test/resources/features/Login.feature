@TTLPH2-1 @login @regression
Feature: Login Page

	Background:
		Given The browser is open
	  And Admin gives the correct LMS portal URL
	
	@TTLPH2-16 @sanity
  Scenario: Verify Admin is able to land on login page
    Then Admin lands on login page

	@TTLPH2-12 @sanity
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
    
  #Scenario Outline: Validate login with invalid data
    #Given Admin lands on login page
    #When Admin enter invalid data and clicks login button
    #Then Error message "Invalid username and password Please try again"
    
    #Examples:
    
    
  #Scenario: Validate login credentials with null user name
    #Given Admin lands on login page
    #When Admin enter value only in password and clicks login button
    #Then Error message" Please enter your user name"
    
  #Scenario: Validate login credentials with null password
    #Given Admin lands on login page
    #When Admin enter value only in user name and clicks login button
    #Then Error message" Please enter your password"   
    
    
                 