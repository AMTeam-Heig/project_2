<p align="center">
  <img width="200" height="200" src=src/main/webapp/assets/img/goat.png>
</p> 

![Building and publishing the Docker image](https://github.com/AMTeam-Heig/Project_01/workflows/Building%20and%20publishing%20the%20Docker%20image/badge.svg) ![tests](https://github.com/AMTeam-Heig/Project_01/workflows/tests/badge.svg)

 
### Objectif
The main goal of this project is to implement a simple version of [stack overflow](https://stackoverflow.com/) website :

### Quick start

It is realy simple to use our application, all you need is to clone the project and run the script :

First clone the project 
```bash
git clone git@github.com:AMTeam-Heig/Project_01.git
```
Then execute  the script to start the application :
```bash
./start_docker.sh
```

The application can be used over the image in the GitHub Container Registry. That offers the possibility of creating images and saving them under the GitHub Container Registry. Using this feature we created an image containing both the server (openLiberty) and the application.
All you need to test it is to run this script : 
 ```bash
docker run 9080:9080 ghcr.io/amteam-heig/project_01/stackover-goat/stackovergoat:latest
 ```
 
 ### Known issues & bugs
 We corrected a few bugs since the presentation :
 - HTML / CSS in single question page,
 - Updating the session when changing user information. 
 
 #### Tests
 
 ##### Arquillian/Integration tests
 
 Arquillian & integration tests can be run on branch fb-arquillian. This can be done by running the script ```run-integration-tests.sh```.
 
 Unfortunately, some compilation errors with maven remain thus we can't be sure these tests work.
 
 ##### Codecept
 
 The situation is similar to what was described previously (cf. Arquillian). 
 
 The scenarios are prepared but we encountered module issues with NodeJS while running them.
 
 Note that all e2e tests passed on the week preceeding the last commit.
 
 #### Comments removal
 
 Adding a comment is functionnal. However, removing them doesn't work. It seems to be related to primary keys in the database.
 

_Team : [Clarisse Fleurimont](https://github.com/Stellucidam), [Baptiste Hardrick](https://github.com/batach31), [Elodie Lagier](https://github.com/CosmicElodie) and [Walid Massaoudi](https://github.com/ChickenLivesMatter)_
