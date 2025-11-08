# CMPP 3020 Assignment 2 - Variables, Types, Statements and Subprograms
## By Group 3, CMPP 3020 BSA, Fall 2025 - BTech
### Jazmin, Mihir, Nathan, Umaya

## Overview
This application is a console-based student enrollment system for SAIT, written in both Java and Python. It allows the user to manage student records by adding, displaying, modifying, and removing them.

The core requirement was to build the exact same application in both Java and Python. This provides a direct, hands-on comparison of the two languages, focusing on key concepts covered in class such as:

Static vs. Dynamic Data Types and Type Binding

Modular Subprogram (Functions/Methods) Design

Variable Scoping (Block-level vs. Function-level)

Referencing and Argument Passing (Pass-by-value vs. Pass-by-object-reference)

## Part 2: Using a Programming Language (Java)
Our Java implementation is built inside of a single EnrollmentSystem class. It follows a modular style while still following Java's class based, object oriented structure.

Static Typing: All variables, parameters, and return types are specifically declared (e.g., List<StudentRecord>, int, String). This helps provide type safety at compile time.

Class-Based Design: All of the methods and "global" variables are static, belonging to the EnrollmentSystem class.

Argument Passing: To handle the auto incrementing next_student_id, we use a custom IntRef wrapper class. This allows us to pass the integer by reference and change its value from inside a method, a classic Java solution to its "pass by value" nature.

Input Handling: A single, static Scanner object is used for all user input inside the console.

Error Handling: try/catch (NumberFormatException) blocks are used in all validation helpers to ensure type safe inputs.

## Part 3: Writing the Program in Python
Our Python implementation is built modular with standalone functions. It's functionally identical to the Java version, but is implemented using Python features.

Dynamic Typing: No data types are declared. Types are inferred at run-time, offering flexibility but requiring strong validation.

Function-Based Design: The program is structured as a series of helper and core functions, with a main() function as the  entry point to the main application loop.

Argument Passing: Instead of a wrapper, the add_student function uses a more functional approach. It returns the new, incremented next_student_id, which is then reassigned by the caller in main.

Input Handling: The built in input() function is used for all console inputs.

Error Handling: try/except (ValueError) blocks are used in the validation helpers to catch invalid user inputs.

### How to Run
First, please clone this repository to your local machine.

Java
You will need a Java JDK (Version 8 or higher) installed.
Navigate to the directory containing the Java file.

Compile the program:
javac EnrollmentSystem.java

Run the program:
java EnrollmentSystem

Python
You will need a Python 3 interpreter installed.
Navigate to the directory containing the Python file.

Run the program:
python enrollment_system.py
(Note: You may need to use python3 depending on your system's configuration)

### Output Preview
Both programs have an identical console interface. The program starts, displays the main menu, and waits for user input.
([Screenshot 2025-11-07 210722.png](<Screenshot 2025-11-07 210722.png>))

## Built With

(Java Version 8 or above.(https://www.oracle.com/ca-en/java/technologies/javase/javase8-archive-downloads.html)


[Python 3.13.7](https://www.python.org/downloads/release/python-3137/) (Python Version)
