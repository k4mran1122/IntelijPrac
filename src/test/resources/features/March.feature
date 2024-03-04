Feature: Tenant login

  Scenario: Invite local users
    Given User login as a tenant
    Then User clicks on invite users
    Then User invites local user
    Then Click on logout

  Scenario: Invite google users
    Given User login as a tenant
    Then User clicks on invite users
    Then User invites google user
    Then Click on logout

  Scenario: Invite microsoft users
    Given User login as a tenant
    Then User clicks on invite users
    Then User invites microsoft user
    Then Click on logout

  Scenario: Invite local users with UG
    Given User login as a tenant
    Then User clicks on invite users
    Then User invites local user with UserGroup
    Then Click on logout

  Scenario: Invite google users with UG
    Given User login as a tenant
    Then User clicks on invite users
    Then User invites google user with UserGroup
    Then Click on logout

  Scenario: Invite microsoft user with UG
    Given User login as a tenant
    Then User clicks on invite users
    Then User invites microsoft user with UserGroup
    Then Click on logout
