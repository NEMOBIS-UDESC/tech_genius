package repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import models.Question;

public class QuestionRepository {
    // mudar retorno para List<Question>
    public String getQuestions() throws Exception {
        try {
            // Cria a URL da requisição
            URL url = new URL("http://localhost:8080/question");

            // Abre a conexão
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Define o método da requisição
            connection.setRequestMethod("GET");

            // Obtém a resposta
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_BAD_REQUEST
                    || responseCode != HttpURLConnection.HTTP_INTERNAL_ERROR) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                connection.disconnect();
                // Processa a resposta
                return response.toString();
            } else {
                System.out.println("Erro na requisição. Código de resposta: " + responseCode);
                connection.disconnect();
                return "deu ruim";
            }

            // Fecha a conexão

        } catch (Exception e) {
            e.printStackTrace();
            return "deu ruim";
        }

    }
}
