package com.comp3001.team3.controllers;

/**
 * Created by mlloyd on 19/11/15.
 */


import com.comp3001.team3.calculations.RouteGenerator;
import org.springframework.web.bind.annotation.*;


import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin
@RequestMapping(value= {"/api/v1"})

public class MasterController {


    @RequestMapping( value = {"/route"}, method = RequestMethod.GET)

    public RouteGenerator route(@RequestParam(value = "start", defaultValue = "malet place")
                                    String start,
                                @RequestParam(value = "destination" , defaultValue = "white hart lane")
                                    String destination) {
        return new RouteGenerator(start, destination);
    }

}
