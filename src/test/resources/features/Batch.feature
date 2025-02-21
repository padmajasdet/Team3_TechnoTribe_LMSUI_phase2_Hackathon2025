#Author: Mathumathi Balakrishnan

@batch 
Feature: Batch Page

Background:
Given Admin successfully Logged on to the LMS Portal


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

Scenario: Validate pagination in the Batch Page
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the enabled pagination controls under the data table

Scenario: Validate edit icon in each data rows
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the edit icon in each row

Scenario: validate delete icon in each data rows
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the delete icon in each row

Scenario: validate checkbox in each data rows
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the checkbox in each row

Scenario: Validate Datatable headers
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the datatable headers Batch name, Batch Description,Batch Status, No Of classes, Program Name, Edit/Delete

Scenario: Validate "Checkbox" in the Datatable header row
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the checkbox  in the datatable header row

Scenario: Validate "sort icon" next to all the datatable header
	Given Admin is on the home Page
	When Admin Clicks on the Batch menu from the header
	Then Admin should see the sort icon next to all Datatable headers

#Scenario: Verify sub menu displayed in batch menu bar
#Given Admin clicks "batch" on the navigation bar
#Then Admin should see sub menu in menu bar as "Add New Batch"
#
Scenario: Validate Admin able to click on the Add new Batch Option
Given Admin is on the home Page
When Admin clicks on "Add New batch" under the "batch" menu bar
Then Admin should see the Batch Details pop up window

Scenario: Validate all the fields exist in pop up 
Given Admin is on the Batch Details Pop Up WIndow
Then The pop up should include the fields Batch Name,Number of classes and Description as text box,Program Name as drop down Status as radio button

Scenario: Validate batchname prefix selected program name
Given Admin is on the Batch Details Pop Up WIndow
When Admin selects program name present in the dropdown
Then Admin should see selected program name in the batch name prefix box

Scenario: Validate batch name suffix box should accept only numbers
Given Admin is on the Batch Details Pop Up WIndow
When Admin enters alphabets in batch name suffix box
Then Admin should get error message below the text box of respective field

Scenario: Validate batch name prefix box is not editable
Given Admin is on the Batch Details Pop Up WIndow
When Admin enters alphabets in batch name prefix box
Then Admin should see empty text box

Scenario: Validate input data only for mandatory fields
Given Admin is on the Batch Details Pop Up WIndow
When Admin enters the data only to the mandatory fields and clicks save button
Then Admin should get a successful message

Scenario: validate input data missing for mandatory fields
Given Admin is on the Batch Details Pop Up WIndow
When Admin leaves blank one of the mandatory fields
Then Admin should get a error message on the respective mandatory field
@sanity
Scenario: validate save button in Batch details pop up
Given Admin is on the Batch Details Pop Up WIndow
When Admin enters the valid data to all the fields and click save button 
Then Admin should get a successful message

Scenario: validate cancel button in Batch details pop up
Given Admin is on the Batch Details Pop Up WIndow
When Admin enters the valid data to all the mandatory fields and click cancel button 
Then Admin can see the batch details popup closes without creating any batch

Scenario: validate close icon on the batch details pop up
Given Admin is on the Batch Details Pop Up WIndow
When Admin clicks on the close icon
Then batch details pop up closes


	
		
	
 
 
    
