package com.vincle.ahl.demo.simulator;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Random;

public class ItemSimulator {

    private static final String[] TYPES = {"bebida", "comida", "salsas", "especies"};
    private static final int[] CAPACITIES = {100, 1000};
    private static final String[] CONTAINERS = {"botella", "caja"};
    private static final String[] STATUSES = {"WAITING", "CREATED", "DELETED"};

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            createRandomItem();
        }
    }

    private static void createRandomItem() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            URI uri = new URI("http://localhost:8080/items");

            String jsonInputString = String.format(
                "{\"name\":\"Item %d\",\"type\":\"%s\",\"requiresRefrigeration\":%b,\"capacity\":%d,\"container\":\"%s\",\"clientName\":\"Client %d\",\"timestamp\":\"%s\",\"status\":\"%s\"}",
                new Random().nextInt(1000),
                TYPES[new Random().nextInt(TYPES.length)],
                new Random().nextBoolean(),
                CAPACITIES[new Random().nextInt(CAPACITIES.length)],
                CONTAINERS[new Random().nextInt(CONTAINERS.length)],
                new Random().nextInt(100),
                LocalDateTime.now().toString(),
                STATUSES[new Random().nextInt(STATUSES.length)]
            );

            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("POST Response Code :: " + response.statusCode());
            System.out.println("Response Body :: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
