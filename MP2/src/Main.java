import bank.Banker;
import bank.Client;

public class Main {
    public static void main(String[] args) {
        Banker banker = new Banker(1, "Kowalski", "Jan");
        Client client = new Client("00000", "Dawid", "Kucharski", banker);


    }
}
