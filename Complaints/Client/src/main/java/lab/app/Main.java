package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.persistence.sessions.serializers.JSONSerializer;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String BASE_URL = "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menu:");
            System.out.println("a. Pobierz i wyświetl na konsoli wszystkie skargi");
            System.out.println("b. Pobierz i wyświetl na konsoli jedną z otwartych skarg (przesyłając jej identyfikator do usługi)");
            System.out.println("c. Zmodyfikuj skargę pobraną w poprzednim punkcie zmieniając jej status na zamknięty (poprzez podmianę całego zasobu)");
            System.out.println("d. Pobierz i wyświetl na konsoli wszystkie otwarte skargi");
            System.out.println("e. Exit");
            System.out.print("Select an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "a":
                    getAllComplaints(client);
                    break;
                case "b":
                    System.out.print("Enter complaint ID: ");
                    String id = scanner.nextLine();
                    getComplaintById(client, id);
                    break;
                case "c":
                    System.out.print("Enter complaint ID to modify: ");
                    String complaintId = scanner.nextLine();
                    modifyComplaintStatus(client, complaintId);
                    break;
                case "d":
                    getAllOpenComplaints(client);
                    break;
                case "e":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        client.close();
        scanner.close();
    }

    private static String formatJson(String json) {
        return json.replace("{", "\n")
                .replace("}", "\n====\n")
                .replace("\"", "")
                .replace(",", "\n");
    }

    private static void getAllComplaints(Client client) {
        String response = client
                .target(BASE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println(formatJson(response));
    }

    private static void getAllOpenComplaints(Client client) {
        String response = client
                .target(BASE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println(formatJson(response));
    }

    private static void getComplaintById(Client client, String id) {
        String response = client
                .target(BASE_URL + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        System.out.println(formatJson(response));
    }

    private static void modifyComplaintStatus(Client client, String id) {
        try {
            String response = client
                    .target(BASE_URL + "/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);

            client.target(BASE_URL + "/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(response.replace(
                            "\"status\":\"open\"",
                            "\"status\":\"closed\"")
                    ));

            System.out.println("Complaint status updated to closed.");
        } catch (Exception e) {
            System.out.println("Error updating complaint: " + e.getMessage());
        }
    }
}
