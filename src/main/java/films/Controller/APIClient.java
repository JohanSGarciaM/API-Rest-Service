package films.Controller;

import films.Service.APIService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class which request to API
 * @author Johan Garcia
 * @version 24/01/2024/1
 */

public class APIClient {

    // Class Variables
    private static APIService as;
    private static APIClient instance = null;
    private static final String apikey = "fc03d85a";
    private static final String omdbapiurl = "http://www.omdbapi.com/?apikey="+apikey;

    /**
     * APIClient Constructor
     * @param as APIService instance
     */
    public APIClient(APIService as){
        APIClient.as = as;
    }

    /**
     * Singleton Patron
     * APIClient
     * @return Class's Instance
     */

    public static APIClient getInstance(){
        if (instance == null){
            instance = new APIClient(APIService.getInstance());
        }
        return instance;
    }

    /**
     * Brings the movie's informations if it is in the caché
     * @return Movie's caché information
     */
    public static String getMovie(String movie) throws IOException {
        if (as.storedInCache(movie)){
            return as.getMovie(movie);
        }
        URL urlmovie = new URL(omdbapiurl+"&t="+movie);
        String info = requestGetMovie(urlmovie);
        as.addMovie(movie, info);
        return info;
    }

    /**
     * Request the movie's data from the API if it is not in caché
     * @param url API's Url
     * @return Movie's information given by the API
     * @throws IOException In/Out Exception
     */

    private static String requestGetMovie(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode();
        System.out.println("codigo: "+responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                System.out.println("Respuesta:" + inputLine);
            }
            in.close();
            return response.toString();
        } else{
            return "GET did not work";
        }
    }
}