import java.util.*;
 import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        /*fazer uma conexão HTTP com e buscar os top250 filmes */
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        //extrair só os dados que interessam (title, poster, classification)

        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

         /* 
        System.out.println(movieList.size());
        System.out.println(movieList.get(0)); //representação java de mapping 
        */
                
        // show and manipulate data

        //cabeçalho

        System.out.println(("\u001B[1;40;34m!---------------------------------------------------!\u001B[0m"));
        System.out.println(("\u001B[1;40;34m!------------------ \u001b[1;33mTOP 10 MOVIES\u001B[0m\u001B[1;40;34m ------------------!\u001B[0m"));
        System.out.println(("\u001B[1;40;34m!---------------------------------------------------!\u001B[0m\n"));

        for (Map<String,String> movie : movieList) {


            System.out.println("\u001B[1;4;34;43mTitle:\u001B[0m " + movie.get("fullTitle"));

            System.out.println("\u001B[1;4;33;44mIMAGE:\u001B[0m " + movie.get("image"));

            System.out.println("\u001B[1;4;33;45mImBDRating:\u001B[0m " + movie.get("imDbRating") + "\n");

            double classification = Double.parseDouble(movie.get("imDbRating"));
            int starNumber = (int) classification;

            for (int x = 1; x <= starNumber; x++) {
                if (starNumber > 8) {
                    System.out.print("⭐"); //print sem o ln //meu java não conseguiu resolver o emoji de estrela
                }else 
                {
                    System.out.print("❌");
                }

            }            

            System.out.println("\n");


        }





    }
}
