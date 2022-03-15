Feature: Ice

  Scenario: mock ice response
    Given ice mock server
    When ice client send request
    Then ice client get server response