#Author: padmaja
@class
Feature: Class page validation

  Background: Admin Is on the Dashboard page after login
    Given Admin is on the dashboard page after login
    When Admin clicks the Class Navigation bar in the Header

  @TTLPH2-88
  Scenario: Validating the class manage page
    Then Admin should land on the Manage class page with Title "LMS"

  @TTLPH2-89
  Scenario: Validating the Header in the Manage class page
    Then Admin should see the "Manage Class" Header

  @TTLPH2-91
  Scenario: Validating Search bar in class page
    Then Admin should see the Search Bar in Manage class page

  @TTLPH2-92
  Scenario: Validating the data table headers in the class page
    Then Admin should see the datatable heading like Batchname,class topic,class description,status,class Date,staff name,Edit/Delete

  @TTLPH2-102
  Scenario: Validating total no of in class page
    Then Admin should see Total no of classes in below of the data table.

  #Add new class popup window
  @TTLPH2-106
  Scenario: Validate Class Details Popup window
    When clicks add new class under the class menu bar
    Then Admin should see a popup open for class details with empty form along with <SAVE> and <CANCEL> button and Close(X) Icon on the top right corner of the window

  @TTLPH2-107
  Scenario: Validate input fields and their text boxes in Class details form
    When clicks add new class under the class menu bar
    Then Admin should see few input fields and their respective text boxes in the class details window

  @TTLPH2-108
  Scenario Outline: Check if class is created when only mandatory fields are entered with valid data
    Given clicks add new class under the class menu bar
    When Admin enters mandatory fields "<BatchName>" "<ClassTopic>" "<ClassDescription>" "<month>" "<date1>"  "<StaffName>" "<Status>" "<SuccessMsg>" in the form and clicks on save button
    Then Admin gets message Class added Successfully

    Examples: 
      | BatchName |  | ClassTopic |  | ClassDescription |  | month    |  | date1      |  | StaffName |  | Status |  | SuccessMsg |  |
      | SMPO33    |  | Java       |  | Core Java        |  | February |  | 02/28/2025 |  | Sarnaya   |  | Active |  | Successful |  |

  # | SMPO10     |  | @@         |  | %^U^**&          |  | December |  |    26 |  |    31 |  | Geetha Thakur |  | Inactive |  | Unsuccessful |
  @TTLPH2-151
  Scenario Outline: Check if class is created when only optional fields are entered with valid data
    Given clicks add new class under the class menu bar
    When Admin skips to add value in mandatory field and enter only the optional field "<comments>" "<notes>" "<recording>"
    Then Admin should see error message below the test field and the field will be highlighed in red color "<batchNameReqText>" "<classTopicReqText>" "<classDateReqText>" "<staffNameReqText>" "<noOfClassesReqText>"

    Examples: 
      | comments |  | notes      |  | recording |  | batchNameReqText       | classTopicReqText       | classDateReqText       | staffNameReqText       | noOfClassesReqText         |
      | Good     |  | java notes |  | goto link |  | Batch Name is required | Class Topic is required | Class Date is required | Staff Name is required | No. of Classes is required |

  @TTLPH2-100
  Scenario: Validate the sort icon of all the field in datatable
    Then Admin should see the Sort icon of all the field in the datatable.

  @TTLPH2-101
  Scenario: Validating the Delete button under the Manage class
    Then Admin should see the Delete button under the Manage class page header

  @TTLPH2-99
  Scenario: Validating the text and pagination icon in the classpage
    Then Admin should see the showing entries and enabled pagination controls under the data table

  @TTLPH2-149
  Scenario: Validate Cancel/Close(X) icon on class Details form
    Given clicks add new class under the class menu bar
    When Admin clicks Cancel Icon on class Details form
    Then Class Details popup window should be closed without saving

  @TTLPH2-150
  Scenario Outline: Empty form submission
    Given clicks add new class under the class menu bar
    When Admin clicks on save button without entering data
    Then Admin should see error message below the test field and the field will be highlighed in red color "<batchNameReqText>" "<classTopicReqText>" "<classDateReqText>" "<staffNameReqText>" "<noOfClassesReqText>"

    Examples: 
      | batchNameReqText       | classTopicReqText       | classDateReqText       | staffNameReqText       | noOfClassesReqText         |
      | Batch Name is required | Class Topic is required | Class Date is required | Staff Name is required | No. of Classes is required |

  #edit classpopup
  @TTLPH2-152
  Scenario: Validate row level edit icon
    When Admin clicks on the edit icon
    Then A new pop up with class details appears

  @TTLPH2-153
  Scenario: Check disabled  batch name
    When Admin clicks on the edit icon
    Then Admin should see batch name field is disabled

  @TTLPH2-154
  Scenario: Check disabled class topic
    When Admin clicks on the edit icon
    Then Admin should see class topic field is disabled

  @TTLPH2-155
  Scenario Outline: Check if the optional fields are updated with valid data
    Given Admin is on the Edit Class Popup window
    When Update the optional fields with valid values "<comments>" "<notes>" "<recording>" and click save
    Then Admin gets message "Updated class Successfully" and see the updated values in data table

    Examples: 
      | comments |  | notes      |  | recording |
      | Good     |  | java notes |  | goto link |

  @TTLPH2-157
  Scenario Outline: Check if the fields are updated with valid data
    Given Admin is on the Edit Class Popup window
    When Update the fields with valid data "<Class>" and click save
    Then Admin gets message "Updated class Successfully" and see the updated values in data table

    Examples: 
      | Class |
      | Java  |

  @TTLPH2-156
  Scenario: Validate Cancel button on Edit popup
    Given Admin is on the Edit Class Popup window
    When Admin clicks Cancel button on edit popup
    Then Admin can see the class details popup disappears and can see nothing changed for particular Class

  #Delete and Mulitiple delete
  @TTLPH2-158
  Scenario: Admin checks Succes delete operation with single class delete
    When Admin clicks on the delete icon on class module page
    Then Admin able to delete by clicking yes to confirmation pop up on Class module

  @TTLPH2-159
  Scenario: Admin checks delete operation unsucces with single class delete
    When Admin clicks on the delete icon on class module page
    Then Admin able to delete by clicking No to confirmation pop up on Class module

  @TTLPH2-160
  Scenario: Admin validate delete by deleting multiple classes
    When Admin clicks on the multiple checkboxes on class module page
    And Admin clicks  on the left delete button on class module page
    Then Admin able to delete multiple class by clicking yes to confirm

  #Search Box validation
  @TTLPH2-161
  Scenario Outline: Search class by Batch Name
    When Admin enter the "<field>" "<value>" in search textbox
    Then Admin should see Class details are searched by given fields

    Examples: 
      | field       |  | value       |
      | Batch Name  |  | Python101   |
      | Class Topic |  | Java        |
      | Staff Name  |  | Getha Takur |

  #----------------------------- Sorting----------------------------------------------------
  @TTLPH2-163
  Scenario: Verify sorting of Batch name in Ascending order
    When Admin clicks on Arrow next to Batch Name of Class module page for sort
    Then Admin See the Batch Name is sorted Ascending order in Class module page for sort

  @TTLPH2-164
  Scenario: Verify sorting of Batch name in Descending order
    When Admin clicks on Arrow next to Batch Name of Class module page for sort descend
    Then Admin See the Batch Name is sorted Descending order in Class module page

  @TTLPH2-165
  Scenario: Verify sorting of Class Topic in Ascending order
    When Admin clicks on Arrow next to Class Topic of Class module page for sort
    Then Admin See the Class Topic is sorted Ascending order in Class module page

  @TTLPH2-166
  Scenario: Verify sorting of Class Topic in Descending order
    When Admin clicks on Arrow next to Class Topic of Class module page for sort descend
    Then Admin See the Class Topic is sorted Descending order in Class module page

  @TTLPH2-167
  Scenario: Verify sorting of Class Description in Ascending order
    When Admin clicks on Arrow next to Class Description of Class module page for sort
    Then Admin See the Class Description is sorted Ascending order in Class module page

  @TTLPH2-168
  Scenario: Verify sorting of Class Description in Descending order
    When Admin clicks on Arrow next to Class Description of Class module page for sort descend
    Then Admin See the Class Description is sorted Descending order in Class module page

  @TTLPH2-172
  Scenario: Verify sorting of Status in Ascending order
    When Admin clicks on Arrow next to Status of Class module page for sort
    Then Admin See the Status is sorted Ascending order in Class module page

  @TTLPH2-173
  Scenario: Verify sorting of Status in Descending order
    When Admin clicks on Arrow next to Status of Class module page for sort descend
    Then Admin See the Status is sorted Descending order in Class module page
#Regression-failed
  @TTLPH2-174
  Scenario: Verify sorting of StaffName in Ascending order
    When Admin clicks on Arrow next to StaffName of Class module page for sort
    Then Admin See the StaffName is sorted Ascending order in Class module page
#Regression-failed
  @TTLPH2-176
  Scenario: Verify sorting of StaffName in Descending order
    When Admin clicks on Arrow next to StaffName of Class module page for sort descend
    Then Admin See the StaffName is sorted Descending order in Class module page

  @TTLPH2-177
  Scenario: Verify sorting of ClassDate in Ascending order
    When Admin clicks on Arrow next to ClassDate of Class module page for sort
    Then Admin See the ClassDate is sorted Ascending order in Class module page

  #@TTLPH2-178
  #Scenario: Verify sorting of ClassDate in Descending order
   # When Admin clicks on Arrow next to ClassDate of Class module page for sort descend
   # Then Admin See the ClassDate is sorted Descending order in Class module page
