Feature: Working selenium test
    In order to be productive
    As a developer
    I want to have a working selenium setup

    Scenario: I navigate on the dd-demo-app
        When I go to it and click on omröstning
        Then I should see the list of omröstningar
