import java.time.LocalDate;
import java.util.regex.Pattern;

public class Reader {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String pesel;
    private String readerCardNum;
    private LocalDate registrationDate;

    public Reader(int id, String name, String surname, String email, String pesel, String readerCardNum, LocalDate registrationDate) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setEmail(email);
        this.setPesel(pesel);
        this.setReaderCardNum(readerCardNum);
        this.setRegistrationDate(registrationDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id; // uniqueness can be handled elsewhere if needed
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty!");
        }
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty!");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format!");
        }
        this.email = email;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        if (pesel == null || !pesel.matches("^\\d{11}$")) {
            throw new IllegalArgumentException("PESEL must be exactly 11 digits!");
        }
        this.pesel = pesel;
    }

    public String getReaderCardNum() {
        return readerCardNum;
    }

    public void setReaderCardNum(String readerCardNum) {
        if (readerCardNum == null || readerCardNum.trim().isEmpty()) {
            throw new IllegalArgumentException("Reader Card Number cannot be null or empty!");
        }
        this.readerCardNum = readerCardNum;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        if (registrationDate == null || registrationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Registration date cannot be in the future!");
        }
        this.registrationDate = registrationDate;
    }

    private static boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }
}
