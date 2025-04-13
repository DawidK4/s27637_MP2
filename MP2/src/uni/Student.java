package uni;

import utils.ObjectPlusPlus;

import java.util.HashSet;
import java.util.Set;

public class Student extends ObjectPlusPlus {
    private String indexNum;
    private static Set<String> allIndexNum = new HashSet<>();
    private String name;
    private String surname;
    private Set<SubjectStudent> attends = new HashSet<>();

    public Student(String indexNum, String name, String surname) {
        setIndexNum(indexNum);
        setName(name);
        setSurname(surname);
    }

    public void addSubject(SubjectStudent subject) throws Exception {
        if (subject == null) throw new IllegalArgumentException("Subject must not be null!");

        if (!attends.contains(subject)) {
            attends.add(subject);
            subject.setStudent(this);
        } else {
            throw new Exception("Student is already assigned to the subject!");
        }
    }

    public void deleteSubject(SubjectStudent subject) {
        if (subject == null) throw new IllegalArgumentException("Subject must not be null!");
        attends.remove(subject);
    }

    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        if (indexNum == null || indexNum.isEmpty()) {
            throw new IllegalArgumentException("Index number must not be null or empty!");
        }

        if (allIndexNum.contains(indexNum)) {
            throw new IllegalArgumentException("This index number already exists!");
        }

        this.indexNum = indexNum;
        allIndexNum.add(indexNum);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name must not be null or empty!");

        if (name.length() < 3) throw new IllegalArgumentException("Name must contain at least 3 characters!");

        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isEmpty()) throw new IllegalArgumentException("Surname must not be null or empty!");

        if (surname.length() < 3) throw new IllegalArgumentException("Surname must contain at least 3 characters!");

        this.surname = surname;
    }
}
