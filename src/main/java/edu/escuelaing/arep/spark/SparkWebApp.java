/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.spark;

import com.google.gson.Gson;
import edu.escuelaing.arep.calculator.LinkedList;
import static spark.Spark.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SparkWebApp {

    private static LinkedList linkedList;
    private static ObjectMapper om = new ObjectMapper();

    public static void main(String[] args) {
        port(getPort());

        get("/hello", (request, response) -> "Hello Mundo", new JsonTransformer());

        get("/hello/:name", (request, response) -> {
            return "Hello, " + request.params(":name");
        });

        post("/numbers", (request, response) -> {
            // Se cargan los parámetros de la query (URL)
            String datos = request.queryParams("dato");
            String body = request.body();
            
            // Convertimos de JSON a objeto de la clase User
            Double dato = new Gson().fromJson(body, Double.class);
            if (dato != null) {
                System.out.println("---- Numero cargado correctamente.");
            }
            linkedList.addNode(dato);
            response.status(201);
            return dato;
        }, new JsonTransformer());

        /*
        post("/numbers", (request, response) -> {
            response.type("application/json");
            double date;
            date = new Gson().fromJson(request.body(), Double.class);
            linkedList.addNode(date);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });*/
 /*
        post("/numbers", (request, response) -> {
            response.type("application/json");
            double date;
            date = new Gson().fromJson(request.body(), Double.class);
            linkedList.addNode(date);

            return new Gson().toJson("{"success""}");
        });*/
 /*
        post("/number", (request, response) -> {
            response.type("application/json");
            double date = Double.parseDouble(request.queryParams("date"));
            linkedList.addNode(date);
            response.status(201);
            return om.writeValueAsString(date);
        });*/
 /*
        post("/number/:value", (request,response)->{
            double date = Double.parseDouble(request.params(":value"));
            linkedList.addNode(date);
            response.status(201);
            return "se guardo";
        });*/
        after((request, response) -> {
            response.type("application/json");
        });

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));

        }
        linkedList = new LinkedList();
        return 4567; //returns default port if heroku-port isn't set (i.e.on localhost)
    }
}
