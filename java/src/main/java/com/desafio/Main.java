package com.desafio;

import com.desafio.model.PaidDebt;
import com.desafio.model.Partner;
import com.desafio.model.PriceRange;
import com.desafio.model.PriceTable;
import com.desafio.service.CommissionResult;
import com.desafio.service.CommissionService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        CommissionService service = new CommissionService();

        String partnerId = "1";
        CommissionResult result = service.calculate(partnerId, getPaidDebts(), getPriceTables());
        System.out.println(result);

        // Bônus: endpoint HTTP simples.
        // Experimente: mvn compile exec:java -Dexec.mainClass="com.desafio.Main"
        // e depois acesse http://localhost:8080/comissao?partnerId=1
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/comissao", exchange -> {
            Map<String, String> params = parseQuery(exchange.getRequestURI().getQuery());
            String id = params.get("partnerId");
            if (id == null) {
                sendResponse(exchange, 400, "informe o parâmetro partnerId");
                return;
            }
            try {
                CommissionResult r = service.calculate(id, getPaidDebts(), getPriceTables());
                String json = String.format("{\"partnerId\":\"%s\",\"totalDebts\":%.2f,\"totalCommission\":%.2f}",
                        r.getPartnerId(), r.getTotalDebts(), r.getTotalCommission());
                sendResponse(exchange, 200, json);
            } catch (RuntimeException e) {
                sendResponse(exchange, 404, e.getMessage());
            }
        });
        server.start();
        System.out.println("servidor rodando em http://localhost:8080");
    }

    private static Map<String, String> parseQuery(String query) {
        if (query == null || query.isBlank()) {
            return Map.of();
        }
        return java.util.Arrays.stream(query.split("&"))
                .map(p -> p.split("=", 2))
                .filter(p -> p.length == 2)
                .collect(java.util.stream.Collectors.toMap(p -> p[0], p -> p[1]));
    }

    private static void sendResponse(com.sun.net.httpserver.HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static List<Partner> getPartners() {
        return List.of(
                new Partner("1", "Parceiro 1"),
                new Partner("2", "Parceiro 2")
        );
    }

    private static List<PriceTable> getPriceTables() {
        return List.of(
                new PriceTable("1", List.of(
                        new PriceRange(0, 30, 5),
                        new PriceRange(31, 9999, 10)
                )),
                new PriceTable("2", List.of(
                        new PriceRange(0, 30, 3),
                        new PriceRange(31, 9999, 7)
                ))
        );
    }

    private static List<PaidDebt> getPaidDebts() {
        return List.of(
                new PaidDebt(100, LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 10), "1"),
                new PaidDebt(100, LocalDate.of(2025, 2, 1), LocalDate.of(2025, 4, 2), "1"),
                new PaidDebt(100, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 4, 2), "2")
        );
    }
}
