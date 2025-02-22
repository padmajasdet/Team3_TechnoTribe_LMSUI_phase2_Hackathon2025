@ProgramPaginationFeature
Feature: Program Module

  Background: 
    Given Admin is logged in to LMS Portal

  @TC1 @ProgramPagination
  Scenario: Verify navigation to Program page for Admin
    Given Admin is on Program page
    When Admin clicks Next page link on the program table
    Then Admin should see the Pagination has "Next" active link
    
    
