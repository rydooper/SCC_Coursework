package com.example.scccoursework;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Random;

@Path("/userIDResource")
public class userIDResource {
    @GET
    @Produces("text/plain")
    /*public String hello() {

        return "Hello, World!";
    }*/
    public int userID() {
        Random rand = new Random();
        int userID = rand.nextInt(1000);
        return userID;
    }
}