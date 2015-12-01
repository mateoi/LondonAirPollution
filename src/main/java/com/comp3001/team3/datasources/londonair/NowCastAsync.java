package com.comp3001.team3.datasources.londonair;

import com.sun.deploy.net.HttpResponse;
import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.*;
//import org.apache.commons.httpclient.methods.GetMethod;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by mlloyd on 01/12/15.
 */
public class NowCastAsync {

    private String[] urls;
    public static final String BASE_URL = "http://api.erg.kcl.ac.uk/AirQuality/Data/Nowcast/Json/route=";

    public NowCastAsync(String[] urls){

        this.urls = urls;
    }

    // Creating MultiThreadedHttpConnectionManager


    public void query() throws IOException {
        //MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

        // Passing it to the HttpClient.
        //HttpClient httpClient = new HttpClient(connectionManager);

        // URIs that needs to be GET by threads.


        // create a thread for each URI
        int i = 0;
        for(String url : this.urls){
           // GetMethod get = new GetMethod(url);
            new CreateThread(url).start();
            i++;
        }
    }


    static class CreateThread extends Thread {
       /* private final HttpClient httpClient;
        private final GetMethod method;*/
        private String address;

        public CreateThread(/*HttpClient httpClient, GetMethod method, */String url) {
            this.address = address;
            /*super(name);
            this.httpClient = httpClient;
            this.method = method;*/
        }

        @Override
        public void run() {
            try {
                /*System.out.println(Thread.currentThread().getName() + " - about to get something from "
                        + method.getURI());
*/
                // Execute the method.
                String results = "";
                URL url = new URL(address);
                URLConnection connect = url.openConnection();
                connect.setRequestProperty("Host", "www.someserver.com");
                connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko/20100101 Firefox/11.0");
                connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                connect.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
                connect.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                connect.setRequestProperty("Keep-Alive", "115");
                connect.setRequestProperty("Connection", "keep-alive");
                BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));
                String inputLine;
                while ((inputLine = in.readLine()) != null){
                    results += inputLine;
                }


                System.out.println(results);







             /*

                int statusCode = httpClient.executeMethod(method);

                if (statusCode != HttpStatus.SC_OK) {
                    System.err.println("Method failed: " + method.getStatusLine());
                }

                // Reading the status body.
                StatusLine statusLine = method.getStatusLine();
                System.out.println(Thread.currentThread().getName() + " " + statusLine);*/
            } catch (HttpException e) {
                System.err.println("Fatal protocol violation: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Fatal transport error: " + e.getMessage());
            } finally {
                // Release the connection.
                //method.releaseConnection();
            }
        }

    }

}
