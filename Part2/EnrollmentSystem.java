import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrollmentSystem {
    static class IntRef {
        public int value;
        public IntRef(int value) { this.value = value; }
    }

    static List<StudentRecord> studentList = new ArrayList<>();
    static IntRef nextStudentId = new IntRef(1000);
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

    public static void main(String[] args) {
        boolean running = true;
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

            int userChoice = GetValidIntegerRange("Enter your choice: ", 0, 4);

            if (userChoice == 1) {
                AddStudent(studentList, nextStudentId);
            } else if (userChoice == 2) {
                DisplayStudents(studentList);
            } else if (userChoice == 3) {
                ModifyStudent(studentList);
            } else if (userChoice == 4) {
                RemoveStudent(studentList);
            } else if (userChoice == 0) {
                running = false;
                System.out.println("\nExiting program. Goodbye!");
            }
        }
        scanner.close();
    }

    public static void AddStudent(List<StudentRecord> studentList, IntRef nextStudentId) {
        System.out.println("\n--- Add New Student ---");
        StudentRecord studentToAdd = CreateNewStudent(nextStudentId.value);
        studentList.add(studentToAdd);
        nextStudentId.value = nextStudentId.value + 1;
        System.out.printf("%nStudent added successfully with ID: %d%n", studentToAdd.id);
    }

    public static void DisplayStudents(List<StudentRecord> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("\nNo students in the system.");
        } else {
            System.out.println("\n--- List of Enrolled Students ---");
            for (StudentRecord student : studentList) {
                System.out.println(student.toString());
            }
        }
    }

    public static void ModifyStudent(List<StudentRecord> studentList) {
        System.out.println("\n--- Modify Student Record ---");
        int index = PromptAndFindStudentIndex(studentList);
        if (index != -1) {
            ModifyStudentMenu(studentList, index);
            System.out.println("\nModifications saved.");
        }
    }

    public static void RemoveStudent(List<StudentRecord> studentList) {
        System.out.println("\n--- Remove Student ---");
        int index = PromptAndFindStudentIndex(studentList);
        if (index != -1) {
            StudentRecord student = studentList.get(index);
            System.out.printf("Found student: %s %s%n", student.firstName, student.lastName);
            String confirmation = GetYesNo("Are you sure you want to remove? (Y/N): ");
            if (confirmation.equals("Y")) {
                studentList.remove(index);
                System.out.println("\nStudent removed successfully.");
            } else {
                System.out.println("\nRemoval cancelled.");
            }
        }
    }

    public static String GetString(String promptMessage) {
        System.out.print(promptMessage);
        return scanner.nextLine();
    }

    public static int GetValidInteger(String promptMessage) {
        boolean valid = false;
        int number = 0;
        while (!valid) {
            System.out.print(promptMessage);
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

    public static int GetValidIntegerRange(String promptMessage, int min, int max) {
        int number;
        while (true) {
            number = GetValidInteger(promptMessage);
            if (number >= min && number <= max) {
                return number;
            } else {
                System.out.printf("Invalid choice. Please enter a number between %d and %d.%n", min, max);
            }
        }
    }

    public static float GetValidFloat(String promptMessage) {
        boolean valid = false;
        float number = 0.0f;
        while (!valid) {
            System.out.print(promptMessage);
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

    public static String GetYesNo(String promptMessage) {
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

    public static int FindStudentIndexByID(List<StudentRecord> studentList, int idToFind) {
        int index = 0;
        while (index < studentList.size()) {
            if (studentList.get(index).id == idToFind) {
                return index;
            }
            index = index + 1;
        }
        return -1;
    }

    public static int PromptAndFindStudentIndex(List<StudentRecord> studentList) {
        int idToFind = GetValidInteger("Enter Student ID: ");
        int index = FindStudentIndexByID(studentList, idToFind);
        if (index == -1) {
            System.out.printf("%nStudent with ID %d not found.%n", idToFind);
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
        int numCourses = GetValidInteger("Enter Number of Courses: ");
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
    }

    public static void ModifyStudentMenu(List<StudentRecord> studentList, int index) {
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

            int fieldChoice = GetValidIntegerRange("Enter field to modify: ", 0, 6);

            if (fieldChoice == 1) {
                String newVal = GetString("Enter new First Name: ");
                student.firstName = newVal;
                System.out.println("First Name updated.");
            } else if (fieldChoice == 2) {
                String newVal = GetString("Enter new Last Name: ");
                student.lastName = newVal;
                System.out.println("Last Name updated.");
            } else if (fieldChoice == 3) {
                float newVal = GetValidFloat("Enter new GPA: ");
                student.gpa = newVal;
                System.out.println("GPA updated.");
            } else if (fieldChoice == 4) {
                String newVal = GetString("Enter new Semester: ");
                student.semester = newVal;
                System.out.println("Semester updated.");
            } else if (fieldChoice == 5) {
                String newVal = GetString("Enter new Program: ");
                student.program = newVal;
                System.out.println("Program updated.");
            } else if (fieldChoice == 6) {
                int newVal = GetValidInteger("Enter new Number of Courses: ");
                student.numCourses = newVal;
                System.out.println("Number of courses updated.");
            } else if (fieldChoice == 0) {
                modifying = false;
            } 
        }
    }
}