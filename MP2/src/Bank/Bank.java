package Bank;

public class Bank {
    private String name;

    public Bank(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty!");
        }

        if (name.length() < 3) {
            throw new IllegalArgumentException("Name of the bank must contain at least 3 letters!");
        }

        this.name = name;
    }
}
