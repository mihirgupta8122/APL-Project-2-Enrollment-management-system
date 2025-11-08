import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * SAIT Enrollment Management System
 * This program implements a student enrollment system
 */
public class EnrollmentSystem {

    /**
     * A "wrapper" class to allow pass-by-reference for an integer.
     * Since Java passes primitives (like 'int') by value, we pass an
     * object (this IntRef) by reference-value instead, which will let us
     * change its internal 'value' from inside  a method.
     */
    static class IntRef {
        public int value;
        public IntRef(int value) { this.value = value; }
    }

    static List<StudentRecord> studentList = new ArrayList<>();
    static IntRef nextStudentId = new IntRef(1000);
    //Class Level (Static) Variables
    //This is Java's way of handling a "global" state for the application.

    //The main data container for all student records.
    static List<StudentRecord> student_list = new ArrayList<>();

    //The counter for new student IDs, wrapped in the IntRef class.
    static IntRef next_student_id = new IntRef(1000);

    //A shared Scanner object for all user input.
    static Scanner scanner = new Scanner(System.in);

    //This defines the structure of a student record.
    public static class StudentRecord {
        // These are all 'public' for easy access
        public int id;
        public String firstName;
        public String lastName;
        public String dob;
        public String gender;
        public float gpa;
        public String semester;
        public String program;
        public int numCourses;

        public StudentRecord() {}

