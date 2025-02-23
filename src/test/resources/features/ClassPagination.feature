#Author: Maya SDET185
@ClassPaginationFeature
Feature: Class Pagination Module

  Background: Admin Is on the Dashboard page after login
    Given Admin is on the dashboard page after login
    When Admin clicks the Class Navigation bar in the Header

  @TC1 @ClassPagination
  Scenario: Verify Admin is able to click Next page link
    When Admin clicks Next page link on the class table
    Then Admin should see the next page record on the table  with Pagination has next active link enabled

  @TC2 @ClassPagination
  Scenario: Verify Admin is able to click Next page link
    When Admin clicks Last page link of class data table
    Then Admin should see the last page record on the table with Next page link are disabled for class data table

  @TC3 @ClassPagination
  Scenario: Verify Admin is able to click Next page link
    When Admin clicks next page link of class data table
    Then Admin should see the previous page record on the table with pagination has previous page link enabled for class data table

  @TC4 @ClassPagination
  Scenario: Verify Admin is able to click Next page link
    When Admin clicks Start page link of class data table
    Then Admin should see the very first page record on the table with Previous page link are disabled for class data table
