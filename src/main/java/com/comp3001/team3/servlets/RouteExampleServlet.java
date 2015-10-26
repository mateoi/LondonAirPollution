package com.comp3001.team3.servlets;


import com.comp3001.team3.datasources.google.Gkey;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by mlloyd on 26/10/15.
 */
@WebServlet(name = "RouteExampleServlet", urlPatterns = {"/route"})
public class RouteExampleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            String start    = request.getParameter("start");
            String dest     = request.getParameter("destination");


            DirectionsApiRequest apiRequest = DirectionsApi.newRequest(new GeoApiContext()
                    .setQueryRateLimit(3)
                    .setConnectTimeout(1, TimeUnit.SECONDS)
                    .setReadTimeout(1, TimeUnit.SECONDS)
                    .setWriteTimeout(1, TimeUnit.SECONDS)
                    .setApiKey(Gkey.API_KEY));

            apiRequest.mode(TravelMode.WALKING);
            apiRequest.origin(start);
            apiRequest.destination(dest);

            //add waypoints if needed: apiRequest.waypoints()

            DirectionsRoute[] routes = apiRequest.await();
            List<LatLng> points = routes[0].overviewPolyline.decodePath(); //loads of points add up the pollution on


            String polyline = routes[0].overviewPolyline.getEncodedPath();

            PrintWriter out = response.getWriter();
            out.write("<p>"+ polyline +"</p>" );
            out.write("<p> put string above in ENCODED POLYLINE here: "
                    + " https://developers.google.com/maps/documentation/utilities/polylineutility?hl=en ");





        }catch (Exception e){
            System.out.print(e.getMessage());
        }


    }
}
