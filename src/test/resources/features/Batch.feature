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
  
Scenario: Validate "Title" in Batch Page
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the "LMS - Learning Management System" Title

Scenario: Validate "heading" in the Batch Page
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the "Manage Batch" Heading

Scenario: Validate disabled "Delete Icon" under the header in the Batch Page
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the disabled "Delete Icon" under the header

#Scenario: Validate pagination in the Batch Page
#	Given Admin is on the home Page
#	When Admin Clicks on the Batch menu from the header
#	Then Admin should see the enabled pagination controls under the data table

#Scenario: Validate edit icon in each data rows
#	Given Admin is on the home Page
#	When Admin Clicks on the Batch menu from the header
#	Then Admin should see the edit icon in each row

#Scenario: validate delete icon in each data rows
#	Given Admin is on the home Page
#	When Admin Clicks on the Batch menu from the header
#	Then Admin should see the delete icon in each row

#Scenario: validate checkbox in each data rows
#	Given Admin is on the home Page
#	When Admin Clicks on the Batch menu from the header
#	Then Admin should see the checkbox in each row

#Scenario: Validate Datatable headers
#	Given Admin is on the home Page
#	When Admin Clicks on the Batch menu from the header
#	Then Admin should see the datatable headers Batch name, Batch Description,Batch Status, No Of classes, Program Name, Edit/Delete

#Scenario: Validate "Checkbox" in the Datatable header row
#	Given Admin is on the home Page
#	When Admin Clicks on the Batch menu from the header
#	Then Admin should see the checkbox  in the datatable header row

#Scenario: Validate "sort icon" next to all the datatable header
#	Given Admin is on the home Page
#	When Admin Clicks on the Batch menu from the header
#	Then Admin should see the sort icon next to all Datatable headers



	
		
	
 
 
    
