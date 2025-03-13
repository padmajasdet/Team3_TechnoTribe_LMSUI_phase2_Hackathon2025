#Author: Mathumathi Balakrishnan

@batch 
Feature: Batch Page

Background:
Given Admin is logged in to LMS Portal

#--------------------------Batch Page Navigation----------------------------#

@TCB1 @batchPageValidation
Scenario: Verify Admin Navigate to Batch page successfully
  When Admin clicks "Batch" on the navigation bar
  Then Admin should be in the Manage Batch Page

#--------------------------Batch Page Validation----------------------------# 

@TCB2 @batchPageValidation
Scenario: Validate "Title" in Batch Page
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the "LMS - Learning Management System" Title

@TCB3 @batchPageValidation @smoke
Scenario: Validate "heading" in the Batch Page
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the "Manage Batch" Heading

@TCB4 @batchPageValidation
Scenario: Validate disabled "Delete Icon" under the header in the Batch Page
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the disabled "Delete Icon" under the header

@TCB5 @batchPageValidation
Scenario: Validate pagination in the Batch Page
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the enabled pagination controls under the data table

@TCB6 @batchPageValidation
Scenario: Validate edit icon in each data rows
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the edit icon in each row

@TCB7 @batchPageValidation
Scenario: validate delete icon in each data rows
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the delete icon in each row

@TCB8 @batchPageValidation
Scenario: validate checkbox in each data rows
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the checkbox in each row

@TCB9 @batchPageValidation
Scenario: Validate Datatable headers
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the datatable headers Batch name, Batch Description,Batch Status, No Of classes, Program Name, Edit/Delete

@TCB10 @batchPageValidation
Scenario: Validate "Checkbox" in the Datatable header row
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the checkbox  in the datatable header row

@TCB11 @batchPageValidation
Scenario: Validate "sort icon" next to all the datatable header
	When Admin clicks "Batch" on the navigation bar
	Then Admin should see the sort icon next to all Datatable headers

#----------------------------Add New Batch-------------------------------#

@TCB12 @addBatchPageValidation
Scenario: Validate Admin able to click on the Add new Batch Option
	When Admin clicks on "Add New batch" under the "batch" menu bar
	Then Admin should see the Batch Details pop up window

#-------------------------Add New Batch details pop up-------------------#

@TCB13 @addBatchPageValidation
Scenario: Validate all the fields exist in pop up 
	Given Admin is on the Batch Details Pop Up WIndow
	Then The pop up should include the fields Batch Name,Number of classes and Description as text box,Program Name as drop down Status as radio button

@TCB14 @addBatchPageValidation 
Scenario: Validate batchname prefix selected program name
	Given Admin is on the Batch Details Pop Up WIndow
	When Admin selects program name present in the dropdown
	Then Admin should see selected program name in the batch name prefix box

@TCB15 @addBatchPageValidation
Scenario Outline: Validate batch name suffix box should accept only numbers
	Given Admin is on the Batch Details Pop Up WIndow
	When Admin enters "<non-numeric>" values in batch name suffix box
	Then Admin should get error message below the text box of respective field	
	Examples:
	|non-numeric|
	|invalid|
	|	@#$%^ |
	|a123		|
	|1234567|
	|-123		|
	| 12.34	|

@TCB16 @addBatchPageValidation
Scenario: Validate batch name prefix box is not editable
	Given Admin is on the Batch Details Pop Up WIndow
	When Admin enters alphabets in batch name prefix box
	Then Admin should see empty text box

@TCB17 @addBatchPageValidation @smoke
Scenario: Validate input data only for mandatory fields
	Given Admin is on the Batch Details Pop Up WIndow
	When Admin enters the data only to the mandatory fields and clicks save button
	Then Admin should get a successful message

@TCB18 @addBatchPageValidation
Scenario Outline: validate input data missing for mandatory fields
	Given Admin is on the Batch Details Pop Up WIndow
	When Admin leaves blank one of the "<mandatoryFields>"
	Then Admin should get a "<errorMessage>" on the respective mandatory field
	Examples:
	|			mandatoryFields 				|	 		errorMessage						|
	|missingProgramName						|Program Name is required.		|
	|missingBatchName							|Batch Name is required.			|
	|missingBatchDescription			|Batch Description is required.|
	|missingNumberOfClasses				|Number of classes is required.|
	
@TCB19 @addBatchPageValidation @smoke
Scenario: validate save button in Batch details pop up
	Given Admin is on the Batch Details Pop Up WIndow
	When Admin enters the valid data to all the fields and click save button 
	Then Admin should get a successful message

@TCB20 @addBatchPageValidation
Scenario: validate cancel button in Batch details pop up
	Given Admin is on the Batch Details Pop Up WIndow
	When Admin enters the valid data to all the mandatory fields and click cancel button 
	Then Admin can see the batch details popup closes without creating any batch

@TCB21 @addBatchPageValidation
Scenario: validate close icon on the batch details pop up
	Given Admin is on the Batch Details Pop Up WIndow
	When Admin clicks on the close icon
	Then batch details pop up closes

#----------------------------Edit icon Validation-------------------------------#

