import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentSystem {
    static class IntRef {
        public int value;
        public IntRef(int value) { this.value = value; }
    }

    static List<StudentRecord> student_list = new ArrayList<>();
    static IntRef next_student_id = new IntRef(1000);
    static Scanner scanner = new Scanner(System.in);

    public static class StudentRecord {
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

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- SAIT Enrollment System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Modify Student Record");
            System.out.println("4. Remove Student");
            System.out.println("0. Exit");

            int user_choice = GetValidIntegerRange("Enter your choice: ", 0, 4);

            if (user_choice == 1) {
                AddStudent(student_list, next_student_id);
            } else if (user_choice == 2) {
                DisplayStudents(student_list);
            } else if (user_choice == 3) {
                ModifyStudent(student_list);
            } else if (user_choice == 4) {
                RemoveStudent(student_list);
            } else if (user_choice == 0) {
                running = false;
                System.out.println("\nExiting program. Goodbye!");
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    public static void AddStudent(List<StudentRecord> student_list, IntRef next_student_id) {
        System.out.println("\n--- Add New Student ---");
        StudentRecord student_to_add = CreateNewStudent(next_student_id.value);
        student_list.add(student_to_add);
        next_student_id.value = next_student_id.value + 1;
        System.out.println("\nStudent added successfully with ID: " + student_to_add.id);
    }

    public static void DisplayStudents(List<StudentRecord> student_list) {
        if (student_list.isEmpty()) {
            System.out.println("\nNo students in the system.");
        } else {
            System.out.println("\n--- List of Enrolled Students ---");
            for (StudentRecord student : student_list) {
                System.out.println(student.toString());
            }
        }
    }

    public static void ModifyStudent(List<StudentRecord> student_list) {
        System.out.println("\n--- Modify Student Record ---");
        int index = PromptAndFindStudentIndex(student_list);
        if (index != -1) {
            ModifyStudentMenu(student_list, index);
            System.out.println("\nModifications saved.");
        }
    }

    public static void RemoveStudent(List<StudentRecord> student_list) {
        System.out.println("\n--- Remove Student ---");
        int index = PromptAndFindStudentIndex(student_list);
        if (index != -1) {
            StudentRecord student = student_list.get(index);
            System.out.println("Found student: " + student.firstName + " " + student.lastName);
            String confirmation = GetYesNo("Are you sure you want to remove? (Y/N): ");
            if (confirmation.equals("Y")) {
                student_list.remove(index);
                System.out.println("\nStudent removed successfully.");
            } else {
                System.out.println("\nRemoval cancelled.");
            }
        }
    }

    public static String GetString(String prompt_message) {
        System.out.print(prompt_message);
        return scanner.nextLine();
    }

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

    public static int PromptAndFindStudentIndex(List<StudentRecord> student_list) {
        int id_to_find = GetValidInteger("Enter Student ID: ");
        int index = FindStudentIndexByID(student_list, id_to_find);
        if (index == -1) {
            System.out.println("\nStudent with ID " + id_to_find + " not found.");
        }
        return index;
    }

    public static StudentRecord CreateNewStudent(int id) {
        String fname = GetString("Enter First Name: ");
        String lname = GetString("Enter Last Name: ");
        String dob = GetString("Enter Date of Birth (YYYY-MM-DD): ");
        String gender = GetString("Enter Gender: ");
        float gpa = GetValidFloat("Enter Previous GPA: ");
        String semester = GetString("Enter Current Semester (e.g. Fall 2026): ");
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

    public static void ModifyStudentMenu(List<StudentRecord> student_list, int index) {
        boolean modifying = true;
        while (modifying) {
            StudentRecord student = student_list.get(index);
            System.out.println("\n--- Modifying Student: " + student.firstName + " " + student.lastName + " ---");
            System.out.println("1. First Name (" + student.firstName + ")");
            System.out.println("2. Last Name (" + student.lastName + ")");
            System.out.println("3. GPA (" + student.gpa + ")");
            System.out.println("4. Semester (" + student.semester + ")");
            System.out.println("5. Program (" + student.program + ")");
            System.out.println("6. Number of Courses (" + student.numCourses + ")");
            System.out.println("0. Finish Modifying");

            int field_choice = GetValidIntegerRange("Enter field to modify: ", 0, 6);

            if (field_choice == 1) {
                String new_val = GetString("Enter new First Name: ");
                student.firstName = new_val;
                System.out.println("First Name updated.");
            } else if (field_choice == 2) {
                String new_val = GetString("Enter new Last Name: ");
                student.lastName = new_val;
                System.out.println("Last Name updated.");
            } else if (field_choice == 3) {
                float new_val = GetValidFloat("Enter new GPA: ");
                student.gpa = new_val;
                System.out.println("GPA updated.");
            } else if (field_choice == 4) {
                String new_val = GetString("Enter new Semester: ");
                student.semester = new_val;
                System.out.println("Semester updated.");
            } else if (field_choice == 5) {
                String new_val = GetString("Enter new Program: ");
                student.program = new_val;
                System.out.println("Program updated.");
            } else if (field_choice == 6) {
                int new_val = GetValidInteger("Enter new Number of Courses: ");
                student.numCourses = new_val;
                System.out.println("Number of courses updated.");
            } else if (field_choice == 0) {
                modifying = false;
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }
}