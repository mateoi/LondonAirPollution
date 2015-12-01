package com.comp3001.team3.calculations;

import com.comp3001.team3.datasources.google.Gkey;
import com.comp3001.team3.pojo.Polyline;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RouteGenerator {

    String start;

    /*public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }*/

    String destination;
    ArrayList<String> routes;
    //String polyline;

    @JsonCreator
    public RouteGenerator(String start, String destination){
        this.start          = start;
        this.destination    = destination;
        routes = new ArrayList<String>();



        try{

            DirectionsApiRequest apiRequest = DirectionsApi.newRequest(new GeoApiContext()
                    .setQueryRateLimit(3)
                    .setConnectTimeout(1, TimeUnit.SECONDS)
                    .setReadTimeout(1, TimeUnit.SECONDS)
                    .setWriteTimeout(1, TimeUnit.SECONDS)
                    .setApiKey(Gkey.API_KEY));



            apiRequest.mode(TravelMode.WALKING);
            apiRequest.origin(start);
            apiRequest.alternatives(true);

            apiRequest.destination(destination);

            //add waypoints if needed: apiRequest.waypoints()

            DirectionsRoute[] googleRoutes = apiRequest.await();
            //List<LatLng> points = routes[0].overviewPolyline.decodePath(); //loads of points add up the pollution on


            for (DirectionsRoute route : googleRoutes){
                routes.add(route.overviewPolyline.getEncodedPath());
                System.out.print("adding polyline");
            }
            System.out.println("polylines size " + routes.size() );
            //this.polyline = routes[0].overviewPolyline.getEncodedPath();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   /* public String getJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        return jsonInString;
    }*/

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public ArrayList<String> getRoutes() {
        return routes;
    }

    /*public void setPolyline(List<Polyline> polylines) {
        this.polylines = polylines;
    }*/

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }



}
