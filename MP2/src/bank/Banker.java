package bank;

import utils.ObjectPlus;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Banker extends ObjectPlus implements Serializable {
    private int id;
    private String name;
    private String surname;
    private Set<Client> serves;

    public Banker(int id, String surname, String name, Set<Client> clients) {
        super();
        setId(id);
        setSurname(surname);
        setName(name);
        this.serves = new HashSet<>(clients);
    }

    public Banker(int id, String name, String surname, Client client) {
        setId(id);
        setName(name);
        setSurname(surname);
        this.serves = new HashSet<>();
        addClient(client);
    }

    public Banker(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.serves = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
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

    // Association with Client
    public Set<Client> getClients() {
        return Collections.unmodifiableSet(serves);
    }

    public void addClient(Client newClient) {
        if (newClient == null) {
            throw new IllegalArgumentException("New client must not be null!");
        }

        if (!serves.contains(newClient)) {
            serves.add(newClient);
            newClient.setBanker(this);
        }
    }

    public void removeClient(Client oldClient) {
        if (oldClient == null) return;
        if (serves.contains(oldClient)) {
            serves.remove(oldClient);
            oldClient.setBanker(null);
        }
    }
}
