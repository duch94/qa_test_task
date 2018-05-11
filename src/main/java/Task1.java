import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Task1 {
    private final String rootUrl = "https://swapi.co/api/";
    private final String format = "?format=json";
    private final String format2nd = "&format=json";

    private final String YELLOW = "\033[0;33m";
    private final String CYAN = "\033[0;36m";
    private final String BLUE = "\u001B[34m";
    private final String GREEN = "\u001B[32m";
    private final String RED = "\u001B[31m";
    private final String RESET = "\033[0m";

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

    private boolean starshipIsFromThirdEpisode(JSONObject starship) {
        JSONArray films = starship.getJSONArray("films");
        for (int i = 0; i < films.length(); i++) {
            JSONObject film = new JSONObject(getRequestSender(films.getString(i) + format));
            if (film.getInt("episode_id") == 3) {
                return true;
            }
        }
        return false;
    }

    public String obiWanStarShipFromThirdEpisode() {
        String obiWanSearchMethod = "people/?search=Obi-Wan%20Kenobi";
        String resp = getRequestSender(rootUrl + obiWanSearchMethod + format2nd);
        String result = "";

        JSONObject obiWan = new JSONObject(resp).getJSONArray("results").getJSONObject(0);
        JSONArray obiWanStarships = obiWan.getJSONArray("starships");

        System.out.println(CYAN + "Obiwan's starships characteristics: " + RESET);
        // пройтись по всем кораблям и найти те которые были в 3 эпизоде и взять их названия
        String obiwanStarshipsFromThirdEpisode = "";
        JSONObject currentStarship = null;
        for (int i = 0; i < obiWanStarships.length(); i++) {
            String currentStarshipString = getRequestSender(obiWanStarships.getString(i) + format);
            currentStarship = new JSONObject(currentStarshipString);
            if (starshipIsFromThirdEpisode(currentStarship)) {
                obiwanStarshipsFromThirdEpisode += currentStarship.getString("name") + ", ";
                System.out.println(CYAN + currentStarshipString + RESET);
            }
        }

        obiwanStarshipsFromThirdEpisode =
                obiwanStarshipsFromThirdEpisode.substring(0, obiwanStarshipsFromThirdEpisode.length() - 2);
        return obiwanStarshipsFromThirdEpisode;
    }

    public boolean does100CharacterExists() {
        String charactersListMethod = "people/";
        JSONObject charactersCurrentPage = new JSONObject(getRequestSender(rootUrl + charactersListMethod + format));
        final int maxCharacters = 100;
        int charactersCurrentQuantity = charactersCurrentPage.getInt("count");
        if (charactersCurrentQuantity >= maxCharacters) {
            return true;
        }
        return false;
    }

    public void assertion(String funcName, Object received, Object actual) {
        System.out.print(BLUE + funcName + " must be " + YELLOW + actual + BLUE + ": ");
        if (received.equals(actual)) {
            System.out.println(GREEN + "OK!" + RESET);
        } else {
            System.out.println(RED + "Assertion error, must be " + actual + RESET);
        }
    }

    public static void main(String[] args) {
        Task1 self = new Task1();

        self.assertion("howMuchStarWarsFilms", self.howMuchStarWarsFilms(), 7);
        self.assertion("whenForthEpisodeCreated", self.whenForthEpisodeCreated(), "1977-05-25");
        self.assertion("whoWasDirectorOfForthEpisode", self.whoWasDirectorOfForthEpisode(), "George Lucas");
        self.assertion("titleOfForthEpisode", self.titleOfForthEpisode(), "A New Hope");
        self.assertion("planetsFromSecondEpisode", self.planetsFromSecondEpisode(), "Naboo, Coruscant, Kamino, Geonosis, Tatooine");
        self.assertion("lukeMotherlandPlanet", self.lukeMotherlandPlanet(), "Tatooine");
        self.assertion("obiWanStarShipFromThirdEpisode", self.obiWanStarShipFromThirdEpisode(), "Jedi starfighter, Trade Federation cruiser, Naboo star skiff, Jedi Interceptor, Belbullab-22 starfighter");
        self.assertion("does100CharacterExists", self.does100CharacterExists(), false);
    }
}
