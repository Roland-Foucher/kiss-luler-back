# KissLulerBack

## Objectif scrum 1 (semaine 13/06 - 17/06)

* Définir les besoins
* Faire un Use Case
* Faire un diagramme d'entité
* Développer les entités et les repository JPA
* Pouvoir afficher une liste de Projet sur la page principale
* Pouvoir connecter un utilisateur
* Pouvoir afficher les projets de l'utilisateur sur sa page

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