        //A parameterized constructor for creating a full student record at once. 
        public StudentRecord(int id, String firstName, String lastName, String dob, String gender,
                             float gpa, String semester, String program, int numCourses) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.dob = dob;
            this.gender = gender;
            this.gpa = gpa;
            this.semester = semester;
            this.program = program;
            this.numCourses = numCourses;
        }

        
          //Overrides the default toString() method for clean, formatted printing of student's details.
        @Override
        public String toString() {
            return String.format(
                """

                Student ID: %d
                   Name: %s %s
                   DOB: %s
                   Gender: %s
                   GPA: %.2f
                   Program: %s
                   Semester: %s
                   Number of Courses: %d""",
                id, firstName, lastName, dob, gender, gpa, program, semester, numCourses);
            // This was done to match the f string formatting in the python version as well as for readability
            return "\nStudent ID: " + id + "\n"
                    + "  Name: " + firstName + " " + lastName + "\n"
                    + "  DOB: " + dob + "\n"
                    + "  Gender: " + gender + "\n"
                    + "  GPA: " + gpa + "\n"
                    + "  Program: " + program + "\n"
                    + "  Semester: " + semester + "\n"
                    + "  Courses: " + numCourses;
        }
    }

    
     //This driver's only job is to run the main menu loop and delegate tasks to the appropriate subprograms.
    public static void main(String[] args) {
        boolean running = true;
        
        // This is the main application loop
        while (running) {
            System.out.println(
                """

                --- SAIT Enrollment System ---
                1. Add New Student
                2. Display All Students
                3. Modify Student Record
                4. Remove Student
                0. Exit
                """
                );
            // Display the main menu
            System.out.println("\n--- SAIT Enrollment System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Modify Student Record");
            System.out.println("4. Remove Student");
            System.out.println("0. Exit");

            int userChoice = getValidIntegerRange("Enter your choice: ", 0, 4);
            // Get a validated menu choice by calling a helper method
            int user_choice = GetValidIntegerRange("Enter your choice: ", 0, 4);

            if (userChoice == 1) {
                addStudent(studentList, nextStudentId);
            } else if (userChoice == 2) {
                displayStudents(studentList);
            } else if (userChoice == 3) {
                modifyStudent(studentList);
            } else if (userChoice == 4) {
                removeStudent(studentList);
            } else if (userChoice == 0) {
                running = false;
            // Based on the choice, call the relevant subprogram.
            if (user_choice == 1) {
                // Pass both the list and the ID counter
                AddStudent(student_list, next_student_id);
            } else if (user_choice == 2) {
                DisplayStudents(student_list);
            } else if (user_choice == 3) {
                ModifyStudent(student_list);
            } else if (user_choice == 4) {
                RemoveStudent(student_list);
            } else if (user_choice == 0) {
                running = false; // Exit the while loop
                System.out.println("\nExiting program. Goodbye!");
            }
        }
        // Close the single static scanner
        scanner.close();
    }

    public static void addStudent(List<StudentRecord> studentList, IntRef nextStudentId) {
    //Main Subprograms
    /**
     * This handles adding a new student.
     * @param student_list The main list 
     * @param next_student_id The ID counter 
     */
    public static void AddStudent(List<StudentRecord> student_list, IntRef next_student_id) {
        System.out.println("\n--- Add New Student ---");
        StudentRecord studentToAdd = createNewStudent(nextStudentId.value);
        studentList.add(studentToAdd);
        nextStudentId.value = nextStudentId.value + 1;
        System.out.printf("%nStudent added successfully with ID: %d%n", studentToAdd.id);
        // 1. Delegate creation to a helper, passing the *current* ID value
        StudentRecord student_to_add = CreateNewStudent(next_student_id.value);
        
        // 2. Add the new student to the main list (modifies the original list)
        student_list.add(student_to_add);
        
        // 3. Increment the ID by modifying the IntRef object's value.
        next_student_id.value = next_student_id.value + 1;
        
        System.out.println("\nStudent added successfully with ID: " + student_to_add.id);
    }

    public static void displayStudents(List<StudentRecord> studentList) {
        if (studentList.isEmpty()) {
    /**
     * Displays all students in the list.
     * @param student_list The main list. We only read from it,
     */
    public static void DisplayStudents(List<StudentRecord> student_list) {
        if (student_list.isEmpty()) {
            System.out.println("\nNo students in the system.");
        } else {
            System.out.println("\n--- List of Enrolled Students ---");
            for (StudentRecord student : studentList) {
            // Use a "for-each" loop to iterate over the list
            for (StudentRecord student : student_list) {
                // This implicitly calls the student.toString() method
                System.out.println(student.toString());
            }
        }
    }

    public static void modifyStudent(List<StudentRecord> studentList) {
    /**
     * This handles modifying a student's information. It delegates pretty much all logic to helper methods.
     * @param student_list The main list 
     */
    public static void ModifyStudent(List<StudentRecord> student_list) {
        System.out.println("\n--- Modify Student Record ---");
        int index = promptAndFindStudentIndex(studentList);
        // 1. Find the student by their ID
        int index = PromptAndFindStudentIndex(student_list);
        
        // 2. If the student was found (the index isn't -1), show the menu for modifying
        if (index != -1) {
            modifyStudentMenu(studentList, index);
            System.out.println("\nModifications saved.");
        }
    }

    public static void removeStudent(List<StudentRecord> studentList) {
    /**
     * Handles removing a student.
     * @param student_list The main list 
     */
    public static void RemoveStudent(List<StudentRecord> student_list) {
        System.out.println("\n--- Remove Student ---");
        int index = promptAndFindStudentIndex(studentList);
        // 1. Find the student
        int index = PromptAndFindStudentIndex(student_list);
        
        // 2. If the student was found, ask for confirmation
        if (index != -1) {
            StudentRecord student = studentList.get(index);
            System.out.printf("Found student: %s %s%n", student.firstName, student.lastName);
            String confirmation = getYesNo("Are you sure you want to remove? (Y/N): ");
            StudentRecord student = student_list.get(index);
            System.out.println("Found student: " + student.firstName + " " + student.lastName);
            
            String confirmation = GetYesNo("Are you sure you want to remove? (Y/N): ");
            
            // 3. If confirmed, remove the student from the original list
            if (confirmation.equals("Y")) {
                studentList.remove(index);
                System.out.println("\nStudent removed successfully.");
            } else {
                System.out.println("\nRemoval cancelled.");
            }
        }
    }

    public static String getString(String promptMessage) {
        System.out.print(promptMessage);
    //Helper Subprograms

    /**
     * HELPER: Gets a simple string input from the user.
     * @param prompt_message The message to display to the user.
     * @return The string entered by the user.
     */
    public static String GetString(String prompt_message) {
        System.out.print(prompt_message);
        return scanner.nextLine();
    }

    public static int getValidInteger(String promptMessage) {
    /**
     * HELPER: Loops until a valid integer is entered.
     * @param prompt_message The message to display.
     * @return The validated integer.
     */
    public static int GetValidInteger(String prompt_message) {
        boolean valid = false;
        int number = 0;
        while (!valid) {
            System.out.print(promptMessage);
            String input = scanner.nextLine();
            
            //Static Typing
            //We have to explicitly try to parse the String into an int.
            //If it fails, a 'NumberFormatException' is thrown.
            try {
                number = Integer.parseInt(input.trim());
                valid = true; // Input was valid, exit the loop
            } catch (NumberFormatException e) {
                // Catch the error and tell the user to try again
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
        return number;
    }

    public static int getValidIntegerRange(String promptMessage, int min, int max) {
    /**
     * HELPER: Re-uses GetValidInteger to get a number within a specific range.
     * @param prompt_message The message to display.
     * @param min The minimum allowed value.
     * @param max The maximum allowed value.
     * @return The validated integer within the specified range.
     */
    public static int GetValidIntegerRange(String prompt_message, int min, int max) {
        int number;
        while (true) {
            number = getValidInteger(promptMessage);
        while (true) { //This will Loop indefinitely
            //1. Get a valid integer (reusing another helper)
            number = GetValidInteger(prompt_message);
            
            //2. Check if it's in the correct range
            if (number >= min && number <= max) {
                return number; //Valid, exit the loop and return
            } else {
                System.out.printf("Invalid choice. Please enter a number between %d and %d.%n", min, max);
                // Invalid range, print the error and loop back
                System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
            }
        }
    }

    public static float getValidFloat(String promptMessage) {
    /**
     * HELPER: Loops until a valid float is entered.
     * @param prompt_message The message to display.
     * @return The validated float.
     */
    public static float GetValidFloat(String prompt_message) {
        boolean valid = false;
        float number = 0.0f;
        while (!valid) {
            System.out.print(promptMessage);
            String input = scanner.nextLine();
            try {
                // Same as GetValidInteger, but for 'float' instead of 'int'
                number = Float.parseFloat(input.trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (e.g., 3.5).");
            }
        }
        return number;
    }

    public static String getYesNo(String promptMessage) {
    /**
     * HELPER: Loops until the user enters "Y" or "N".
     * @param prompt_message The message to display.
     * @return The validated string ("Y" or "N").
     */
    public static String GetYesNo(String prompt_message) {
        while (true) {
            System.out.print(promptMessage);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("N")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        }
    }

    public static int findStudentIndexByID(List<StudentRecord> studentList, int idToFind) {
    /**
     * HELPER: Finds the list index of a student by their ID.
     * @param student_list The main list (read-only).
     * @param id_to_find The student ID to search for.
     * @return The index of the student, or -1 if not found.
     */
    public static int FindStudentIndexByID(List<StudentRecord> student_list, int id_to_find) {
        int index = 0;
        while (index < studentList.size()) {
            if (studentList.get(index).id == idToFind) {
                return index;
        //Java's 'list.size()' is equivalent to Python's 'len(list)'
        while (index < student_list.size()) {
            //'list.get(index)' is equivalent to Python's 'list[index]'
            if (student_list.get(index).id == id_to_find) {
                return index; // Found
            }
            index = index + 1;
        }
        return -1; // Not found
    }

    public static int promptAndFindStudentIndex(List<StudentRecord> studentList) {
        int idToFind = getValidInteger("Enter Student ID: ");
        int index = findStudentIndexByID(studentList, idToFind);
    /**
     * HELPER: Bundles the logic for prompting for and finding a student.
     * @param student_list The main list.
     * @return The index of the student, or -1 if not found.
     */
    public static int PromptAndFindStudentIndex(List<StudentRecord> student_list) {
        int id_to_find = GetValidInteger("Enter Student ID: ");
        int index = FindStudentIndexByID(student_list, id_to_find);
        if (index == -1) {
            System.out.printf("%nStudent with ID %d not found.%n", idToFind);
        }
        return index;
    }

    public static StudentRecord createNewStudent(int id) {
        String fname = getString("Enter First Name: ");
        String lname = getString("Enter Last Name: ");
        String dob = getString("Enter Date of Birth (YYYY-MM-DD): ");
        String gender = getString("Enter Gender: ");
        float gpa = getValidFloat("Enter Previous GPA: ");
        String semester = getString("Enter Current Semester (e.g. Fall 2026): ");
        String program = getString("Enter Program: ");
        int numCourses = getValidInteger("Enter Number of Courses: ");
        StudentRecord newStudent = new StudentRecord();
        newStudent.id = id;
        newStudent.firstName = fname;
        newStudent.lastName = lname;
        newStudent.dob = dob;
        newStudent.gender = gender;
        newStudent.gpa = gpa;
        newStudent.semester = semester;
        newStudent.program = program;
        newStudent.numCourses = numCourses;
        return newStudent;
    /**
     * HELPER: Handles the creation of a new StudentRecord by
     * calling all the other 'Get' helpers.
     * @param id The unique ID for the new student.
     * @return A new, fully populated StudentRecord object.
     */
    public static StudentRecord CreateNewStudent(int id) {
        String fname = GetString("Enter First Name: ");
        String lname = GetString("Enter Last Name: ");
        String dob = GetString("Enter Date of Birth (YYYY-MM-DD): ");
        String gender = GetString("Enter Gender: ");
        float gpa = GetValidFloat("Enter Previous GPA: ");
        String semester = GetString("Enter Current Semester (e.g. Fall 2026): ");
        String program = GetString("Enter Program: ");
        int num_courses = GetValidInteger("Enter Number of Courses: ");
        
        // Create a new object instance
        StudentRecord new_student = new StudentRecord();
        
        //Static Typing & Assignment
        //Fill up the fields of the object with the given data
        new_student.id = id;
        new_student.firstName = fname;
        new_student.lastName = lname;
        new_student.dob = dob;
        new_student.gender = gender;
        new_student.gpa = gpa;
        new_student.semester = semester;
        new_student.program = program;
        new_student.numCourses = num_courses;
        
        return new_student;
    }

    public static void modifyStudentMenu(List<StudentRecord> studentList, int index) {
    /**
     * HELPER: Manages the modification sub-menu.
     * @param student_list The main list.
     * @param index The index of the student to be modified.
     */
    public static void ModifyStudentMenu(List<StudentRecord> student_list, int index) {
        boolean modifying = true;
        while (modifying) {
            StudentRecord student = studentList.get(index);
            System.out.println(String.format(
                """

                --- Modifying Student: %1$s %2$s ---
                1. First Name (%1$s)
                2. Last Name (%2$s)
                3. GPA (%3$.2f)
                4. Semester (%4$s)
                5. Program (%5$s)
                6. Number of Courses (%6$d)
                0. Finish Modifying
                """, 
                student.firstName, student.lastName, student.gpa, student.semester, student.program, student.numCourses));
            StudentRecord student = student_list.get(index);
            
            // Display the sub-menu
            System.out.println("\n--- Modifying Student: " + student.firstName + " " + student.lastName + " ---");
            System.out.println("1. First Name (" + student.firstName + ")");
            System.out.println("2. Last Name (" + student.lastName + ")");
            System.out.println("3. GPA (" + student.gpa + ")");
            System.out.println("4. Semester (" + student.semester + ")");
            System.out.println("5. Program (" + student.program + ")");
            System.out.println("6. Number of Courses (" + student.numCourses + ")");
            System.out.println("0. Finish Modifying");

            int fieldChoice = getValidIntegerRange("Enter field to modify: ", 0, 6);

            if (fieldChoice == 1) {
                String newVal = getString("Enter new First Name: ");
                student.firstName = newVal;
            // Branching logic for the sub-menu
            if (field_choice == 1) {
                String new_val = GetString("Enter new First Name: ");
                student.firstName = new_val;
                System.out.println("First Name updated.");
            } else if (fieldChoice == 2) {
                String newVal = getString("Enter new Last Name: ");
                student.lastName = newVal;
                System.out.println("Last Name updated.");
            } else if (fieldChoice == 3) {
                float newVal = getValidFloat("Enter new GPA: ");
                student.gpa = newVal;
                System.out.println("GPA updated.");
            } else if (fieldChoice == 4) {
                String newVal = getString("Enter new Semester: ");
                student.semester = newVal;
                System.out.println("Semester updated.");
            } else if (fieldChoice == 5) {
                String newVal = getString("Enter new Program: ");
                student.program = newVal;
                System.out.println("Program updated.");
            } else if (fieldChoice == 6) {
                int newVal = getValidInteger("Enter new Number of Courses: ");
                student.numCourses = newVal;
                System.out.println("Number of courses updated.");
            } else if (fieldChoice == 0) {
                modifying = false;
            } else if (field_choice == 0) {
                modifying = false; // Exit the 'while (modifying)' loop
            } 
        }
    }
}