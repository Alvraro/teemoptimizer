package es.raro.riot;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jfree.io.IOUtils;

import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;

public class RiotAPI {
	private static RiotAPI instance;
	private String key;
	private String region;
	private String globalEndpoint;
	private String regionalEndpoint;
	
	public static RiotAPI getInstance() throws FileNotFoundException, IOException{
		if(instance==null)
			instance = new RiotAPI();
		return instance; 
	}
	
	private RiotAPI() throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		prop.load(new FileReader("riot_api.properties"));
		key = prop.getProperty("key");
		region = prop.getProperty("region");
		globalEndpoint = prop.getProperty("global_endpoint");
		regionalEndpoint = prop.getProperty("regional_endpoint");
	}
		
	public String getChampionStats(String name) throws ClientProtocolException, IOException{
		String champions = makeRequest(globalEndpoint+"/api/lol/static-data/"+region+"/v1.2/champion");
		Gson gson = new Gson();
		
		
		return null;
	}
	
	private String makeRequest(String url) throws IllegalStateException, IOException{
		CloseableHttpClient client = null;
		InputStream in = null;
		InputStreamReader reader = null;
		try{
			client = HttpClientBuilder.create().build();
			HttpUriRequest request = new HttpGet(url);
			CloseableHttpResponse response = client.execute(request);
			in = response.getEntity().getContent();
			reader = new InputStreamReader(in);
			return CharStreams.toString(reader);
		}
		finally{
			if(reader!=null)
				reader.close();
			if(in!=null)
				in.close();
			if(client!=null)
				client.close();
		}
	}
		
}
