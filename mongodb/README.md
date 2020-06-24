## Spring Boot MongoDB

#### Start/Stop MongoDB

```shell script
# Start
docker run -d -p 27017-27019:27017-27019 --rm --name=mongodb mongo

# Stop
docker stop mongodb
```

```shell script
# Connect to the container
docker exec -it mongodb bash

# Launch MongoDB shell client
mongo

# Create database test_mongo
> use test_mongo
> db.readme.insert({"name":"Test spring boot with mongoDB"})

# List databases
> show dbs

# Show data
> db.readme.find()
```