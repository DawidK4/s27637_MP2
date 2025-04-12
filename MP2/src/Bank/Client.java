package Bank;

import java.util.HashSet;
import java.util.Set;

public class Client {
    private String clientNumber;
    private String name;
    private String surname;
    private Banker isServedBy;
    private Set<PersonalAccount> owns;

    public Client(String clientNumber, String name, String surname, Banker isServedBy) {
        setClientNumber(clientNumber);
        setName(name);
        setSurname(surname);
        setBanker(isServedBy);
    }

    public PersonalAccount createPersonalAccount(String accountNumber, double balance) {
        PersonalAccount personalAccount = new PersonalAccount(accountNumber, balance, this);
        owns.add(personalAccount);

        return personalAccount;
    }

    // Add account number validation, simply refactor
    public PersonalAccount getPersonalAccount(String accountNumber) {
        for (PersonalAccount pa : owns) {
            if (pa.getAccountNumber().equals(accountNumber)) return pa;
        }
        // has to be fixed
        return null;
    }

    public void deletePersonalAccount(String accountNumber) {
        for (PersonalAccount pa : owns) {
            if (pa.getAccountNumber().equals(accountNumber)) {
                owns.remove(pa);
                break;
            }
        }
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

    // Association with Banker
    public Banker getBanker() {
        return isServedBy;
    }

    public void setBanker(Banker newBanker) {
        // Prevents the loop
        if (this.isServedBy != null && !this.isServedBy.equals(newBanker)) {
            this.isServedBy.removeClient(this);
        }

        if (newBanker != null) {
            this.isServedBy = newBanker;
            newBanker.addClient(this);
        } else {
            throw new IllegalArgumentException("New banker must not be null!");
        }
    }

    // Association with PersonalAccount
    public class PersonalAccount {
        private static Set<String> accountNumbers = new HashSet<>();
        private String accountNumber;
        private double balance;
        private Client isOwnedBy;

        public PersonalAccount(String accountNumber, double balance, Client isOwnedBy) {
            for (String number : accountNumbers) {
                if (number.equals(accountNumber)) throw new IllegalArgumentException("Account with provided number already exists!");
            }

            setAccountNumber(accountNumber);
            setBalance(balance);
        }

        public Client getClient() {
            return this.isOwnedBy;
        }

        public void setClient(Client client){
            if (client == null) throw new IllegalArgumentException("Client cannot be null!");
            this.isOwnedBy = client;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            if (accountNumber == null || accountNumber.length() < 5 || accountNumber.length() > 20) {
                throw new IllegalArgumentException("Account number must be between 5 and 20 characters");
            }
            this.accountNumber = accountNumber;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            if (balance < 0) {
                throw new IllegalArgumentException("Balance cannot be negative");
            }
            this.balance = balance;
        }
    }
}
