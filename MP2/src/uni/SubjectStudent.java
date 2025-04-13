package uni;

import java.time.LocalDate;

public class SubjectStudent {
    private Student student;
    private Subject subject;
    private int finalGrade;
    private LocalDate dateOfEnrollment;

    public SubjectStudent(Student student, Subject subject, int finalGrade, LocalDate dateOfEnrollment) {
        setStudent(student);
        setSubject(subject);
        setFinalGrade(finalGrade);
        setDateOfEnrollment(dateOfEnrollment);
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

    public int getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(int finalGrade) {
        if (finalGrade < 2 || finalGrade > 5) throw new IllegalArgumentException("Final grade must be greater that 1 and less than 6!");

        this.finalGrade = finalGrade;
    }

    public LocalDate getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(LocalDate dateOfEnrollment) {
        if (LocalDate.now().isBefore(dateOfEnrollment)) {
            throw new IllegalArgumentException("Date of enrollment cannot be in the furture!");
        }

        this.dateOfEnrollment = dateOfEnrollment;
    }
}
