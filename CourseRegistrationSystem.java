package nitesh.codsoft;

import java.util.*;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public void registerStudent(String studentId) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentId);
            System.out.println("Student " + studentId + " registered for course " + code);
        } else {
            System.out.println("Course " + code + " is already full. Registration failed for student " + studentId);
        }
    }

    public void removeStudent(String studentId) {
        registeredStudents.remove(studentId);
        System.out.println("Student " + studentId + " removed from course " + code);
    }
}

class Student {
    private String id;
    private String name;
    private List<String> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
        System.out.println("Student " + id + " registered for course " + courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
        System.out.println("Student " + id + " dropped from course " + courseCode);
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourseListing() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getCapacity() + " slots available)");
        }
    }

    public void displayStudentRegistration(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                System.out.println("Registered Courses for Student " + studentId + ":");
                for (String courseCode : student.getRegisteredCourses()) {
                    System.out.println(courseCode + " - " + findCourseByCode(courseCode).getTitle());
                }
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();

        // Create courses
        Course course1 = new Course("CS101", "Introduction to Computer Science", "Fundamental concepts of computer science", 30, Arrays.asList("Mon", "Wed", "Fri"));
        Course course2 = new Course("MATH201", "Calculus I", "Introduction to differential and integral calculus", 25, Arrays.asList("Tue", "Thu"));
        Course course3 = new Course("ENG101", "English Composition", "Developing writing skills", 20, Arrays.asList("Mon", "Wed"));

        // Create students
        Student student1 = new Student("S001", "John");
        Student student2 = new Student("S002", "Alice");

        // Add courses and students to the system
        registrationSystem.addCourse(course1);
        registrationSystem.addCourse(course2);
        registrationSystem.addCourse(course3);

        registrationSystem.addStudent(student1);
        registrationSystem.addStudent(student2);

        // Register students for courses
        student1.registerCourse(course1.getCode());
        student1.registerCourse(course2.getCode());
        student2.registerCourse(course2.getCode());

        // Drop a course for a student
        student1.dropCourse(course2.getCode());

        // Display available courses
        registrationSystem.displayCourseListing();

        // Display registered courses for a student
        registrationSystem.displayStudentRegistration(student1.getId());
    }
}
