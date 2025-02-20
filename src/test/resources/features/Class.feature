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
 @TTLPH2-92
 Scenario: Validating total no of in class page
    Then Admin should see Total no of classes in below of the data table.
 