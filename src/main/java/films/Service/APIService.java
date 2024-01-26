package films.Service;

import films.Persistence.APIPersistence;


/**
 * API Service Class
 * @author Johan Garcia
 * @version 24/01/2024/1
 */
public class APIService {

    // Class Variables
    private static APIPersistence ap;
    private static APIService instance = null;

    /**
     * APIService Class Constructor
     */
    public APIService(APIPersistence ap){
        APIService.ap = ap;
    }

    /**
     * Singleton Patron
     * @return Class instance
     */
    public static APIService getInstance(){
        if (instance == null){
            instance = new APIService(APIPersistence.getInstance());
        }
        return instance;
    }

    /**
     * Method which looks for the movie in caché
     * @param movie Movie's name
     * @return Movie's name and info into an array
     */
    public String getMovie(String movie){
        return ap.getMovie(movie);
    } 

    /**
     * Check if the movie is in caché
     * @param movie Movie's name
     * @return Boolean which shows if the movie is in caché
     */
    public boolean storedInCache(String movie){
        return ap.movieStoredCache(movie);
    }

    /**
     * Method which stores a movie in caché
     * @param movie Movie's name
     * @param info Movie's Information
     */
    public void addMovie(String movie, String info){
        ap.addMovie(movie, info);
    }
}