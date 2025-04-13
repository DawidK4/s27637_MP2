import bank.Banker;
import bank.Client;
import uni.Student;
import uni.Subject;
import uni.SubjectStudent;

public class Main {
    public static void main(String[] args) {
        // bank
        Banker banker = new Banker(1, "Kowalski", "Jan");
        Client client = new Client("00000", "Dawid", "Kucharski", banker);

        // uni
        // Create a student
        Student student = new Student("s123", "Anna", "Kowalska");

        // Create a subject
        Subject subject = new Subject("Math", "Dr. Nowak");

        // Create an association
        SubjectStudent subjectStudent = new SubjectStudent(student, subject);

        // Add subject to student
        student.addSubject(subjectStudent);

        // Add student to subject
        subject.addStudent(subjectStudent);

        // Print basic info
        System.out.println("Student: " + student.getName() + " " + student.getSurname());
        System.out.println("Subject: " + subject.getName() + " by " + subject.getLecturerName());
        System.out.println("SubjectStudent links: ");
        System.out.println(" - Student: " + subjectStudent.getStudent().getIndexNum());
        System.out.println(" - Subject: " + subjectStudent.getSubject().getName());

        // Test removing the association
        subjectStudent.removeAssociation();

        System.out.println("After removing association:");
        System.out.println(" - Student: " + subjectStudent.getStudent()); // should be null
        System.out.println(" - Subject: " + subjectStudent.getSubject()); // should be null
    }
}
