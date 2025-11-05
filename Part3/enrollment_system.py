# SAIT Enrollment Management System

# Data Structure: Defines the properties for a single student record
class StudentRecord:
    def __init__(self, id, first_name, last_name, dob, gender, gpa, semester, program, num_courses):
        self.id = id
        self.first_name = first_name
        self.last_name = last_name
        self.dob = dob
        self.gender = gender
        self.gpa = gpa
        self.semester = semester
        self.program = program
        self.num_courses = num_courses


# Helper: Loops until a valid integer is entered
def get_valid_integer(prompt_message):
    valid = False
    number = None

    while valid == False:
        user_input = input(prompt_message)

        try:
            number = int(user_input)
            valid = True
        except ValueError:
            print("Invalid input. Please enter a whole number.")
    
    return number


# Helper: Loops until a valid integer in a specific range is entered
def get_valid_integer_range(prompt_message, min, max):
    number = None

    while True:
        number = get_valid_integer(prompt_message)

        if min <= number <= max:
            return number
        else:
            print("Invalid choice. Please enter a number between " + str(min) + " and " + str(max) + ".")


# Helper: Loops until a valid float is entered
def get_valid_float(prompt_message):
    valid = False
    number = None

    while valid == False:
        user_input = input(prompt_message)
        
        try:
            number = float(user_input)
            valid = True
        except ValueError:
            print("Invalid input. Please enter a number (e.g., 3.5).")
    
    return number


# Helper: Loops until 'Y' or 'N' is entered
def get_yes_no(prompt_message):
    while True:
        user_input = input(prompt_message).upper()

        if user_input == "Y" or user_input == "N":
            return user_input
        else:
            print("Invalid input. Please enter 'Y' or 'N'.")


# Helper: Finds a student's index by ID
def find_student_index_by_id(student_list, id_to_find):
    index = 0

    while index < len(student_list):
        if student_list[index].id == id_to_find:
            return index
        index = index + 1

    return -1


# Helper: Prompts for an ID and finds the student
def prompt_and_find_student_index(student_list):
    id_to_find = get_valid_integer("Enter Student ID: ")
    index = find_student_index_by_id(student_list, id_to_find)

    if index == -1:
        print("Student with ID " + str(id_to_find) + " not found.")

    return index


# Helper: Creates a new student record
def create_new_student(id):
    first_name = input("Enter First Name: ")
    last_name = input("Enter Last Name: ")
    dob = input("Enter Date of Birth (YYYY-MM-DD): ")
    gender = input("Enter Gender: ")
    gpa = get_valid_float("Enter Previous GPA: ")
    semester = input("Enter Current Semester: ")
    program = input("Enter Program: ")
    num_courses = get_valid_integer("Enter Number of Courses: ")

    new_student = StudentRecord(id, first_name, last_name, dob, gender, gpa, semester, program, num_courses)
    
    return new_student


# 1. Add Student
def add_student(student_list, next_student_id):
    print("\n--- Add New Student ---")

    student_to_add = create_new_student(next_student_id)
    student_list.append(student_to_add)

    print("Student added successfully with ID:" + str(student_to_add.id))

    next_student_id = next_student_id + 1

    return next_student_id


# 2. Display Students
def display_students(student_list):
    if len(student_list) == 0:
        print("\nNo students in the system.")
    else:
        print("\n--- List of Enrolled Students ---")

        for student in student_list:
            print("\nStudent ID: " + str(student.id) +
                "\n   Name: " + student.first_name + " " + student.last_name +
                "\n   DOB: " + student.dob +
                "\n   Gender: " + student.gender +
                "\n   GPA: " + str(student.gpa) +
                "\n   Program: " + student.program +
                "\n   Semester: " + student.semester +
                "\n   Courses: " + str(student.num_courses))


# 3. Modify Student
def modify_student(student_list):
    print("\n--- Modify Student Record ---")

    index = prompt_and_find_student_index(student_list)

    if index != -1:
        modify_student_menu(student_list, index)
        print("Modifications saved.")


# 4. Remove Student
def remove_student(student_list):
    print("\n--- Remove Student ---")

    index = prompt_and_find_student_index(student_list)

    if index != -1:
        student = student_list[index]
        print("Found student:" + student.first_name + " " + student.last_name)
        
        confirmation = get_yes_no("Are you sure you want to remove? (Y/N): ")

        if confirmation == "Y":
            student_list.pop(index)
            print("Student removed successfully.")
        else:
            print("Removal cancelled.")


# Helper: Modification sub-menu
def modify_student_menu(student_list, index):
    modifying = True

    while modifying:
        student = student_list[index]
        print("\n--- Modifying Student: " + student.first_name + " " + student.last_name + " ---" +
            "\n1. First Name (" + student.first_name + ")" +
            "\n2. Last Name (" + student.last_name + ")" +
            "\n3. GPA (" + str(student.gpa) + ")" +
            "\n4. Semester (" + student.semester + ")" +
            "\n5. Program (" + student.program + ")" +
            "\n6. Number of Courses (" + str(student.num_courses) + ")" +
            "\n0. Finish Modifying")

        field_choice = get_valid_integer_range("Enter field to modify: ", 0, 6)

        if field_choice == 1:
            new_value = input("Enter new First Name: ")
            student_list[index].first_name = new_value
            print("First Name updated.")
        elif field_choice == 2:
            new_value = input("Enter new Last Name: ")
            student_list[index].last_name = new_value
            print("Last Name updated.")
        elif field_choice == 3:
            new_value = get_valid_float("Enter new GPA: ")
            student_list[index].gpa = new_value
            print("GPA updated.")
        elif field_choice == 4:
            new_value = input("Enter new Semester: ")
            student_list[index].semester = new_value
            print("Semester updated.")
        elif field_choice == 5:
            new_value = input("Enter new Program: ")
            student_list[index].program = new_value
            print("Program updated.")
        elif field_choice == 6:
            new_value = get_valid_integer("Enter new Number of Courses: ")
            student_list[index].num_courses = new_value
            print("Number of courses updated.")
        else:
            modifying = False


# Main Driver
def main():
    student_list = []
    next_student_id = 1000

    running = True
    while running:
        print("\n--- SAIT Enrollment System ---\n" \
        "1. Add New Student\n" \
        "2. Display All Students\n" \
        "3. Modify Student Record\n" \
        "4. Remove Student\n" \
        "0. Exit")

        user_choice = get_valid_integer_range("Enter your choice: ", 0, 4)

        if user_choice == 1:
            next_student_id = add_student(student_list, next_student_id)
        elif user_choice == 2:
            display_students(student_list)
        elif user_choice == 3:
            modify_student(student_list)
        elif user_choice == 4:
            remove_student(student_list)
        else:
            running = False
            print("\nExiting program. Goodbye!")


# Run program
if __name__ == "__main__":
    main()
