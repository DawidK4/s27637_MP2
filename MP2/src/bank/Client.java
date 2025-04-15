package bank;

import utils.ObjectPlus;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Client extends ObjectPlus implements Serializable {
    private final String clientNumber;
    private String name;
    private String surname;
    private Banker isServedBy;
    private Set<PersonalAccount> owns;
    private Bank deals;

    public Client(String clientNumber, String name, String surname, Banker isServedBy) {
        super();
        if (clientNumber == null || clientNumber.length() < 5 || clientNumber.length() > 15) {
            throw new IllegalArgumentException("Client number must be between 5 and 15 characters");
        }
        this.clientNumber = clientNumber;

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

    public PersonalAccount getPersonalAccount(String accountNumber) {
        PersonalAccount personalAccount = null;
        for (PersonalAccount pa : owns) {
            if (pa.getAccountNumber().equals(accountNumber)) {
                personalAccount = pa;
                break;
            }
        }

        return personalAccount;
    }

    public Set<PersonalAccount> getPersonalAccounts() {
        return Collections.unmodifiableSet(this.owns);
    }

    public String getClientNumber() {
        return clientNumber;
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
        if (this.isServedBy == newBanker) return;

        if (this.isServedBy != null) {
            this.isServedBy.removeClient(this);
        }

        this.isServedBy = newBanker;

        if (newBanker != null && !newBanker.getClients().contains(this)) {
            newBanker.addClient(this);
        }
    }

    public void removeBanker() {
        if (isServedBy != null) {
            Banker oldBanker = this.isServedBy;
            this.isServedBy = null;
            oldBanker.removeClient(this);
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
        this.deals = null;
    }

    public void deleteClient() {
        for (PersonalAccount account : owns) {
            account.removeAssociation();
            ObjectPlus.removeFromExtent(account);
        }
        owns.clear();
    }

    public void deletePersonalAccountByAccountNumber(String accountNumber) {
        Iterator<PersonalAccount> it = owns.iterator();
        while (it.hasNext()) {
            PersonalAccount acc = it.next();
            if (acc.getAccountNumber().equals(accountNumber)) {
                acc.removeAssociation();
                it.remove();
                break;
            }
        }
    }

    // PersonalAccount class (composed part of Client)
    public class PersonalAccount extends ObjectPlus implements Serializable{
        private static final Set<String> accountNumbers = new HashSet<>();
        private String accountNumber;
        private double balance;
        private Client isOwnedBy;

        public PersonalAccount(String accountNumber, double balance, Client isOwnedBy) {
            super();
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

        private void setClient(Client client) {
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
