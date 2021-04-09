package com.sqltestdataapi.controllers;

import com.sqltestdataapi.services.TestDataGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("v0/api/")
@RestController("queryController")
public class QueryController {

    private static final Logger log = LoggerFactory.getLogger(QueryController.class);

    @Autowired
    private TestDataGeneratorService service;

    @RequestMapping(value = "/request", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRequest(@RequestParam(required = false) String query) {
        log.info("Query " + query + " asked !");
        // String fixture = "SELECT * FROM GuitarHero;";
        String[] formattedQuery = query.split("%20");
        String test = Stream.of(formattedQuery)
                .map(s -> s.split("%20"))
                .flatMap(Stream::of)
                .collect(Collectors.joining(" "));
        System.out.println("test = " + test);
        String fixture = this.service.generateTestQuery(test);
//        URI uri = URI.create(query);
//        String fixture = uri.getRawQuery();
        System.out.println("fixture = " + fixture);

        // voir si un simple retour String marche ?
        return new ResponseEntity<>(fixture, HttpStatus.OK);
    }
}