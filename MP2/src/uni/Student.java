package uni;

import utils.ObjectPlusPlus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Student extends ObjectPlusPlus {
    private String indexNum;
    private static Set<String> allIndexNum = new HashSet<>();
    private String name;
    private String surname;
    private Set<SubjectStudent> attends = new HashSet<>();

    public Student(String indexNum, String name, String surname) {
        super();
        setIndexNum(indexNum);
        setName(name);
        setSurname(surname);
    }

    //add subject
    public void addSubject(Subject newSubject, int finalGrade, LocalDate dateOfEnrollment) {
        SubjectStudent subjectStudent = new SubjectStudent(this, newSubject, finalGrade, dateOfEnrollment);
        attends.add(subjectStudent);
        newSubject.addStudent(subjectStudent);
    }

    // helper method
    public void addSubject(SubjectStudent subjectStudent){
        attends.add(subjectStudent);
    }

    public Set<Subject> getSubjects() {
        Set<Subject> temp = new HashSet<>();
        attends.forEach(e -> temp.add(e.getSubject()));
        return Collections.unmodifiableSet(temp);
    }

    public void deleteSubject(Subject subject) {
        SubjectStudent toRemove = null;

        for (SubjectStudent s : attends) {
            if (s.getSubject().equals(subject)) {
                toRemove = s;
                break;
            }
        }

        if (toRemove != null) {
            attends.remove(toRemove);              // 1. student -> subjectStudent
            subject.removeSubjectStudent(toRemove); // 2. subject -> subjectStudent

            toRemove.clearReferences();             // 3. subjectStudent -> student i subject
        }
    }

    void removeSubjectStudent(SubjectStudent ss) {
        attends.remove(ss);
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
