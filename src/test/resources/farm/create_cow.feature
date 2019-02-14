Feature: Create Cow

  Scenario: Creating valid Cow object
    Given Cow properties without errors
    When Creating Cow Object
    Then Cow Object is returned

  Scenario: Creating invalid Cow object
    Given Cow properties with invalid date
    When Creating Cow Object
    Then Exception is raised when creating cow