import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Librarian {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private LocalDate hireDate;

    public Librarian(int id, String name, String surname, String email, String telephone, LocalDate hireDate) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setHireDate(hireDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id; // You can later implement uniqueness validation if needed
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty!");
        }
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty!");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Please provide a valid e-mail!");
        }
        this.email = email;
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
        if (hireDate == null || LocalDate.now().isBefore(hireDate)) {
            throw new IllegalArgumentException("Hire date cannot be null or greater than today!");
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
