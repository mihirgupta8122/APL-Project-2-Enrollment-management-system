# CMPP 3020 Assignment 2 - Variables, Types, Statements and Subprograms
## By Group 3, CMPP 3020 BSA, Fall 2025 - BTech
### Jazmin, Mihir, Nathan, Umaya

## Overview
This application is a console based student enrollment system for SAIT, written in both Java and Python. It allows the user to manage student records by adding, displaying, modifying, and removing them.

The core requirement was to build the exact same application in both Java and Python. This provides a way for us to dorectly comapred both languages when they are programmed to do the exact same thing, focusing on key concepts covered in class such as:

1. Static vs. Dynamic Data Types and Type Binding

2. Modular Subprogram (Functions/Methods) Design

3. Variable Scoping (Block-level vs. Function-level)

4. Referencing and Argument Passing (Pass-by-value vs. Pass-by-object-reference)

## Part 2: Using a Programming Language (Java)
Our Java implementation is built inside of a single EnrollmentSystem class. It follows a modular style while still following Java's class based, object oriented structure.

1. Static Typing: All variables, parameters, and return types are specifically declared (e.g., List<StudentRecord>, int, String). This helps provide type safety at compile time.

2. Class-Based Design: All of the methods and "global" variables are static, belonging to the EnrollmentSystem class.

3. Argument Passing: To handle the auto incrementing next_student_id, we use a custom IntRef wrapper class. This allows us to pass the integer by reference and change its value from inside a method, a classic Java solution to its "pass by value" nature.

4. Input Handling: A single, static Scanner object is used for all user input inside the console.

5. Error Handling: try/catch (NumberFormatException) blocks are used in all validation helpers to ensure type safe inputs.

## Part 3: Writing the Program in Python
Our Python implementation is built modular with standalone functions. It's functionally identical to the Java version, but is implemented using Python features.

1. Dynamic Typing: No data types are declared. Types are inferred at run-time, offering flexibility but requiring strong validation.

2. Function-Based Design: The program is structured as a series of helper and core functions, with a main() function as the  entry point to the main application loop.

3. Argument Passing: Instead of a wrapper, the add_student function uses a more functional approach. It returns the new, incremented next_student_id, which is then reassigned by the caller in main.

4. Input Handling: The built in input() function is used for all console inputs.

5. Error Handling: try/except (ValueError) blocks are used in the validation helpers to catch invalid user inputs.

### How to Run
First, please clone this repository.

## Java
You will need a Java JDK (Version 8 or higher) installed.
Navigate to the directory containing the Java file.

Compile the program:
javac EnrollmentSystem.java

Run the program:
java EnrollmentSystem

## Python
You will need a Python 3 interpreter installed.
Navigate to the directory containing the Python file.

Run the program:
python enrollment_system.py
(Note: You may need to use python3 depending on your system's configuration)

### Output Preview
Both programs have an identical console interface. The program starts, displays the main menu, and waits for user input.
([Output preview](<Screenshot 2025-11-07 210722.png>))

## Built With

(Java Version 8 or above.(https://www.oracle.com/ca-en/java/technologies/javase/javase8-archive-downloads.html)


[Python 3.13.7](https://www.python.org/downloads/release/python-3137/) (Python Version)
