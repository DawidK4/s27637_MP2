package bank;

import utils.ObjectPlus;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Bank extends ObjectPlus implements Serializable {
    private String swiftNumber;
    private String name;
    private Map<String, Client> dealsWith = new TreeMap<>();

    public Bank(String swiftNumber, String name) {
        super();
        setSwiftNumber(swiftNumber);
        setName(name);
    }

    public void addClient(Client newClient) {
        if (newClient == null) throw new IllegalArgumentException("Client cannot be null!");

        if (!dealsWith.containsKey(newClient.getClientNumber())) {
            dealsWith.put(newClient.getClientNumber(), newClient);

            newClient.setBank(this);
        }
    }

    public Client findClient(String clientNumber) throws Exception{
        if (!dealsWith.containsKey(clientNumber)) {
            throw new Exception("Unable to find a client with number: " + clientNumber);
        }

        return dealsWith.get(clientNumber);
    }

    public Map<String, Client> getClients() {
        return Collections.unmodifiableMap(this.dealsWith);
    }

    public void removeClient(String clientNumber) {
        Client oldClient = this.dealsWith.get(clientNumber);
        oldClient.removeBank();
        this.dealsWith.remove(clientNumber);
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

    public String getSwiftNumber() {
        return swiftNumber;
    }

    public void setSwiftNumber(String swiftNumber) {
        if (swiftNumber.isEmpty() || swiftNumber == null) {
            throw new IllegalArgumentException("Swift number must not be null or empty!");
        }

        if (swiftNumber.trim().length() <= 3 ){
            throw new IllegalArgumentException("Swift number has to contain at least 4 digits!");
        }

        this.swiftNumber = swiftNumber;
    }
}
