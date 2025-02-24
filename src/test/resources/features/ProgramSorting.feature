#Author: Maya SDET185
@ProgramSortingFeature
Feature: Program Sorting Module

  Background: 
    Given Admin is logged in to LMS Portal

  @TC1 @ProgramSorting
  Scenario: Verify sorting of  Program name in ascending order
    Given Admin is on Program page
    When Admin clicks on Arrow next to Program Name of Program module page for sort ascending
    Then Admin See the Program Name is sorted in ascending order

  @TC2 @ProgramSorting
  Scenario: Verify sorting of Program name in Descending order
    Given Admin is on Program page
    When Admin clicks on Arrow next to Program Name of Program module page for sort descend
    Then Admin See the Program Name is sorted in descending order

  @TC3 @ProgramSorting
  Scenario: Verify sorting of Program Description in Ascending order
    Given Admin is on Program page
    When Admin clicks on Arrow next to program description of Program module page for sort ascending
    Then Admin See the program description is sorted Ascending order in Program module page

  @TC4 @ProgramSorting
  Scenario: Verify sorting of Program Description in Descending order
    Given Admin is on Program page
    When Admin clicks on Arrow next to program description of Program module page for sort descending
    Then Admin See the program description is sorted Descending order in Program module page

  @TC5 @ProgramSorting
  Scenario: Verify sorting of Program Status in Ascending order
    Given Admin is on Program page
    When Admin clicks on Arrow next to program Status of Program module page for sort ascending
    Then Admin See the program Status is sorted Ascending order in Program module page

  @TC6 @ProgramSorting
  Scenario: Verify sorting of Program Status in Descending order
    Given Admin is on Program page
    When Admin clicks on Arrow next to program Status of Program module page for sort descending
    Then Admin See the program Status is sorted descending order in Program module page
