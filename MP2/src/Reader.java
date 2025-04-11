import java.time.LocalDate;
import java.util.regex.Pattern;

public class Reader {
    private static int idCounter = 1;

    private int id;
    private String name;
    private String surname;
    private String email;
    private String pesel;
    private String readerCardNum;
    private LocalDate registrationDate;

    public Reader(String name, String surname, String email, String pesel, String readerCardNum, LocalDate registrationDate) {
        this.id = idCounter++;
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPesel(pesel);
        setReaderCardNum(readerCardNum);
        setRegistrationDate(registrationDate);
    }

    public int getId() {
        return id;
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
