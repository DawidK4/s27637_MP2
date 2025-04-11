import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Librarian {
    private static int idCounter = 1;

    private int id;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private LocalDate hireDate;

    public Librarian(String name, String surname, String email, String telephone, LocalDate hireDate) {
        this.id = idCounter++;
        setName(name);
        setSurname(surname);
        setEmail(email);
        setTelephone(telephone);
        setHireDate(hireDate);
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
        this.name = name.trim();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty!");
        }
        this.surname = surname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty!");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Please provide a valid e-mail!");
        }
        this.email = email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if (telephone == null || !isValidPhoneNumber(telephone)) {
            throw new IllegalArgumentException("Telephone number should contain 9 digits without any spaces!");
        }
        this.telephone = telephone;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        if (hireDate == null || hireDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Hire date cannot be null or in the future!");
        }
        this.hireDate = hireDate;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("^\\d{9}$");
    }
}
