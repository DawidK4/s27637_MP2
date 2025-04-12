package Bank;

public class Client {

    private String clientNumber;
    private String name;
    private String surname;
    private Banker isServedBy;

    public Client(String clientNumber, String name, String surname, Banker isServedBy) {
        setClientNumber(clientNumber);
        setName(name);
        setSurname(surname);
        setBanker(isServedBy);
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        if (clientNumber == null || clientNumber.length() < 5 || clientNumber.length() > 15) {
            throw new IllegalArgumentException("Client number must be between 5 and 15 characters");
        }
        this.clientNumber = clientNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Name must be between 2 and 50 characters");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.length() < 2 || surname.length() > 50) {
            throw new IllegalArgumentException("Surname must be between 2 and 50 characters");
        }
        this.surname = surname;
    }

    // Association
    public Banker getBanker() {
        return isServedBy;
    }

    public void setBanker(Banker newBanker){
        if (!(this.isServedBy == null)){
            newBanker.removeClient(this);
        }

        this.isServedBy = newBanker;
    }
}
