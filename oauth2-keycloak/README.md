## Spring Boot Rest API with Oauth2 & Keycloak

##### Start/Stop Keycloak

```shell script
# Start
docker run -d -p 8081:8080 --rm --name=keycloak \
-e KEYCLOAK_USER=admin \
-e KEYCLOAK_PASSWORD=admin \
-e DB_VENDOR=h2 jboss/keycloak

# Stop
docker stop keycloak
```

##### Configure Keycloak
1) Login and create a Realm (name : dev)
2) Create a client (Client ID : registration-service, Client Protocol : openid-connect)
3) WIP