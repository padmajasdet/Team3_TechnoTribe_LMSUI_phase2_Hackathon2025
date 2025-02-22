#Author: Mathumathi Balakrishnan

@batch 
Feature: Batch Page

Background:
Given Admin successfully Logged on to the LMS Portal

#Batch Page Navigation
Scenario: Verify Admin Navigate to Batch page successfully
	Given Admin is on the home Page
  When Admin Clicks on the Batch menu from the header
  Then Admin should be in the Manage Batch Page

#Batch Page Validation  
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

#Add New Batch 
Scenario: Validate Admin able to click on the Add new Batch Option
	Given Admin is on the home Page
	When Admin clicks on "Add New batch" under the "batch" menu bar
	Then Admin should see the Batch Details pop up window

#Add New Batch details pop up 
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

@smoke
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

#Edit icon Validation
Scenario: Validate Edit icon feature in any row
	Given Admin is on the Batch page
	When Admin clicks the edit icon
	Then Admin should see the Batch details pop up window in edit

Scenario: Validate program name  value is disabled to edit
	Given Admin is on the Batch page
	When Admin clicks the edit icon
	Then Admin should see Program name value field is disabled for editing

Scenario: Validate batch name  value is disabled to edit
	Given Admin is on the Batch page
	When Admin clicks the edit icon
	Then Admin should see batch name value field is disabled for editing

Scenario: Validate editing description and No. of classes fields with invalid data in the pop up
	Given Admin is on the Batch Details Page
	When Admin Updates any fields with invalid data and click save button
	Then Admin should get a error message under the respective field

Scenario: validate save button in Batch details pop up
	Given Admin is on the Batch Details Page
	When Admin edit the valid data to all the mandatory fields and click save button 
	Then Admin should get a successful message for editing the batch

Scenario: validate cancel button in Batch details pop up
	Given Admin is on the Batch Details Page
	When Admin edit the valid data to all the mandatory fields and click cancel button 
	Then Admin can see the batch details popup closes without editing the batch

#Delete batch validation

#Scenario: validate delete Icon on any row
#	Given Admin is on the Batch page
#	When Admin clicks the delete Icon on any row
#	Then Admin should see the confirm alert box with yes and no button
#	
#Scenario: Validate yes button on the confirm alert box
#	Given Admin is on the batch confirm popup page
#	When Admin clicks on the delete icon and click yes button
#	Then Admin should see the successful message and the batch should be deleted
#	
#Scenario: validate no button on the confirm alert box
#	Given Admin is on the batch confirm popup page
#	When Admin clicks on the delete icon and click no button
#	Then Admin should see the alert box closed and the batch is not deleted
#	
#Scenario: validate close Icon on the alert box
#	Given Admin is on the batch confirm popup page
#	When Admin clicks on the close icon in batch confirm popup
#	Then Admin should see the alert box closed 
	
	

	
		
	
 
 
    
