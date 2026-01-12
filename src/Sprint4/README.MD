# Todo Manager Sprint 4

--------------------------
## Liberties Taken
- Used Scanner for user input.
- Using ArrayList instead of standard Array.
- Searching for a task is based on exact matching of the description,
  returns first task that satisfies the equals check.
- User registration includes simple "Client" or "Visitor" role self assignment.
- All Visitors still have their own usernames to assign tasks specifically to.
Specifically, there isn't a global "visitor" name.
- Menu looks a bit bloated but a lot of it is Menu related, such as menu conditionals, 
and menu print statements.
---------------------------

## Problem Statement
Todo Manager is an application which can Manage our Tasks and keep track of our Tasks. In the course of 3 week we will be creating a Todo manager.
## User Stories
1. As a user I should be able to register and login to the application.
2. As a user I can be either the client or visitor.
3. AS a visitor i can only see the tasks assigned to me
4. As a client I should be able to add the task into an array
5. As a client I should be able to update the task in the array
6. As a client I should be able to delete the task in the array
7. As a client I should be able to search a task from an array
8. As a client I should be able to assign the task to a visitor.
## Instructions:-
1. Please refactor the code created in spring 3.
2. Please create a user as a parent class and client and visitor should be child classes.
3. Please use arrays to store the user objects
4. All the CRUD operations should happen in a dao class. Main should contain only menu.
5. please use userDAO and taskDAO interfaces and userDAOimpl and taskDAOImpl as classes implements the interface
6. The application can be exited only if user enters 0 to exit. Else user should see the menu again and again.
7. Estimated completion time of the challenge will be 2hr.
8. Make sure you are uploading the code into git and giving the access to your mentor.
