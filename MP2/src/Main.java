    import bank.Banker;
import bank.Client;
import uni.Student;
import uni.Subject;
import uni.SubjectStudent;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // bank
        Banker banker = new Banker(1, "Kowalski", "Jan");
        Client client = new Client("00000", "Dawid", "Kucharski", banker);

        // uni
        Student student = new Student("s123", "Anna", "Kowalska");
        Subject subject = new Subject("Math", "Dr. Nowak");

        SubjectStudent subjectStudent = new SubjectStudent(student, subject, 5, LocalDate.now());

        try {
            student.addSubject(subjectStudent);
            subject.addStudent(subjectStudent);
        } catch (Exception e){
            e.printStackTrace();
        }

        subjectStudent.removeAssociation();

        System.out.println("After removing association:");
        System.out.println(" - Student: " + subjectStudent.getStudent());
        System.out.println(" - Subject: " + subjectStudent.getSubject());
    }
}
