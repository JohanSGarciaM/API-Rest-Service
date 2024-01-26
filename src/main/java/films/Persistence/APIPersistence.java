package films.Persistence;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Aplications caché (Persistence)
 * @author Johan Garcia
 * @version 24/01/2024/1
 */

public class APIPersistence{

    // Class Variables
    private ConcurrentHashMap<String, String> moviesCache;
    private static APIPersistence instance = null;

    /**
     *  APIPersistence Class Constructor
     */
    public APIPersistence(){
        moviesCache = new ConcurrentHashMap<String, String>();
    }

    /**
     * Singleton Patron
     * @return Class Instance
     */
    public static APIPersistence getInstance(){
        if (instance == null){
            instance = new APIPersistence();
        }
        return instance;
    }
    /**
     * Check if the movie is in caché and return it's information
     * @param movie Movie's name
     * @return Boolean which shows if the movie is in caché
     */
    public boolean movieStoredCache(String movie){
        return moviesCache.contains(movie);
    }

    /**
     * Method go gives the Movie if is in caché
     * @param movie Movie's Name
     * @return Movie's Information
     */
    public String getMovie(String movie){
        return moviesCache.get(movie);
    } 

    /**
     * Method which inserts a new movie and its information
     * @param movie Movie's name
     * @param info Movie's information
     */

    public void addMovie(String movie, String info){
        moviesCache.put(movie, info);
    }
}