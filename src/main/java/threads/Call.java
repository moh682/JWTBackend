package threads;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 *
 * @author RasmusFriis
 */
public class Call implements Callable<String>
{

    String url;
    Gson gson;

    /**
     *
     * @param url
     */
    public Call(String url)
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
        this.url = url;
    }

    @Override
    public String call() throws Exception
    {
        FetchError error = new FetchError("Not fetched", url);
        String jsonError = gson.toJson(error);
        try
        {

            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL
                    .openConnection();
            connection.setConnectTimeout(1000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json;charset=UTF-8");
            connection.setRequestProperty("User-Agent", "server");

            Scanner scan = new Scanner(connection.getInputStream());
            String jsonStr = null;
            if (scan.hasNext())
            {
                jsonStr = scan.nextLine();
            }
            scan.close();
            return jsonStr;
//            return Thread.currentThread().getName();

        } catch (Exception e)
        {
            return jsonError;
        }

    }
}