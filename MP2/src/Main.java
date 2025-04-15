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
        System.out.println("Association with attribute:");
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
        System.out.println("");

        //
        System.out.println("Composition:");
        Banker banker = new Banker(1, "Tomasz", "Kowalski");
        Client client = new Client("00000", "Dawid", "Kucharski", banker);

        System.out.println("Creation of personal account - works");
        client.createPersonalAccount("000000", 200.50);
        Set<Client.PersonalAccount> personalAccounts = client.getPersonalAccounts();
        personalAccounts.forEach(pa -> System.out.println(pa.getAccountNumber() +
                ": " + pa.getBalance()));

        System.out.println("Deletion of personal account - works");
//        Client.PersonalAccount personalAccount = client.getPersonalAccount("000000");
//        client.deletePersonalAccountByAccountNumber("000000");
//        Set<Client.PersonalAccount> accountsAfterDeletion = client.getPersonalAccounts();
//        accountsAfterDeletion.forEach(acc -> System.out.println(acc.getAccountNumber() +
//                ": " + acc.getBalance()));

        System.out.println("Deletion of a part if a whole is deleted and removal from extension");

    }
}
