package Bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Banker {
    private int id;
    private String name;
    private String surname;
    private ArrayList<Client> serves;

    public Banker(int id, String surname, String name) {
        setId(id);
        setSurname(surname);
        setName(name);
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

    // Association
    public List<Client> getClients(){
        return Collections.unmodifiableList(serves);
    }

    public void addClient(Client newClient){
        if (!serves.contains(newClient)){
            serves.add(newClient);
            newClient.setBanker(this);
        }
    }

    public void removeClient(Client oldClient) {
        if (serves.contains(oldClient)) {
            serves.remove(oldClient);
        } else {
            throw new IllegalArgumentException("Banker does not serve the client " + oldClient.getName());
        }
    }
}
