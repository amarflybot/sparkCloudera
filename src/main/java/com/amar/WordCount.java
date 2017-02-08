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
        JavaRDD<String> textFile = sc.textFile(args[0]);
        JavaRDD<String> words = textFile.flatMap(new FlatMapFunction<String, String>() {

            @Override
                public Iterable<String> call(String s) throws Exception {
                    return Arrays.asList(s.split(" "));

            }
        });

        // count the occurrence of each word
        JavaPairRDD<String, Integer> counts = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });

        // count characters

        Iterator<Tuple2<String, Integer>> iterator = counts.collect().iterator();
        while (iterator.hasNext()){
            Tuple2<String, Integer> tuple2 = iterator.next();
            System.out.println(tuple2._1() + "  "+ tuple2._2());
        }

        //.forEach(action-> logger.info(action._1() + "  " + action._2()));

        //counts.saveAsTextFile("/mnt/555D33F16C69C1B3/sandbox/sparktest/target/save");

    }
}
