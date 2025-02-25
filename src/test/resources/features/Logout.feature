#Author: Tohfatul SDET126
@TTLPH2-6 @Logout @Regression
Feature: LogOut

  Background:
  Given Admin is logged in to LMS Portal
	
	@smoke
	Scenario: Validate delete functionality for batch
	Given Admin is on the Batch page
	When Admin enters the batch name in the search and click on delete icon
	Then Selected Batch should be deleted
	
	
	
	@US-TTLPH2-10 @TTLPH2-112 @sanity
	Scenario: Verify logout function
	When Admin clicks "Logout" on the navigation bar
	Then Admin should be redirected to login page
	
	@US-TTLPH2-10 @TTLPH2-114
	Scenario: Verify back button function 
	When Admin clicks browser back button
	Then Admin should receive error message
