package com.amar;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by amarendra on 08/02/17.
 */
public class JsonUtil {

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return new ResponseTransformer() {
            @Override
            public String render(Object o) throws Exception {
                return toJson(o);
            }
        };
    }
}
