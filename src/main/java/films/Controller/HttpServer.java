package films.Controller;

import java.net.*;
import java.io.*;
import org.json.*;
/**
 * HTTP Server Class
 * @author Johan Garcia
 * @version 24/01/2024/1
 */

public class HttpServer {

    /**
     * Class Main Method
     * @param args Method's Array
     * @throws IOException In/Out Exception
     */

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean firstLine = true;
            String path = null;

            String name = "";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    firstLine = false;
                    path = inputLine.split(" ")[1];
                }
                if (inputLine.contains("GET") && inputLine.contains("=")){
                    name = inputLine.split("=")[1].split(" ")[0];
                }
                if (!in.ready()) {
                    break;
                }
            }
            outputLine = "HTTP/1.1 200 OK \r\n";

            if (path.startsWith("/movie")){
                outputLine += getMovie(path);
            } else {
                outputLine += getResponse();
            }


            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    } // Method's finish

    /**
     * Method which send the movie's information
     * @param path Directory
     * @return Movie's Info
     */

    public static String getMovie(String path) throws IOException {
        APIClient client = APIClient.getInstance();
        String info = client.getMovie(path.split("=")[1]);
        JSONObject resp = new JSONObject(info);
        String response = "Content-Type: text/json \r\n"
                + "\r\n"+
                "<div>"+
                "<h1>" + resp.get("Title") + "</h1>"+
                "<h2>" + resp.get("Year")+ "</h2>"+
                "<img src=\"" + resp.get("Poster") +"\">"+
                "<p>" + resp.get("Director") + "</p>"+
                "<p>" + resp.get("Rated") + "</p>"+
                "<p>" + resp.get("Genre") + "</p>"+
                "<p>" + resp.get("Plot") + "</p>"+
                "</div>\n";

        return response;
    } // Method's finish

    /**
     * This Method shows the page's format 
     * @return Get/Post Answer
     */

    public static String getResponse(){
        String response = "Content-Type: text/html \r\n"
                + "\r\n <!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>APIRest</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>APIRest</h1>\n" +
                "        <h2>Best source of movie information</h2>\n" +
                "        <h3> Please introduce the Title of the movie you want to search for</h3>" +
                "        <form action=\"/movie\">\n" +
                "            <label for=\"name\">Name:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n" +
                "            <input type=\"button\" value=\"Search\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/movie?name=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "    </body>\n" +
                "</html>";
        return response;
    }
}