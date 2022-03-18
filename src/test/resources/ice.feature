Feature: Ice

  Scenario: mock ice response
    Given ice mock server with response "ice response"
    When ice client send request
    Then ice client get server response "ice response"