package com.amar;


import org.apache.spark.api.java.JavaSparkContext;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.amar.JsonUtil.json;

/**
 * Created by amarendra on 08/02/17.
 */
public class WordCountController {

    private WordCountService wordCountService;

    public WordCountController(final WordCountService wordCountService,
                               final JavaSparkContext sc,
                               final String arg) {
        this.wordCountService = wordCountService;

        spark.Spark.get("/wordCount", new Route() {
            @Override
            public Object handle(Request request, Response response) {

                return wordCountService.test(sc,arg);
            }
        }, json());
    }


}
