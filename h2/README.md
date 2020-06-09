## Spring Boot H2 Rest

#### H2 console
* http://localhost:8080/h2/

#### List all lessons for a teacher (GET)
* 200 : http://localhost:8080/registration/teacher/1/
* 404 : http://localhost:8080/registration/teacher/5/


#### List all registrations for a student (GET)
* 200 : http://localhost:8080/registration/student/6/
* 404 : http://localhost:8080/registration/student/15/


#### Create a new registration (POST)
* 200 : http://localhost:8080/registration/register?student_id=5&lesson_id=2
* 404 : http://localhost:8080/registration/register?student_id=5&lesson_id=15
* 409 : http://localhost:8080/registration/register?student_id=6&lesson_id=5

#### Grade a student (PUT)
* 200 : http://localhost:8080/registration/grade?student_id=6&lesson_id=5&grade=C
* 404 : http://localhost:8080/registration/grade?student_id=5&lesson_id=5&grade=C

#### Look for overlaps (GET)
* 200 : http://localhost:8080/overlap/teacher/6/
* 404 : http://localhost:8080/overlap/student/15/
