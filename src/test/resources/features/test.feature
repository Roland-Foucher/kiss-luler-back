@Tests
Feature: Creer un utilisateur

  Scenario: Se rendre sur la page principale
    Given je lance l'explorer et je vais sur l'url de l'application
    And je suis sur la page "Home"
    When je clique sur le bouton "Créer un compte"
    Then je vois la modal "CréMule toi"

  Scenario: Créér un compte
    Given je vois la modal "CréMule toi"
    And j'ajoute le text "test@test.com" dansle champs "email"
    And j'ajoute le text "12345678" dansle champs "password"
    And j'ajoute le text "Jean" dansle champs "firstName"
    And j'ajoute le text "Paul" dansle champs "lastName"
    And j'ajoute le text "01/07/1988" dansle champs "birthdate"
    When je clique sur le bouton "Me créer un compte"
    Then la modal se ferme

  Scenario: Se connecter
    Given je clique sur le bouton "Se connecter"
    When j'ajoute le text "test@test.com" dansle champs "username"
    And j'ajoute le text "12345678" dansle champs "password"
    And je clique sur le bouton "Me Mumulecter"
    Then je vois le text "Jean Paul" dans la navbar
    And je reset la base de donnée