@TCB22 @editBatchPageValidation
Scenario: Validate Edit icon feature in any row
	Given Admin is on the Batch page
	When Admin clicks the edit icon
	Then Admin should see the Batch details pop up window in edit

@TCB23 @editBatchPageValidation
Scenario: Validate program name  value is disabled to edit
	Given Admin is on the Batch page
	When Admin clicks the edit icon
	Then Admin should see Program name value field is disabled for editing

@TCB24 @editBatchPageValidation
Scenario: Validate batch name  value is disabled to edit
	Given Admin is on the Batch page
	When Admin clicks the edit icon
	Then Admin should see batch name value field is disabled for editing

@TCB25 @editBatchPageValidation
Scenario: Validate editing description and No. of classes fields with invalid data in the pop up
	Given Admin is on the Batch Details Page
	When Admin Updates any fields with invalid data and click save button
	Then Admin should get a error message under the respective field

@TCB26 @editBatchPageValidation @smoke
Scenario: validate save button in Batch details pop up
	Given Admin is on the Batch Details Page
	When Admin edit the valid data to all the mandatory fields and click save button 
	Then Admin should get a successful message for editing the batch

@TCB27 @editBatchPageValidation
Scenario: validate cancel button in Batch details pop up
	Given Admin is on the Batch Details Page
	When Admin edit the valid data to all the mandatory fields and click cancel button 
	Then Admin can see the batch details popup closes without editing the batch

#--------------------------Search Text box validation-------------------------------#

@TCB28 @searchValidation @smoke
Scenario: validate search box functionality
	Given Admin is on the Batch page
	When Admin enters the batch name in the search text box
	Then Admin should see the filtered batches in the data table

#-----------------------------Delete batch validation--------------------------------#

@TCB29 @deletePageValidation
Scenario: validate delete Icon on any row
	Given Admin is on the Batch page
	When Admin clicks the delete Icon on any row
	Then Admin should see the confirm alert box with yes and no button

@TCB30 @deletePageValidation
Scenario: Validate yes button on the confirm alert box
	Given Admin is on the batch confirm popup page
	When Admin clicks on the delete icon and click yes button
	Then Admin should see the successful message and the batch should be deleted

@TCB31 @deletePageValidation
Scenario: validate no button on the confirm alert box
	Given Admin is on the batch confirm popup page
	When Admin clicks on the delete icon and click no button
	Then Admin should see the alert box closed and the batch is not deleted

@TCB32 @deletePageValidation
Scenario: validate close Icon on the alert box
	Given Admin is on the batch confirm popup page
	When Admin clicks on the close icon in batch confirm popup
	Then Admin should see the alert box closed 

@TCB33 @deletePageValidation
Scenario: Validate single row delete with checkbox
	Given Admin is on the Batch page
	When Admin clicks on the delete icon under the Manage batch header
	Then Selected batches should get deleted

@TCB34 @deletePageValidation
Scenario: Validate multiple row delete with checkbox
	Given Admin is on the Batch page
	When Admin clicks on the delete icon for multiple row under the Manage batch header
	Then Selected batches should get deleted
	
#---------------------------------Pagination done by MAYA---------------------------------------#

@TCB35 @paginationValidation
Scenario: Validate next page link
  Given Admin is on the Batch page
  When Admin clicks next page link on the data table
  Then Admin should see the Next enabled link

@TCB36 @paginationValidation
Scenario: validate last page link
  Given Admin is on the Batch page
  When Admin clicks last page link on the data table
  Then Admin should see the last page link with next page link disabled on the table

@TCB37 @paginationValidation
Scenario: validate the previous page link
  Given Admin is on the Batch page
  When Admin clicks previous page link on the data table
  Then Admin should see the previous page on the table

@TCB38 @paginationValidation
Scenario: validate the first page link
  Given Admin is on the Batch page
  When Admin clicks first page link on the data table
  Then Admin should see the very first page on the data table

#------------------------------------Sorting--------------------------------------------#

@TCB39 @sortingValidation @smoke
Scenario Outline: Verify sorting of Manage Batch datatable in ascending order
  Given Admin is on the Batch page
  When Admin clicks on Arrow next to "<columnName>" of Batch module page for sort ascending
  Then Admin should see the sorted list for "<columnName>"
 Examples:
 | 		columnName	 	|
 |Program Name			|
 |Batch Name				|
 |Batch Description	|
 |Batch Status			|
 |No Of Classes			|
 

 @TCB40 @sortingValidation @smoke
 Scenario Outline: Verify sorting of Manage Batch datatable in descending order
  Given Admin is on the Batch page
  When Admin clicks on Arrow next to "<columnName>" of Batch module page for sort descending
  Then Admin should see the sorted list for "<columnName>"
 Examples:
 | 		columnName	 	|
 |Program Name			|
 |Batch Name				|
 |Batch Description	|
 |Batch Status			|
 |No Of Classes			|
    
#----------------------------LogOut of the application from the batch page--------------------#

@TCB41 @logoutValidation
Scenario: Validate logout option in the header is visible and enabled from the batch page
	Given Admin is on the Batch page for logout
	When Admin clicks "logout" on the navigation bar
	Then Admin should be redirected to login page
	


	

	
		
	
 
 
    
