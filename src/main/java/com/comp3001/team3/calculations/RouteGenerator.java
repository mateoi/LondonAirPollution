package com.comp3001.team3.calculations;

import com.comp3001.team3.datasources.google.Gkey;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RouteGenerator {

    String start;
    String destination;
    String polyline;

    public RouteGenerator(String start, String destination){
        this.start          = start;
        this.destination    = destination;

        try{

            DirectionsApiRequest apiRequest = DirectionsApi.newRequest(new GeoApiContext()
                    .setQueryRateLimit(3)
                    .setConnectTimeout(1, TimeUnit.SECONDS)
                    .setReadTimeout(1, TimeUnit.SECONDS)
                    .setWriteTimeout(1, TimeUnit.SECONDS)
                    .setApiKey(Gkey.API_KEY));


            apiRequest.mode(TravelMode.WALKING);
            apiRequest.origin(start);
            apiRequest.destination(destination);

            //add waypoints if needed: apiRequest.waypoints()

            DirectionsRoute[] routes = apiRequest.await();
            List<LatLng> points = routes[0].overviewPolyline.decodePath(); //loads of points add up the pollution on


            this.polyline = routes[0].overviewPolyline.getEncodedPath();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }



}
