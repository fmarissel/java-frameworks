## Spring Boot H2 GraphQL

See GraphQLConfig for custom scalars

##### Start and open http://localhost:8080/graphiql/

###### Try some queries
```graphql
{
  lessonsByTeacher(teacherId: 3) {
    id
    name
  }
}
```
```graphql
{
  lessonsByTeacher(teacherId: 3) {
    id
    name
    subject
    teacher {
      firstName
      lastName
      personType
    }
    startAt
    duration
  }
}
```
```graphql
{
  registrationsByStudent(studentId: 6) {
    lesson {
      name,
      subject,
      startAt,
      duration
    }
    registeredAt
    grade
  }
}
```

###### Try some mutations
```graphql
mutation {
  register(studentId: 5, lessonId: 2) {
    id
    registeredAt
    grade
    lesson {
      name
      subject
      startAt
      duration
    }
  }
}
```
Grade a student
```graphql
mutation {
  grade(studentId: 6, lessonId: 5, grade: B) {
    id
    registeredAt
    grade
  }
}
```
###### Error Handling
See package exception
```graphql
{
  lessonsByTeacher(teacherId: 13) {
    id
    name
  }
}
```
```graphql
mutation {
  register(studentId: 6, lessonId: 5) {
    id
  }
}
```
```graphql
mutation {
  grade(studentId: 16, lessonId: 15, grade: B) {
    id
  }
}
```