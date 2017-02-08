package com.amar;

/**
 * Created by amarendra on 08/02/17.
 */
public class WordCnt {

    private String word;
    private Integer count;

    public WordCnt(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
