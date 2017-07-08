package com.samsung.demo.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ji.zhang on 7/8/17.
 */
@RestController
public class HelloController {

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String hello() {
        return "Hello Tom!";
    }
}
