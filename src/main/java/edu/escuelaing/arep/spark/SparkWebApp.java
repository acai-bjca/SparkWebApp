/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.spark;

import edu.escuelaing.arep.servicios.LinkedList;
import static spark.Spark.*;
import spark.Spark;
import edu.escuelaing.arep.servicios.Servicios;
import spark.Request;
import spark.Response;

public class SparkWebApp {

    private static LinkedList linkedList;
    private static Servicios servicios;

    public static void main(String[] args) {
        port(getPort());
        //Spark.staticFiles.location("/static");

        get("/hello", (request, response) -> "Hello Mundo", new JsonTransformer());

        get("/hello/:name", (request, response) -> {
            return "Hello, " + request.params(":name");
        });

        get("/home", (req, resp) -> iniciar(req, resp));
        get("/results", (req, resp) -> answer(req, resp));
        /*
        after((request, response) -> {
            response.type("application/json");
        });*/

    }

    public static String iniciar(Request request, Response response) {
        String htmlString = "<!doctype html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\"\n"
                + "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n"
                + "    <title>Document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <br/>\n"
                + "    <h2>Digite los numeros separados por comas</h2>\n"
                + "    <form action=\"/results\">\n"
                + "        <h3>Conjunto de datos numero 1</h3>\n"
                + "        <input type=\"text\" placeholder=\"Numeros espaciados con ';'\" name=\"DataOne\">\n"
                + "        <br><br><br>\n"
                + "        <input type=\"submit\" value=\"Calcular\">\n"
                + "\n"
                + "    </form>\n"
                + "\n"
                + "</body>\n"
                + "</html>";
        return htmlString;
    }

    public static String answer(Request req, Response resp) {
        String set1 = req.queryParams("DataOne");
        String[] particion1 = set1.split(";");
        LinkedList linkedList = new LinkedList();
        for (String num : particion1) {
            linkedList.addNode(Double.parseDouble(num));
        }
        int length = linkedList.getLength();
        //Calculo de desvicacion estandar
        Double media = servicios.calculateMean(linkedList);
        Double desviacionEstandar = servicios.calculateStandardDeviation(linkedList);

        String tabla = "";
        for (int i = 0; i < length; i++) {
            tabla += genTable("-", particion1[i]);
        }
        
        String respuesta = "<!doctype html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\"\n"
                + "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n"
                + "    <title>Document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <h1>AREP-LAB 2</h1>\n"
                + "\n"
                + "    <h2>Los resultados se presentan a continuacion</h2>\n"
                + "    <table>\n"
                + "        <tr>\n"
                + "            <th>Column 1</th>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <th>Conjuto de datos #1</th>\n"
                + "        </tr>\n"
                + tabla
                + "    </table>\n"
                + "\n"
                + "La media: " + String.format("%.2f", media) + "\n" + "<br>"
                + "La desviacion estandar: " + String.format("%.2f", desviacionEstandar) + "\n" + "<br>"
                + "\n"
                + "\n"
                + "\n"
                + "</body>\n"
                + "</html>";
        return respuesta;
    }

    static String genTable(String num1, String num2) {
        return "<tr>\n"
                + "<th>" + num1 + "</th>\n"
                + "</tr>\n";

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));

        }
        linkedList = new LinkedList();
        return 4567; //returns default port if heroku-port isn't set (i.e.on localhost)
    }
}
