# DurakOnline - Web Version

DurakOnline is a multiplayer web-based implementation of the card game "Durak".

## Quick start
* **Run docker container**
 
      
      docker-compose up -d
  or  
      
      docker run --rm --name pg-docker \
      -e POSTGRES_PASSWORD=pwd \
      -e POSTGRES_USER=usr \
      -e POSTGRES_DB=demoDB \
      -p 5430:5432 \
      postgres:13
* **Run App**
* **Open** http://localhost:8080/room/1

### Setting
