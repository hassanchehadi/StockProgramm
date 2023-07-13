import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ConnectorToAPI {
    private static final String API_KEY = "BBYI426JH9BO1FNN";

    public String getStockData(String symbol) throws Exception {
            String url = "http://api.marketstack.com/v1/intraday?access_key=ad54bb846dd7973235f0afdfe226f81a&symbols=AAPL";

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);

            return responseString;

    }
}
