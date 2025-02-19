#Author: Mathumathi Balakrishnan

@batch @regression
Feature: Batch Page

Background:
	Given Admin successfully Logged on to the LMS Portal

@sanity
Scenario: Verify Admin Navigate to Batch page successfully
	Given Admin is on the home Page
  When Admin Clicks on the Batch menu from the header
  Then Admin should be in the Manage Batch Page
  
@doing
Scenario: Validate "Title" in Batch Page
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the "LMS - Learning Management System" Title
	
	
 
 
    
