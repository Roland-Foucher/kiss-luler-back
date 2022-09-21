@Tests
Feature: Modifier un utilisateur

  Scenario: Se rendre sur la page utilisateur
    Given je lance l'explorer et je vais sur l'url de l'application
    And je suis sur la page "Home"
    When je clique sur le bouton "Se connecter"
    And j'ajoute le text "j.jean@gmail.com" dansle champs "username"
    And j'ajoute le text "1234" dansle champs "password"
    And je clique sur le bouton "Me Mumulecter"
    Then je vois le text "Jean Paul" dans la navbar
    When je clique sur le bouton "Jean Paul"
    And je clique sur le lien "Mon Compte"
    Then je suis sur la page "Account"

  Scenario: Modifier le profil
    Given je suis sur la page "Account"
    When je clique sur le bouton "Modifier"
    Then je suis sur la page "Profil"
    When j'ajoute le text "Boulanger" dansle champs "job"
    And j'ajoute le text "JJ" dansle champs "pseudo"
    And je clique sur le bouton "Mulifier mon profil"
    Then je suis sur la page "Account"
    And je vois le text "Boulanger" dans la card user
    And je vois le text "JJ" dans la card user
