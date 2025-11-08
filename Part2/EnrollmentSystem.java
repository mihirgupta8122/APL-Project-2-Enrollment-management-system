import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * SAIT Enrollment Management System
 * This program is a student enrollment system that allows the user to add, display, modify, and remove student records.
 * It uses a list to store student records and provides a menu-driven interface for user interaction.
 * This Java version is designed to be functionally identical to its Python counterpart for comparison.
 */
public class EnrollmentSystem {

    /**
     * A wrapper class to emulate pass-by-reference for an integer.
     * Since Java passes primitives like 'int' by value, we pass an
     * object like this IntRef by reference-value instead, which will let us
     * change its internal value from inside a method.
     */
    static class IntRef {
        public int value;
        public IntRef(int value) { this.value = value; }
    }

    //Class Level Variables 
    //This is Java's way of handling a "global" state for the application.
    //These are 'static' so they are shared by all methods in the class.

    /** The main data container for all student records. */
    static List<StudentRecord> studentList = new ArrayList<>();

    /** The counter for new student IDs, wrapped in the IntRef class. */
    static IntRef nextStudentId = new IntRef(1000);

    /** A single, shared Scanner object for all user input. */
    static Scanner scanner = new Scanner(System.in);

    /**
     * This inner class defines the structure of a student record.
     */
    public static class StudentRecord {
        // These are all 'public' for easy access, matching the pseudocode's simple design
        public int id;
        public String firstName;
        public String lastName;
        public String dob;
        public String gender;
        public float gpa;
        public String semester;
        public String program;
        public int numCourses;

        /** A default constructor. */
        public StudentRecord() {}

        /**
         * A parameterized constructor for creating a full student record at once.
         */
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

