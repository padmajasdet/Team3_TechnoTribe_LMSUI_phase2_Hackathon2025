#Author: Tohfatul SDET126
@TTLPH2-6 @Logout @Regression
Feature: LogOut

  Background:
  Given Admin is logged in to LMS Portal
	
	@US-TTLPH2-10 @TTLPH2-112 @sanity
	Scenario: Verify logout function
	When Admin clicks "Logout" on the navigation bar
	Then Admin should be redirected to login page
	
#	Scenario: Verify back button function 
#	Given Admin is in home page
#	When Admin clicks browser back button
#	Then Admin should receive error message
