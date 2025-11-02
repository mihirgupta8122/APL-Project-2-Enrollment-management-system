import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentSystem {
    // Corresponds to: DECLARE student_list AS LIST OF StudentRecord
    static List<StudentRecord> student_list = new ArrayList<>();

    // Corresponds to: DECLARE next_student_id AS INTEGER = 1000
    static int next_student_id = 1000;

    static Scanner scanner = new Scanner(System.in);

    // Inner class corresponds to: STRUCTURE StudentRecord
    public static class StudentRecord {
        public int id;
        public String firstName;
        public String lastName;
        public String dob;       // kept as String to match pseudocode
        public String gender;
        public float gpa;
        public String semester;
        public String program;
        public int numCourses;

        public StudentRecord() {}

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

        @Override
        public String toString() {
            return "Student ID: " + id + "\n"
                    + "  Name: " + firstName + " " + lastName + "\n"
                    + "  DOB: " + dob + "\n"
                    + "  Gender: " + gender + "\n"
                    + "  GPA: " + gpa + "\n"
                    + "  Program: " + program + "\n"
                    + "  Semester: " + semester + "\n"
                    + "  Courses: " + numCourses;
        }
    }

    // Main Program (START)
    public static void main(String[] args) {
        // Permanently populate the four requested students at startup
        populatePermanentData();

        boolean running = true;

        while (running) {
            System.out.println("--- SAIT Enrollment System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Modify Student Record");
            System.out.println("4. Remove Student");
            System.out.println("5. Exit");

            int user_choice = GetValidIntegerRange("Enter your choice: ", 1, 5);

            switch (user_choice) {
                case 1:
                    AddStudent(student_list);
                    break;
                case 2:
                    DisplayStudents(student_list);
                    break;
                case 3:
                    ModifyStudent(student_list);
                    break;
                case 4:
                    RemoveStudent(student_list);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting program. Please run the MIHIR program again");
                    break;
                default:
                    // Should not happen because GetValidIntegerRange restricts values
                    System.out.println("Invalid choice.");
                    break;
            }
        }

        scanner.close();
    }

    // Permanently add the exact 4 students provided by the user
    public static void populatePermanentData() {
        // Student 1000
        StudentRecord s0 = new StudentRecord(
            1000,
            "Mihir",
            "Gupta",
            "2000-12-08",
            "male",
            3.4f,
            "4",
            "Bachelor of technology",
            15
        );
        student_list.add(s0);

        // Student 1001
        StudentRecord s1 = new StudentRecord(
            1001,
            "Umaya",
            "Hewaarachchi",
            "2001-12-24",
            "female",
            3.5f,
            "3",
            "bachelors of technology",
            15
        );
        student_list.add(s1);

        // Student 1002
        StudentRecord s2 = new StudentRecord(
            1002,
            "Jazmin",
            "Horton",
            "2003-04-07",
            "female",
            3.4f,
            "4",
            "bachelors of technology",
            15
        );
        student_list.add(s2);

        // Student 1003
        StudentRecord s3 = new StudentRecord(
            1003,
            "Nathan",
            "Woyessa",
            "2001-03-14",
            "Male",
            3.8f,
            "4",
            "bachelors of technology",
            15
        );
        student_list.add(s3);

        // Update next_student_id so newly added students get unique IDs
        next_student_id = 1004;
        System.out.println("Loaded 4 permanent student records (IDs 1000-1003).");
    }

    // 1. Add Student
    // PROCEDURE AddStudent(REFERENCE student_list, REFERENCE next_student_id)
    public static void AddStudent(List<StudentRecord> student_list) {
        System.out.println("--- Add New Student ---");

        // 1. Create the student (delegated to a helper)
        StudentRecord student_to_add = CreateNewStudent(next_student_id);

        // 2. Add it to the list
        student_list.add(student_to_add);

        // 3. Increment the ID (emulates REFERENCE next_student_id)
        next_student_id = next_student_id + 1;

        System.out.println("Student added successfully with ID: " + student_to_add.id);
    }

    // 2. Display Students
    // PROCEDURE DisplayStudents(VALUE student_list)
    public static void DisplayStudents(List<StudentRecord> student_list) {
        if (student_list.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            System.out.println("--- List of Enrolled Students ---");
            for (StudentRecord student : student_list) {
                System.out.println(student.toString());
            }
        }
    }

    // 3. Modify Student
    // PROCEDURE ModifyStudent(REFERENCE student_list)
    public static void ModifyStudent(List<StudentRecord> student_list) {
        System.out.println("--- Modify Student Record ---");

        int index = PromptAndFindStudentIndex(student_list);

        if (index != -1) {
            ModifyStudentMenu(student_list, index);
            System.out.println("Modifications saved.");
        }
    }

    // 4. Remove Student
    // PROCEDURE RemoveStudent(REFERENCE student_list)
    public static void RemoveStudent(List<StudentRecord> student_list) {
        System.out.println("--- Remove Student ---");

        int index = PromptAndFindStudentIndex(student_list);

        if (index != -1) {
            StudentRecord student = student_list.get(index);
            System.out.println("Found student: " + student.firstName + " " + student.lastName);

            String confirmation = GetYesNo("Are you sure you want to remove? (Y/N): ");

            if (confirmation.equals("Y")) {
                student_list.remove(index);
                System.out.println("Student removed successfully.");
            } else {
                System.out.println("Removal cancelled.");
            }
        }
    }

    // HELPER: Gets a simple string from the user
    public static String GetString(String prompt_message) {
        System.out.print(prompt_message);
        return scanner.nextLine();
    }

    // HELPER: Loops until a valid integer is entered
    public static int GetValidInteger(String prompt_message) {
        boolean valid = false;
        int number = 0;
        while (!valid) {
            System.out.print(prompt_message);
            String input = scanner.nextLine();
            try {
                number = Integer.parseInt(input.trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
        return number;
    }

    // HELPER: Loops until a valid integer in a specific range is entered
    public static int GetValidIntegerRange(String prompt_message, int min, int max) {
        int number;
        while (true) {
            number = GetValidInteger(prompt_message);
            if (number >= min && number <= max) {
                return number;
            } else {
                System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
            }
        }
    }

    // HELPER: Loops until a valid float is entered
    public static float GetValidFloat(String prompt_message) {
        boolean valid = false;
        float number = 0.0f;
        while (!valid) {
            System.out.print(prompt_message);
            String input = scanner.nextLine();
            try {
                number = Float.parseFloat(input.trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (e.g., 3.5).");
            }
        }
        return number;
    }

    // HELPER: Loops until 'Y' or 'N' is entered
    public static String GetYesNo(String prompt_message) {
        while (true) {
            System.out.print(prompt_message);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("N")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        }
    }

    // HELPER: Finds a student's index (from before)
    public static int FindStudentIndexByID(List<StudentRecord> student_list, int id_to_find) {
        int index = 0;
        while (index < student_list.size()) {
            if (student_list.get(index).id == id_to_find) {
                return index;
            }
            index = index + 1;
        }
        return -1;
    }

    // HELPER: Handles the repetitive task of asking for an ID and finding the student
    public static int PromptAndFindStudentIndex(List<StudentRecord> student_list) {
        int id_to_find = GetValidInteger("Enter Student ID: ");
        int index = FindStudentIndexByID(student_list, id_to_find);

        if (index == -1) {
            System.out.println("Student with ID " + id_to_find + " not found.");
        }

        return index;
    }

    // HELPER: Handles the creation of a new student record
    public static StudentRecord CreateNewStudent(int id) {
        String fname = GetString("Enter First Name: ");
        String lname = GetString("Enter Last Name: ");
        String dob = GetString("Enter Date of Birth (YYYY-MM-DD): ");
        String gender = GetString("Enter Gender: ");
        float gpa = GetValidFloat("Enter Previous GPA: ");
        String semester = GetString("Enter Current Semester: ");
        String program = GetString("Enter Program: ");
        int num_courses = GetValidInteger("Enter Number of Courses: ");

        StudentRecord new_student = new StudentRecord();
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

    // HELPER: Handles the modification sub-menu
    // PROCEDURE ModifyStudentMenu(REFERENCE student_list, VALUE index)
    public static void ModifyStudentMenu(List<StudentRecord> student_list, int index) {
        boolean modifying = true;
        while (modifying) {
            StudentRecord student = student_list.get(index);
            System.out.println("--- Modifying Student: " + student.firstName + " " + student.lastName + " ---");
            System.out.println("1. Last Name (" + student.lastName + ")");
            System.out.println("2. GPA (" + student.gpa + ")");
            System.out.println("3. Semester (" + student.semester + ")");
            System.out.println("4. Program (" + student.program + ")");
            System.out.println("5. Number of Courses (" + student.numCourses + ")");
            System.out.println("0. Finish Modifying");

            int field_choice = GetValidIntegerRange("Enter field to modify: ", 0, 5);

            switch (field_choice) {
                case 1: {
                    String new_val = GetString("Enter new Last Name: ");
                    student_list.get(index).lastName = new_val;
                    System.out.println("Last Name updated.");
                    break;
                }
                case 2: {
                    float new_val = GetValidFloat("Enter new GPA: ");
                    student_list.get(index).gpa = new_val;
                    System.out.println("GPA updated.");
                    break;
                }
                case 3: {
                    String new_val = GetString("Enter new Semester: ");
                    student_list.get(index).semester = new_val;
                    System.out.println("Semester updated.");
                    break;
                }
                case 4: {
                    String new_val = GetString("Enter new Program: ");
                    student_list.get(index).program = new_val;
                    System.out.println("Program updated.");
                    break;
                }
                case 5: {
                    int new_val = GetValidInteger("Enter new Number of Courses: ");
                    student_list.get(index).numCourses = new_val;
                    System.out.println("Number of courses updated.");
                    break;
                }
                case 0:
                    modifying = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }
}