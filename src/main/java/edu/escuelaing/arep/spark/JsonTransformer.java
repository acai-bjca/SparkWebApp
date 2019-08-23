package edu.escuelaing.arep.spark;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    @Override
    public String render(Object data) throws Exception {
        return new Gson().toJson(data);
    }
}