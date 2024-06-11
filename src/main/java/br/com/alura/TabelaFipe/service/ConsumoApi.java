package br.com.alura.TabelaFipe.service; // Declara o pacote onde esta classe está localizada

import java.io.IOException; // Importa a classe IOException que pode ser lançada ao lidar com operações de E/S
import java.net.URI; // Importa a classe URI que representa um identificador de recurso uniforme
import java.net.http.HttpClient; // Importa a classe HttpClient para enviar solicitações HTTP
import java.net.http.HttpRequest; // Importa a classe HttpRequest para construir solicitações HTTP
import java.net.http.HttpResponse; // Importa a classe HttpResponse para representar respostas HTTP

public class ConsumoApi { // Define a classe pública ConsumoApi
    public String obterDados(String endereco) { // Declara um método público que retorna uma string e aceita um endereço como parâmetro
        HttpClient client = HttpClient.newHttpClient(); // Cria uma nova instância de HttpClient, usada para enviar solicitações HTTP
        HttpRequest request = HttpRequest.newBuilder() // Cria um novo construtor de solicitações HTTP
                .uri(URI.create(endereco)) // Define o URI da solicitação a partir da string de endereço fornecida
                .build(); // Constrói a solicitação HTTP

        HttpResponse<String> response = null; // Declara uma variável para armazenar a resposta HTTP

        try {
            response = client // Usa o cliente HTTP para enviar a solicitação
                    .send(request, HttpResponse.BodyHandlers.ofString()); // Envia a solicitação e manipula o corpo da resposta como uma string
        } catch (IOException e) { // Captura exceções de entrada/saída que podem ser lançadas ao enviar a solicitação
            throw new RuntimeException(e); // Lança uma RuntimeException encapsulando a exceção original
        } catch (InterruptedException e) { // Captura exceções de interrupção que podem ser lançadas ao enviar a solicitação
            throw new RuntimeException(e); // Lança uma RuntimeException encapsulando a exceção original
        }

        String json = response.body(); // Obtém o corpo da resposta HTTP como uma string JSON
        return json; // Retorna a string JSON
    }
}
