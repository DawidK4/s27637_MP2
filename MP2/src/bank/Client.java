package bank;

import utils.ObjectPlusPlus;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Client extends ObjectPlusPlus {
    private String clientNumber;
    private String name;
    private String surname;
    private Banker isServedBy;
    private Set<PersonalAccount> owns;
    private Bank deals;

    public Client(String clientNumber, String name, String surname, Banker isServedBy) {
        setClientNumber(clientNumber);
        setName(name);
        setSurname(surname);
        setBanker(isServedBy);
        this.owns = new HashSet<>();
    }

    public PersonalAccount createPersonalAccount(String accountNumber, double balance) {
        for (PersonalAccount pa : owns) {
            if (pa.getAccountNumber().equals(accountNumber)) {
                throw new IllegalArgumentException("Account with provided number already exists!");
            }
        }

        PersonalAccount personalAccount = new PersonalAccount(accountNumber, balance, this);
        owns.add(personalAccount);
        return personalAccount;
    }

    public Set<PersonalAccount> getPersonalAccounts() {
        return Collections.unmodifiableSet(this.owns);
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

    // Association with Bank
    public Bank getBank() {
        return this.deals;
    }

    public void setBank(Bank newBank) {
        if (newBank == null) throw new IllegalArgumentException("New bank must not be null!");
        this.deals = newBank;
    }

    public void removeBank() {
        this.deals.removeClient(this.clientNumber);
        this.deals = null;
    }

    public void deleteClient() {
        for (PersonalAccount account : owns) {
            account.removeAssociation();
        }
        owns.clear();
    }

    public void deletePersonalAccountByAccountNumber(String accountNumber) {
        for (PersonalAccount account : owns) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.removeAssociation();
                owns.remove(account);
                break;
            }
        }
    }

    // PersonalAccount class (composed part of Client)
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
            setClient(isOwnedBy);
        }

        public Client getClient() {
            return this.isOwnedBy;
        }

        public void setClient(Client client) {
            if (client == null) throw new IllegalArgumentException("Client cannot be null!");
            this.isOwnedBy = client;
        }

        public void removeAssociation() {
            accountNumbers.remove(accountNumber);
            this.isOwnedBy = null;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            if (accountNumber == null || accountNumber.length() < 5 || accountNumber.length() > 20) {
                throw new IllegalArgumentException("Account number must be between 5 and 20 characters");
            }
            this.accountNumber = accountNumber;
            accountNumbers.add(accountNumber);
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
