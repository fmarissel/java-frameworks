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

# Show collections
> show collections

# Show data
> db.readme.find()
```

#### Test with Postman

###### List all lessons for a teacher (GET)
* 200 : http://localhost:8080/registration/teacher?email=jean-michel.history@yopmail.com
* 404 : http://localhost:8080/registration/teacher?email=jean-michel.geography@yopmail.com


###### List all registrations for a student (GET)
* 200 : http://localhost:8080/registration/student?email=arthur.dent@yopmail.com
* 404 : http://localhost:8080/registration/student?email=rick.deckard@yopmail.com


###### Create a new registration (POST)
* 200 : http://localhost:8080/registration/register?student_email=joe.chip@yopmail.com&lesson_code=M1
* 404 : http://localhost:8080/registration/register?student_email=joe.chip@yopmail.com&lesson_code=M15
* 409 : http://localhost:8080/registration/register?student_email=joe.chip@yopmail.com&lesson_code=M3

###### Grade a student (PUT) (with MongoTemplate instead of MongoRepository)
* 200 : http://localhost:8080/registration/grade?student_email=joe.chip@yopmail.com&lesson_code=M3&grade=B
* 404 : http://localhost:8080/registration/grade?student_email=joe.chip@yopmail.com&lesson_code=E1&grade=E

###### Check the updated registration
```shell script
db.registration.find({ grade: "B" })
```
