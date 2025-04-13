package uni;

public class SubjectStudent {
    private Student student;
    private Subject subject;

    public SubjectStudent(Student student, Subject subject) {
        setStudent(student);
        setSubject(subject);
    }

    public void removeAssociation() {
        Student oldStudent = this.student;
        Subject oldSubject = this.subject;

        this.student = null;
        this.subject = null;

        if (oldStudent != null) {
            oldStudent.deleteSubject(this);
        }

        if (oldSubject != null) {
            oldSubject.deleteStudent(this);
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("Student must not be null!");

        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        if (subject == null) throw new IllegalArgumentException("Subject must not be null!");

        this.subject = subject;
    }
}
