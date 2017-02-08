package com.amar;


import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Spark Word Count
 *
 */
public class WordCount{

    public static Logger logger = Logger.getLogger(WordCount.class);
    public static void main( String[] args )
    {

        //  String master = "spark://amarendra-H170-D3H:7077";
        // create Spark context with Spark configuration
        SparkConf conf = new SparkConf().setAppName("Word_Count");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // read in text file and split each document into words
        String arg = args[0];
        WordCountService wordCountService = new WordCountService();
        //wordCountService.test(sc, arg);
        WordCountController wordCountController =
                new WordCountController(wordCountService, sc, arg);

    }
}