        /**
         * Overrides the default toString() method for clean, formatted printing of student's details.
         * This uses Java's String.format() and text block features (since Java 15)
         * to match the Python f-string output.
         */
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
        }
    }

    /**
     * This is the main driver of the program.
     * Its only job is to run the main menu loop and delegate tasks
     * to the appropriate subprograms
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        boolean running = true;
        
        //This is the main application loop
        while (running) {
            //Display the main menu using a Java text block for readability
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

            //Get a validated menu choice by calling a helper method
            int userChoice = getValidIntegerRange("Enter your choice: ", 0, 4);

            //Based on the user choice, call the relevant subprogram.
            if (userChoice == 1) {
                // Pass both the list and the ID counter
                addStudent(studentList, nextStudentId);
            } else if (userChoice == 2) {
                displayStudents(studentList);
            } else if (userChoice == 3) {
                modifyStudent(studentList);
            } else if (userChoice == 4) {
                removeStudent(studentList);
            } else if (userChoice == 0) {
                running = false; // Exit the while loop
                System.out.println("\nExiting program. Goodbye!");
            }
        }
        //Close the scanner
        scanner.close();
    }

    //Main Subprograms
    /**
     * This handles adding a new student.
     * @param studentList The main list (passed by reference-value).
     * @param nextStudentId The ID counter (passed as a mutable IntRef object).
     */
    public static void addStudent(List<StudentRecord> studentList, IntRef nextStudentId) {
        System.out.println("\n--- Add New Student ---");
        //Delegate creation to a helper, passing the current ID value
        StudentRecord studentToAdd = createNewStudent(nextStudentId.value);
        
        //Add the new student to the main list (modifies the original list)
        studentList.add(studentToAdd);
        
        //Increment the ID by changing the IntRef object's internal value.
        //This change persists because 'nextStudentId' is a reference to an object.
        nextStudentId.value = nextStudentId.value + 1;
        
        System.out.printf("%nStudent added successfully with ID: %d%n", studentToAdd.id);
    }

    /**
     * Displays all students in the list.
     * @param studentList The main list. We only read from it,
     * fulfilling the 'pass-by-VALUE' (read-only) intent from the pseudocode.
     */
    public static void displayStudents(List<StudentRecord> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("\nNo students in the system.");
        } else {
            System.out.println("\n--- List of Enrolled Students ---");
            //Use a "for-each" loop to iterate over the list
            for (StudentRecord student : studentList) {
                // This implicitly calls the student.toString() method
                System.out.println(student.toString());
            }
        }
    }

    /**
     * This handles modifying a student's information. It delegates all logic to helper methods.
     * @param studentList The main list (passed by reference-value).
     */
    public static void modifyStudent(List<StudentRecord> studentList) {
        System.out.println("\n--- Modify Student Record ---");
        //Find the student by their ID
        int index = promptAndFindStudentIndex(studentList);
        
        //If the student was found (the index isn't -1), show the menu for modifying
        if (index != -1) {
            modifyStudentMenu(studentList, index);
            System.out.println("\nModifications saved.");
        }
    }

    /**
     * Handles removing a student.
     * @param studentList The main list (passed by reference-value).
     */
    public static void removeStudent(List<StudentRecord> studentList) {
        System.out.println("\n--- Remove Student ---");
        //Find the student
        int index = promptAndFindStudentIndex(studentList);
        
        //If the student was found, ask for confirmation
        if (index != -1) {
            StudentRecord student = studentList.get(index);
            System.out.printf("Found student: %s %s%n", student.firstName, student.lastName);
            
            String confirmation = getYesNo("Are you sure you want to remove? (Y/N): ");
            
            //If confirmed, remove the student from the original list
            if (confirmation.equals("Y")) {
                studentList.remove(index);
                System.out.println("\nStudent removed successfully.");
            } else {
                System.out.println("\nRemoval cancelled.");
            }
        }
    }

    //Helper Subprograms

    /**
     * HELPER: Gets a simple string input from the user.
     * @param promptMessage The message to display to the user.
     * @return The string entered by the user.
     */
    public static String getString(String promptMessage) {
        System.out.print(promptMessage);
        return scanner.nextLine();
    }

    /**
     * HELPER: Loops until a valid integer is entered.
     * @param promptMessage The message to display.
     * @return The validated integer.
     */
    public static int getValidInteger(String promptMessage) {
        boolean valid = false;
        int number = 0;
        while (!valid) {
            System.out.print(promptMessage);
            String input = scanner.nextLine();
            
            //We have to explicitly try to parse the String into an int.
            //If it fails, a 'NumberFormatException' is thrown.
            try {
                number = Integer.parseInt(input.trim());
                valid = true; // Input was valid, exit the loop
            } catch (NumberFormatException e) {
                //Catch the error and tell the user to try again
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
        return number;
    }

    /**
     * HELPER: Reuses getValidInteger to get a number within a specific range.
     * @param promptMessage The message to display.
     * @param min The minimum allowed value (inclusive).
     * @param max The maximum allowed value (inclusive).
     * @return The validated integer within the specified range.
     */
    public static int getValidIntegerRange(String promptMessage, int min, int max) {
        int number;
        while (true) { //This will Loop indefinitely
            //Get a valid integer (reusing another helper)
            number = getValidInteger(promptMessage);
            
            //Check if it's in the correct range
            if (number >= min && number <= max) {
                return number; //Valid, exit the loop and return
            } else {
                //Invalid range, print the error and loop back
                System.out.printf("Invalid choice. Please enter a number between %d and %d.%n", min, max);
            }
        }
    }

    /**
     * HELPER: Loops until a valid float is entered.
     * @param promptMessage The message to display.
     * @return The validated float.
     */
    public static float getValidFloat(String promptMessage) {
        boolean valid = false;
        float number = 0.0f;
        while (!valid) {
            System.out.print(promptMessage);
            String input = scanner.nextLine();
            try {
                // Same as getValidInteger, but for 'float' instead of 'int'
                number = Float.parseFloat(input.trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (e.g., 3.5).");
            }
        }
        return number;
    }

    /**
     * HELPER: Loops until the user enters "Y" or "N".
     * @param promptMessage The message to display.
     * @return The validated string ("Y" or "N").
     */
    public static String getYesNo(String promptMessage) {
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

    /**
     * HELPER: Finds the list index of a student by their ID.
     * @param studentList The main list (read-only).
     * @param idToFind The student ID to search for.
     * @return The index of the student, or -1 if not found.
     */
    public static int findStudentIndexByID(List<StudentRecord> studentList, int idToFind) {
        int index = 0;
        //Java's 'list.size()' is equivalent to Python's 'len(list)'
        while (index < studentList.size()) {
            //'list.get(index)' is equivalent to Python's 'list[index]'
            if (studentList.get(index).id == idToFind) {
                return index; //Found
            }
            index = index + 1;
        }
        return -1; //Not found
    }

    /**
     * HELPER: Bundles the logic for prompting for and finding a student.
     * @param studentList The main list.
     * @return The index of the student, or -1 if not found.
     */
    public static int promptAndFindStudentIndex(List<StudentRecord> studentList) {
        int idToFind = getValidInteger("Enter Student ID: ");
        int index = findStudentIndexByID(studentList, idToFind);
        if (index == -1) {
            System.out.printf("%nStudent with ID %d not found.%n", idToFind);
        }
        return index;
    }

    /**
     * HELPER: Handles the creation of a new StudentRecord by
     * calling all the other 'Get' helpers.
     * @param id The unique ID for the new student.
     * @return A new, fully populated StudentRecord object.
     */
    public static StudentRecord createNewStudent(int id) {
        String fname = getString("Enter First Name: ");
        String lname = getString("Enter Last Name: ");
        String dob = getString("Enter Date of Birth (YYYY-MM-DD): ");
        String gender = getString("Enter Gender: ");
        float gpa = getValidFloat("Enter Previous GPA: ");
        String semester = getString("Enter Current Semester (e.g. Fall 2026): ");
        String program = getString("Enter Program: ");
        int numCourses = getValidInteger("Enter Number of Courses: ");
        
        // Create a new object instance
        StudentRecord newStudent = new StudentRecord();
        
        // Fill up the fields of the object with the given data
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
    }

    /**
     * HELPER: Manages the modification sub-menu.
     * @param studentList The main list.
     * @param index The index of the student to be modified.
     */
    public static void modifyStudentMenu(List<StudentRecord> studentList, int index) {
        boolean modifying = true;
        while (modifying) {
            //'student' is a reference variable pointing to the object in the list.
            StudentRecord student = studentList.get(index);
            
            //Display the sub-menu using a formatted text block
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

            int fieldChoice = getValidIntegerRange("Enter field to modify: ", 0, 6);

            // Branching the logic for the sub-menu
            if (fieldChoice == 1) {
                String newVal = getString("Enter new First Name: ");
                //Modifying 'student.firstName' modifies the original
                //object in the 'studentList' because 'student' is a reference.
                student.firstName = newVal;
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
                modifying = false; // Exit the 'while (modifying)' loop
            } 
        }
    }
}