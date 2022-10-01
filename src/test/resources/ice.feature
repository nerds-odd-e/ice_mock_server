@ice
Feature: Ice

  Scenario: mock ice response
    Given ice mock server with response "ice response"
    When ice client send request
    Then ice client get server response "ice response"

  Scenario: response as structure
    Given 存在"TimeOfDay":
      | hour | minute | second |
      | 10   | 57     | 15     |
    Given ice mock server with for object "Clock"
    When ice client send get time request
    Then ice client get server response structure
    """
    : {
      hour: 10
      minute: 57
      second: 15
    }
    """

  Scenario: mock returned PrinterPrx of Clock.getPrinter
    When ice client send clock printer request "hello"
    Then ice server receive printstring "hello"

#  docker run --rm -it -e USER_NAME=user -e USER_PASSWORD=password -e PASSWORD_ACCESS=true -p 2222:2222 linuxserver/openssh-server
  Scenario: ssh file operation, only demo ssh file, not a test case
    * ssh server:
      | host      | port | user | password |
      | 127.0.0.1 | 2222 | user | password |
    When write file "/tmp/test.txt":
    """
    hello world
    """
    Then got file "/tmp/test.txt":
    """
    hello world
    """
