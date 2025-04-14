import bank.Banker;
import bank.Client;
import uni.Student;
import uni.Subject;
import uni.SubjectStudent;

import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Association with attribute
        Student student = new Student("00000", "Dawid", "Kucharski");
        Subject subject = new Subject("MAS", "Trzaska");

        student.addSubject(subject, 5, LocalDate.now());
        // subject.addStudent(student)

        Set<Subject> subjects = student.getSubjects();
        Set<Student> students = subject.getStudents();

        subjects.forEach(s -> System.out.println(s.getName()));
        students.forEach(s -> System.out.println(s.getName()));

        System.out.println("Testing deletion in Association with attribute");
//        student.deleteSubject(subject);
//        Set<Subject> subjects1 = student.getSubjects();
//        subjects1.forEach(s -> System.out.println(s.getName()));
        subject.deleteStudent(student);
        Set<Student> students1 = subject.getStudents();
        students1.forEach(s -> System.out.println(s.getName()));
    }
}
