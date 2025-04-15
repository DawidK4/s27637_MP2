package uni;

import utils.ObjectPlusPlus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Subject extends ObjectPlusPlus {
    private String name;
    private String lecturerName;
    private Set<SubjectStudent> isAttendedBy = new HashSet<>();

    public Subject(String name, String lecturerName) {
        super();
        setName(name);
        setLecturerName(lecturerName);
    }

    // add students
    public void addStudent(Student newStudent, int finalGrade, LocalDate dateOfEnrollment) {
        SubjectStudent subjectStudent = new SubjectStudent(newStudent, this, finalGrade, dateOfEnrollment);
        isAttendedBy.add(subjectStudent);
        newStudent.addSubject(subjectStudent);
    }

    // helper method
    public void addStudent(SubjectStudent subjectStudent){
        isAttendedBy.add(subjectStudent);
    }

    public void deleteStudent(Student student) {
        SubjectStudent toRemove = null;

        for (SubjectStudent ss : isAttendedBy) {
            if (ss.getStudent().equals(student)) {
                toRemove = ss;
                break;
            }
        }

        if (toRemove != null) {
            isAttendedBy.remove(toRemove);        // 1. subject -> subjectStudent
            student.removeSubjectStudent(toRemove); // 2. student -> subjectStudent

            toRemove.clearReferences();           // 3. subjectStudent -> nullify student & subject
        }
    }


    public Set<Student> getStudents() {
        Set<Student> temp = new HashSet<>();
        isAttendedBy.forEach(e -> temp.add(e.getStudent()));
        return Collections.unmodifiableSet(temp);
    }

    void removeSubjectStudent(SubjectStudent ss) {
        isAttendedBy.remove(ss);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name must not be null or empty!");

        if (name.length() < 2) throw new IllegalArgumentException("Name must contain at least 2 characters!");

        this.name = name;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        if (lecturerName == null || lecturerName.isEmpty()) throw new IllegalArgumentException("Lecturer's name must not be null or empty!");

        if (lecturerName.length() < 3) throw new IllegalArgumentException("Surname must contain at least 3 characters!");

        this.lecturerName = lecturerName;
    }
}
