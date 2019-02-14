Feature: Adding parents

  Scenario: Adding parents with bull younger than 2 years old
    Given Young bull
    When Adding parents to cow
    Then Exception is raised when adding parents

  Scenario: Adding parents with cow younger than 2 years old
    Given Young cow
    When Adding parents to cow
    Then Exception is raised when adding parents

  Scenario: Adding parents with cow that gave birth within last 10 months
    Given Cow that had given birth within last 10 months
    When Adding parents to cow
    Then Exception is raised when adding parents

  Scenario: Adding parents with cow that gave birth to twins
    Given Cow that had given birth to twins
    When Adding parents to cow
    Then Parents are added

  Scenario: Adding valid parents
    Given Valid cow and bull
    When Adding parents to cow
    Then Parents are added