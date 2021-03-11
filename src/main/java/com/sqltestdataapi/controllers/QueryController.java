package com.sqltestdataapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v0/api/")
@RestController("queryController")
public class QueryController {

    private static final Logger log = LoggerFactory.getLogger(QueryController.class);

    @RequestMapping(value = "/request", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRequest(@RequestParam(required = false) String query){
        this.log.info("Query " + query + " asked !");
        String fixture = "SELECT * FROM GuitarHero;";

        return new ResponseEntity<>(fixture, HttpStatus.OK);
    }
}