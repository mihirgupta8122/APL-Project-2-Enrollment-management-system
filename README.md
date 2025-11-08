# CMPP 3020 Assignment 2 - Variables, Types, Statements and Subprograms
## By Group 3, CMPP 3020 BSA, Fall 2025 - BTech
### Jazmin, Mihir, Nathan, Umaya

## Overview
This application is a console based student enrollment system for SAIT, written in both Java and Python. It is suppose to allow the user to manage multiple student records by adding students, displaying all students on record, modifying a student's information, and removing them from the list.

By coding the same scenario in two different languages, it gives us an easy way to compare both of the languages when they are programmed to do the exact same thing, we were focusing on key concepts that were covered in class like:

1. Static vs. Dynamic Data Types and Type Binding

2. Modular Subprogram (Functions/Methods) Design

3. Variable Scoping (Block level vs. Function level)

4. Referencing and Argument Passing (Pass by value vs. Pass by reference)

## Part 2: Using a Programming Language (Java)
Our Java implementation is built inside of a single EnrollmentSystem class. It follows a modular style while still following Java's class based, object oriented structure.

1. Static Typing: All of the variables, parameters, and return types are specifically declared (ex, List<StudentRecord>, int, and String). Doing this helps to provide type safety during compile time.

2. Class-Based Design: All of the methods and "global" variables are static, belonging to the EnrollmentSystem class.

3. Argument Passing: To handle the automatic incrementing of the 'next_student_id', we used a custom IntRef wrapper class. By doing this, we were able to pass the integer by reference and change its value from inside of a method, which we used as solution to its "pass by value" nature.

4. Input Handling: A Scanner object is used for allof the user inputs inside the console.

5. Error Handling: The try/catch (NumberFormatException) blocks are what we used in all of the validation helper functions to make sure we have inputs that were following the data types that were pre defined and to prevent type errors.

## Part 3: Writing the Program in Python
Our Python implementation is built with the intention to be very modular with functions that are standalone. It's functionally the exact same to the Java version, but just in python.

1. Dynamic Typing: There are no data types that were declared in the code. Instead, types are deduced during the run time. This offers flexibility but also ends up requiring strong validation.

2. Function Based: The program is built around the use of many different helper and core functions we created, along with a main() function that acts as the door to the main application.

3. Argument Passing: Instead of a wrapper like we had to use in Java, the add_student function let us do a more functional approach. It returns the new, incremented next_student_id, which is then reassigned by the caller in the main function.

4. Input Handling: The built in input() function is what was used for all of the user inputs.

5. Error Handling: The try/except (ValueError) blocks are what we used in all of the validation helpers to catch any invalid user inputs.

### How to Run
First, please clone this repository.

## Java

You will need a Java JDK (Version 15 or higher) installed.
Navigate to the directory with the Java file in it.

Compile the program:
javac EnrollmentSystem.java

Run the program:
java EnrollmentSystem

## Python

You will need a Python 3 interpreter installed.
Navigate to the directory containing the Python file.

Run the program:
python enrollment_system.py
(Note: You might need to use python3 depending on your system's configuration)

### Output Preview
Both programs have an identical look to the console The program starts, displays the main menu, and waits for user input.
![Output preview](<Screenshot 2025-11-07 210722.png>)

## Built With

[Java 15 and up](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html) (Java Version)


[Python 3.13.7](https://www.python.org/downloads/release/python-3137/) (Python Version)
