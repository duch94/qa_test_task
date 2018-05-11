import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Task2 {
    private String rootUrl = "https://swapi.co/api/";
    private String format = "?format=json";
    private String format2nd = "&format=json";

    public String getRequestSender(String urlString) {
        String responseLine = "";
        StringBuffer bufferedResponse = new StringBuffer();
        BufferedReader in = null;
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "curl");

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            responseLine = in.readLine();
            while (responseLine != null) {
                bufferedResponse.append(responseLine);
                responseLine = in.readLine();
            }
            in.close();
        } catch (java.net.MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
        return bufferedResponse.toString();
    }

    public int howMuchStarWarsFilms() {
        String method = "films/";
        String resp = getRequestSender(rootUrl + method + format);

        JSONObject filmsJson = new JSONObject(resp);
        int result = filmsJson.getInt("count");

        return result;
    }

    public String whenForthEpisodeCreated() {
        String method = "films/";
        String resp = getRequestSender(rootUrl + method + format);

        JSONObject filmsJson = new JSONObject(resp);
        String result = filmsJson.getJSONArray("results").getJSONObject(0).getString("release_date");

        return result;
    }

    public String whoWasDirectorOfForthEpisode() {
        String method = "films/";
        String resp = getRequestSender(rootUrl + method + format);

        JSONObject filmsJson = new JSONObject(resp);
        String result = filmsJson.getJSONArray("results").getJSONObject(0).getString("director");

        return result;
    }

    public String titleOfForthEpisode() {
        String method = "films/";
        String resp = getRequestSender(rootUrl + method + format);

        JSONObject filmsJson = new JSONObject(resp);
        String result = filmsJson.getJSONArray("results").getJSONObject(0).getString("title");

        return result;
    }

    public String planetsFromSecondEpisode() {
        String method = "films/";
        String resp = getRequestSender(rootUrl + method + format);
        String result = "";

        JSONObject filmsJson = new JSONObject(resp);
        JSONArray films = filmsJson.getJSONArray("results");
        for (int i = 0; i < films.length(); i++) {
            JSONObject film = films.getJSONObject(i);
            if (film.getInt("episode_id") == 2) {
                JSONArray planets = film.getJSONArray("planets");
                for (int j = 0; j < planets.length(); j++) {
                    JSONObject planet = new JSONObject(getRequestSender(planets.getString(j) + format));
                    result = result + planet.getString("name") + ", ";
                }
            }
        }

        result = result.substring(0, result.length() - 2);
        return result;
    }

    public String lukeMotherlandPlanet() {
        String method = "people/?search=Luke%20Skywalker";
        String resp = getRequestSender(rootUrl + method + format2nd);
        String result = "";

        JSONObject luke = new JSONObject(resp);
        JSONArray results = luke.getJSONArray("results");
        JSONObject lukePerson = results.getJSONObject(0);
        String motherlandPlanetString = lukePerson.getString("homeworld");
        JSONObject motherlandPlanet = new JSONObject(getRequestSender(motherlandPlanetString + format));
        result = motherlandPlanet.getString("name");

        return result;
    }

    public void assertion(String funcName, Object received, Object actual) {
        String BLUE = "\u001B[34m";
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";
        String RESET = "\033[0m";

        System.out.print(BLUE + funcName + " must be " + actual + ": ");
        if (received.equals(actual)) {
            System.out.println(GREEN + "OK!" + RESET);
        } else {
            System.out.println(RED + "Assertion error" + RESET);
        }
    }

    public static void main(String[] args) {
        Task2 self = new Task2();

        self.assertion("howMuchStarWarsFilms", self.howMuchStarWarsFilms(), 7);
        self.assertion("whenForthEpisodeCreated", self.whenForthEpisodeCreated(), "1977-05-25");
        self.assertion("whoWasDirectorOfForthEpisode", self.whoWasDirectorOfForthEpisode(), "George Lucas");
        self.assertion("titleOfForthEpisode", self.titleOfForthEpisode(), "A New Hope");
        self.assertion("planetsFromSecondEpisode", self.planetsFromSecondEpisode(), "Naboo, Coruscant, Kamino, Geonosis, Tatooine");
        self.assertion("lukeMotherlandPlanet", self.lukeMotherlandPlanet(), "Tatooine");
    }
}
