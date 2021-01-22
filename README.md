<p align="center">
  <img width="200" height="200" src=src/main/webapp/assets/img/goat.png>
</p> 

![Building and publishing the Docker image](https://github.com/AMTeam-Heig/Project_01/workflows/Building%20and%20publishing%20the%20Docker%20image/badge.svg) ![tests](https://github.com/AMTeam-Heig/Project_01/workflows/tests/badge.svg)

 
### Objectif
Le but principal de ce projet est d'implémenter une version simplifiée du site Stack Overflow. 
Il nous a également été demandé de réaliser un moteur de Gamification qui s'occupera d'octroyer certaines entités (comme des badges) et de gérer des événements.
Par exemple : ajouter un commentaire ou une question et réagir à ces derniers. 


### Démarrage
#### Prérequis
La version fonctionnelle de ce repo se trouve sur la branch main.
L'implémentation est optimisée pour Windows. Nous n'avons pas pu le tester sur Linux, cependant les variables d'environnement sont censées s'adapter d'elles-mêmes. 
Ce projet tourne sur Java 11.0.8


#### Étapes
1. Cloner le projet :
```bash
git clone git@github.com:AMTeam-Heig/project_2.git
```

2. Exécuter le script suivant, à la racine : 
```bash
./start_docker.sh
```

Optionnel : 
Cette application peut être utilisée avec une image Docker. Il est possible de modifier le moteur de Gamification en allant sur ce repository : 
https://github.com/AMTeam-Heig/Gamification-Engine

et le push sur une image Docker, puis modifier le docker-compose.

 
 ### Problèmes et bugs connus
 - Problème d'affichage des questions / réponses qui sortent du cadre.
 - La page d'administration ne fonctionne pas.
 - Certaines commandes sont nécessaires afin de purger la base de données;
 En effet, une application "StackOvergoat" est créée lors du lancement du projet. Si la base de données n'est pas purgée, il est possible que deux instance soient créées et que cela créé des conflits. 
 Un ```docker system prune``` et ```docker volume prune``` sont donc nécessaires (pas sécurité) avant chaque lancement de l'application.
 
 ### Choix d'implémentation
 #### openAPI tools
1. Les classes liant notre application à la base de données du Gamification-engine sont générées automatiquement dans le pom.xml grâce à openAPI-tools lors du mvn clean install. 

2. La classe GamificationFacade du package application.gamification créé le lien avec  la base de données grâce à la classe DefaultAPI.
DefaultAPI est une classe Singleton, qui permet d'instancier un unique lien entre l'application et le moteur, afin d'éviter un surplus de connexions entre ces deux entités. 
 
3. La classe GamificationQuery implémente les fonctions générées par openAPI-tools et effectue les requêtes vers la base de données du Gamification-Engine.

4. Les règles et les badges sont créés dans la classe GamificationFacade et sont générées depuis son constructeur, qui sera appelé dans la fonction ServiceRegistry.initFacade.

#### Exemple d'utilisation query-event
Lorsque l'on créé un nouvel utilisateur dans la classe RegisterCommandEndpoint, un utilisateur sera également généré dans la base de données du Gamification Engine via la fonction GamificationQuery.createUser. 
Un évènement est trigger lors d'une requête POST (typiquement dans QuestionCommandEndpoint du package ui.web.question). Un événement sera créé et on utilisera la fonction GamificationQuery.createEvent.
Ce sera ainsi le Gamification Engine qui s'occupera de gérer l'évènement selon les règles spécifiées. 

### Tests
Les tests se trouvent sur le repository Gamification Engine.
```
https://github.com/AMTeam-Heig/Gamification-Engine
```

Des tests unitaires existent dans le repo du projet 1. Nous trouvions inutile car la structure est trop complexe pour faire des tests unitaires liés au Gamificiation Engine et à l'application via une base de données externe.
 

_Team : [Clarisse Fleurimont](https://github.com/Stellucidam), [Baptiste Hardrick](https://github.com/batach31), [Elodie Lagier](https://github.com/CosmicElodie) and [Walid Massaoudi](https://github.com/ChickenLivesMatter)_
