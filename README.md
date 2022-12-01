# KissLulerBack

## site maven

https://roland-foucher.github.io/kiss-luler-maven-site/

## site heroku front

https://kiss-luler-front.herokuapp.com/

## site heroku back

https://kiss-luler-back.herokuapp.com

Pas de gestion des fichiers car pas de serveur de stockage des fichiers gratuit

## Analyse des besoins

**QUOI ?**

* obligatoire :
  
    *User* 
    * créer un compte
    * se connecter
    * modifier son compte
    * créer un projet
    * consulter un projet
    * Consulter ses projets
    * Investir dans un projet
    * Indiquer un montant minimal de dons
    * Définir des contreparties
    * Recevoir des contreparties <br/><br/>

    *Admin*
    * modérer les projets
    * Bloquer un user
    * Supprimer un projet
    * Mettre un projet en attente

    <br/>

  * facultative :
    * Trier les projets par catégories
    * Signaler un projet à l'équipe de modération en laissant un message
    * Pouvoir suspendre/relancer/prolonger/annuler (avec remboursement) un projet
    * Gérer les paliers supplémentaires de financement

**QUI ?**
  * client : entreprise commanditaire
  * utilisateur : 
    * administrateur
    * visiteur 
    * user connecté

**COMMENT ?**

* context final : application web.
  * techno : spring java mysql React/Typescript.
  * délai : 4 semaines effectives.

**USAGE ?**

* usage déstiner au grand public, sensible à la musique.
  * usage destiné aux professionnels administrateurs du site.


## installation 

* Au lancement de l'application backend, les bdd kiss-luler-back et kiss-luler-back-test sont crées
* Réimporter les fichier schema.sql (pour obtenir les trigger et procédures) et le fichier data.sql dans src/main/resources
* Possibilité de se connecter avec le compte `j.jean@gmail.com`  // mdp : `1234`


## Compétences visées détail

* Ajout d'un *trigger* en bdd sur la table `update` : modification du champs `last_update_date` avec la date du jour si le projet est modifié en bdd
* Ajout d'une *procédure stockée* en bdd sur la table `user` : fonction qui passe le champ `last_connection` avec la date du jour sur la ligne de l'id communiquée (implémenté via le repository JPA)
* Ajout de tests unitaires, tests d'intégration, test fonctionnels MockMvc et test fonctionnels avec selenium.
* Application backend organisée de la façon suivante :
  * couche *library* : contient les entités JPA, les repository, les enum, les DTO, les exceptions personnalisés.
  * couche *buisness* : contient les services métiers, les services techniques et les règles de sécuritée de l'application (JWT, authentification)
  * couche *webApp* : contient la configuration de l'application et les controlleurs


## Objectif scrum 1 (semaine 13/06 - 17/06)

* Définir les besoins
* Faire un Use Case
* Faire un diagramme d'entité
* Développer les entités et les repository JPA
* Pouvoir afficher une liste de Projet sur la page principale
* Pouvoir connecter un utilisateur
* Pouvoir afficher les projets de l'utilisateur sur sa page

## Retex semaine 1

def: Le retour d’expérience - RETEX est avant tout, une méthode, un outil. Et comme son nom l’indique, nous permet de faire un retour sur une situation vécue par nous-mêmes ou par quelqu’un d’autre.

Le retour positif est notre capacité à communiquer et notre organisation sur gitlab ce qui nous permet une bonne assignation des tâches et un suivi régulier. 

Cela nous a permis de réaliser que les objectifs que nous nous étions fixé pour cette semaine étaient trop élévé, nous avons réorganiser le travail afin de répartir les tâches en incluant la semaine suivante.

## Objectif scrum 2 (semaine du 11/07 - 15/07)

* Pouvoir afficher une liste de Projet sur la page principale
* Pouvoir connecter et ajouter un utilisateur
* Pouvoir afficher les projets de l'utilisateur sur sa page
* Diagramme d'entité à continuer
* Modifier le profil utilisateur
* Uploader

## Retex semaine 2
L'application à bien avancée, les objectifs ont été tenues pour cette semaine.

L'application backend à besoin d'être restructurée pour être plus lisible et plus maintenable

## Objectif scrum 3 (semaine du 08/08 - 12/08)

* Ajouter modifier supprimer un projet
* Ajouter modifier supprimer une contribution
* Ajouts de données pour l'utilisateur
* Ajout d'un trigger et d'une procédure en bdd
* Refaire le style de l'application
* Restructuration de l'application back

## Retex semaine 3

L'équipe n'était pas au complet en présentiel, la communication a donc été moins importante.

Les objéctifs ont été tenues malgrès tout. Reste à merger la totalité des branch développées


## Objectif scrum 4 (semaine du 05/09 - 09/08)

* Acheter une contribution
* Finalisation du projet
* Finalisation du diagramme de classe
* Création d'un site maven pour la déscription du projet (javadoc, readme, conception, ...)

## Retex semaine 4

Nous avons eu besoin de remettre l'outil de travail au propre, car nous avions tous beaucoup avancé chacun sur nos branches.

Nous avons pu nous rendre compte que nous aurions dû plus assigner les tâches de manière plus précise, car nous avons rencontré des difficultés lors des merges request.
Le projet, dans sa globalité, est quasiment terminé.

Les objectifs de la semaine sont atteints, il nous reste à cleaner le code, et mettre en place des tests (Sélénium/Cucumber/Artillery si possible...etc...)

