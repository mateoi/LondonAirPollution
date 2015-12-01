package com.comp3001.team3.controllers;

/**
 * Created by mlloyd on 19/11/15.
 */


import com.comp3001.team3.calculations.RouteGenerator;
import com.comp3001.team3.datasources.londonair.NowCastAsync;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value= {"/api/v1"})
public class MasterController {




    @RequestMapping( value = {"/route"}, method = RequestMethod.GET)
    public RouteGenerator route(@RequestParam(value = "start", defaultValue = "malet place, WC1E 6BT")
                                    String start,
                                @RequestParam(value = "destination" , defaultValue = "holloway road n7")
                                    String destination) throws IOException {
        RouteGenerator r = new  RouteGenerator(start, destination);
        ObjectMapper mapper = new ObjectMapper();

        String[] urls = new String[r.getRoutes().size()];
        int i =0;
        for (String polyline : r.getRoutes()){
            polyline =  NowCastAsync.BASE_URL + polyline.replace("/", "0")
                    .replace("?", "%3F").replace("|", "%7C").replace("{", "%7B").replace("/", "0");

            urls[i] = polyline;

            System.out.println( "adding url " +  urls[i]);
            i++;
        }
        NowCastAsync nowCast = new NowCastAsync(urls);

        nowCast.query();

        try {
            String json = mapper.writeValueAsString(r);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return r;
    }

}
