package uni;

import utils.ObjectPlusPlus;

import java.util.HashSet;
import java.util.Set;

public class Subject extends ObjectPlusPlus {
    private String name;
    private String lecturerName;
    private Set<SubjectStudent> isAttendedBy = new HashSet<>();

    public Subject(String name, String lecturerName) {
        setName(name);
        setLecturerName(lecturerName);
    }

    public void addStudent(SubjectStudent student) throws Exception{
        if (student == null) throw new IllegalArgumentException("Student must not be null!");

        if (!isAttendedBy.contains(student)){
            isAttendedBy.add(student);
            student.setSubject(this);
        } else {
            throw new Exception("Student is already assigned to the subject!");
        }
    }

    public void deleteStudent(SubjectStudent student){
        if (student == null) throw new IllegalArgumentException("Student must not be null!");
        isAttendedBy.remove(student);
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
