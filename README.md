Implement a mini application to manage a system of users.
The application will target the management of a user base that has data
main: id, name, surname, email, age. They will approach as methods: displaying a
user by id, displaying all users, entering a user, deleting a user,
name change, email change.

1. It must communicate with a sql database and must run in spring boot
   what exactly needs to be installed to be able to communicate with the database?
   what must be installed to run spring boot on the computer?

2. Details of the application tested using JUnit. What does the site/api endpoint/class do?
3. What language did you use? What IDE did you use? Which bookstores did you choose and how do they install
   these?
4. Why is unit testing of code important?
5. If you chose a certain methodology, write a few words about it. What does it represent and
   why did you choose it? What are the advantages?
6. If you have applied certain OOP principles directly or indirectly, explain their role in the application
   CREATED
7. How to use the project? How to run tests written with JUnit? How do you make sure?
   that the project has no errors?
8. You can describe the implemented JUnit test scenarios. What exactly do you want to test?
   why?
   
Recommendations:

● We must have a functional and properly written application.
● Code must be arranged (CTRL + ALT + L)
● Correctly defined variables and easy to understand code.
● We must have at least a consistent number of classes in the project. (Minimum 3 classes in which
to have functional code and coupled in the functionality of the project)
● Depending on the chosen theme, it is mandatory to have the heritage or
implementing an interface.
● Indication: Inventiveness is encouraged in the choice of methods and transpose the requirements in
practice (put yourself in the users' shoes)

Bugfixes - urgent
front-end
- when adding users in the frontend there's not last name to add -> we get error when trying to add new user

user controller
- in the backend we have functionality to change firstname userService.updateFirstName but it's not implemented in frontend
- there is not function to change the last name of a unser from the database
- there is only functionality to run all test and give the response if it failled, we need functionality to run every test individually and show passed or failled and if we run all tests we need to be able to see which test passes and which fails

tests
- test is needed for changing the last name for a user already added

