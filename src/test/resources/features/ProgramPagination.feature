@ProgramPaginationFeature
Feature: Program Pagination Module

  Background: 
    Given Admin is logged in to LMS Portal

  @TC1 @ProgramPagination
  Scenario: Verify navigation to Program page for Admin
    Given Admin is on Program page
    When Admin clicks Next page link on the program table
    Then Admin should see the Pagination has "Next" active link
    @TC2 @ProgramPagination
  Scenario: Verify navigation to Program page for Admin
    Given Admin is on Program page
    When Admin clicks Last page link
    Then Admin should see the last page record on the table with Next page link are disabled
    
    
