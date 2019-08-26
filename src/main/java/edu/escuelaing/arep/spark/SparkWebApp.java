/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.spark;

import edu.escuelaing.arep.servicios.LinkedList;
import static spark.Spark.*;
import edu.escuelaing.arep.servicios.Servicios;
import spark.*;

public class SparkWebApp {

    private static LinkedList linkedList;
    private static Servicios servicios;

    
    public static void main(String[] args) {
        port(getPort());
        //staticFileLocation("/static");
        Spark.staticFiles.location("/static"); //para que abra automaticamente index.html

        get("/hello", (request, response) -> "Hello Mundo", new JsonTransformer());

        get("/hello/:name", (request, response) -> {
            return "Hello, " + request.params(":name");
        });

        get("/home", (request, response) -> {
            response.redirect("index.html");
            return null;
        });
        /*
        get("/calculo",(req,resp)->answer(req,resp));

        get("/calculadora", (request, response) -> answer(request, response));*/
        //get("/index", (req, resp) -> pageIndex(req, resp));
        //get("/calculo", (req, resp) -> Calculo(req, resp));
        get("/results", (req, resp) -> answer(req, resp));
    }
    
    public static String answer(Request request, Response resp) {
        String numeros = request.queryParams("datos");
        String[] numerosSplit = numeros.split(",");
        LinkedList linkedList = new LinkedList();
        for (String num : numerosSplit) {
            linkedList.addNode(Double.parseDouble(num));
        }
        int length = linkedList.getLength();
        Double mean = servicios.calculateMean(linkedList);
        Double standardDeviation = servicios.calculateStandardDeviation(linkedList);
        String meanS = String.format("%.2f", mean);
        String standardDeviationS = String.format("%.2f", standardDeviation);
        String tabla = "";
        for (int i = 0; i < length; i++) {
            tabla += genTable(numerosSplit[i]);
        }

        String respuesta = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "    <head>\n"
                + "        <title>Spark App</title>\n"
                + "        <meta charset=\"utf-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
                + "        <meta name=\"description\" content=\"\">\n"
                + "        <meta name=\"author\" content=\"\">\n"
                + "        <!-- Bootstrap core CSS -->\n"
                + "        <link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n"
                + "        <!-- Custom fonts for this template -->\n"
                + "        <link href=\"https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900\" rel=\"stylesheet\">\n"
                + "        <!-- Custom styles for this template -->\n"
                + "        <link href=\"css/one-page-wonder.min.css\" rel=\"stylesheet\">\n"
                + "    </head>\n"
                + "\n"
                + "    <body style=\"background-image: url('imagenes/fondo.jpg')\">\n"
                + "        <form action=\"/results\" method=\"GET\">\n"
                + "            <!-- Navigation -->\n"
                + "            <nav class=\"navbar navbar-expand-lg navbar-dark navbar-custom fixed-top\">\n"
                + "                <div class=\"container\">\n"
                + "                    <a class=\"navbar-brand\" href=\"#\">AREP-Calculadora</a>\n"
                + "                    <a class=\"navbar-brand\" href=\"#\">Amalia Alfonso</a>\n"
                + "                    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
                + "                    </button>\n"
                + "                </div>\n"
                + "            </nav>\n"
                + "            <header class=\"masthead text-center text-white\">\n"
                + "                <div class=\"container\" style=\"text-align:center;\">\n"
                + "                    <h5 class=\"masthead-heading mb-0\" style=\"color: indigo; font-size: 3.2em\">Media y Desviación Estándar</h5>\n"
                + "                    <br>\n"
                + "                    <label style=\"color: black\">Números</label>\n"
                + "                    <input type=\"text\" placeholder=\"Ejemplo: 3.5,69.1,1.2\" name=\"datos\"> \n"
                + "                    <br>\n"
                + "                    <br>\n"
                + "                    <input type=\"submit\" class=\"btn btn-primary btn-xl\" value=\"Calcular\">\n"
                + "                    <ul id=\"estiloLista\" class=\"list-group\">\n"
                + "                         <li class=\"list-group-item\" style=\"background-color: rgb(51, 154, 219)\">Números</li>\n"                        
                +                           tabla
                + "                    </</ul>\n"
                + "                    <br>\n"
                + "                     <h3 class=\"masthead-heading mb-0\" style=\"color: black; font-size: 1.2em\"> Media = "+meanS+"</h3>\n"
                + "                     <h3 class=\"masthead-heading mb-0\" style=\"color: black; font-size: 1.2em\"> Desviación Estándar = "+standardDeviationS+"</h3>\n"
                + "                </div>\n"
                + "            </header>\n"
                + "\n"
                + "            <!-- Bootstrap core JavaScript -->\n"
                + "            <script src=\"vendor/jquery/jquery.min.js\"></script>\n"
                + "            <script src=\"bootstrap/js/bootstrap.bundle.min.js\"></script>\n"
                + "        </form>\n"
                + "    </body>\n"
                + "</html>";
        return respuesta;
    }
    
    static String genTable(String num) {
        return "<li class=\"list-group-item\">"
                + "<th class=\"list-group-item\">" + num + "</th>\n"
                + "</li>\n";

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e.on localhost)
    }
}
