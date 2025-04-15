import bank.Bank;
import bank.Banker;
import bank.Client;
import uni.Student;
import uni.Subject;
import uni.SubjectStudent;
import utils.ObjectPlus;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
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

        System.out.println("Deletion of a part if a whole is deleted and removal from extension - works");
        Banker banker1 = new Banker(2, "Anna", "Nowak");
        Client client1 = new Client("C12345", "Jan", "Kowalski", banker1);

        client1.createPersonalAccount("PA001", 1000.0);
        client1.createPersonalAccount("PA002", 2500.0);

        System.out.println(">>> Personal accounts before deleting client:");
        ObjectPlus.showExtent(Client.PersonalAccount.class);

        client1.deleteClient();

        System.out.println("\n>>> Personal accounts after deleting client:");
        ObjectPlus.showExtent(Client.PersonalAccount.class);
        System.out.println("");

        System.out.println("Client 0..* -------- 1 Banker Association: ");
        Banker banker2 = client1.getBanker();
        System.out.println("Banker name: " + banker2.getName());
        System.out.println("Deletion of a client - works in both ways");
        banker2.removeClient(client1);
        Set<Client> clients = banker2.getClients();
        clients.forEach(c -> System.out.println(c.getClientNumber()));
        try {
                System.out.println(client1.getBanker().getId());
        } catch (NullPointerException e){
                System.out.println("Deletion works in both ways!");
        }

        System.out.println("Assigning a new Banker - works");
        client1.setBanker(banker2);
        System.out.println(client1.getBanker().getId());
        Set<Client> clients1 = banker2.getClients();
        clients1.forEach(c -> System.out.println(c.getClientNumber()));
        System.out.println("");

        System.out.println("Association with a key");
        System.out.println("Add a client to the bank via Bank - works");
        Bank bank = new Bank("098765", "Bank");
        bank.addClient(client1);
        System.out.println(client1.getBank().getSwiftNumber());
        Map<String, Client> clientMap = bank.getClients();
            for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
                    String clientNumber = entry.getKey();
                    Client client0 = entry.getValue();

                    System.out.println("Client number: " + clientNumber);
                    System.out.println("Client name: " + client0.getName() + " " + client0.getSurname());
            }

        System.out.println("Removing a client via Bank - works");
        bank.removeClient("C12345");
            Map<String, Client> clientMap1 = bank.getClients();
            for (Map.Entry<String, Client> entry : clientMap.entrySet()) {
                    String clientNumber = entry.getKey();
                    Client client0 = entry.getValue();

                    System.out.println("Client number: " + clientNumber);
                    System.out.println("Client name: " + client0.getName() + " " + client0.getSurname());
            }

        System.out.println("Testing ObjectPlus and Serialization: ");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("extents.ser"))) {
                ObjectPlus.writeExtents(out);
        } catch (IOException e) {
                e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("extents.ser"))) {
                ObjectPlus.readExtents(in);
        } catch (IOException e) {
                e.printStackTrace();
        }

        ObjectPlus.showExtent(Client.class);
        ObjectPlus.showExtent(Bank.class);
        ObjectPlus.showExtent(Banker.class);
        ObjectPlus.showExtent(Student.class);
        ObjectPlus.showExtent(Subject.class);
        ObjectPlus.showExtent(SubjectStudent.class);
    }
}
