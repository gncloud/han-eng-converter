package com.gncloud.fastcat.module.hanEngConverter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by gncloud on 2017-02-23.
 */
public class TempFile {

    private boolean combineCheck(String keyword){
        AlphaToHan ath = new AlphaToHan();
        String convertKeyword = ath.alphaTohan(keyword);
        for(int i = 0; i<convertKeyword.length();i++){
            if(convertKeyword.charAt(i) < 0xAC00 || convertKeyword.charAt(i) > 0xD7AF){
                return false;
            }
        }
        return true;
    }
    public static String strReplace(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str =str.replaceAll(match, " ");
        return str;
    }

    public static void main(String[] args){
        AlphaToHan ath = new AlphaToHan();
        TempFile tf = new TempFile();
        DicSearch ds = new DicSearch("./dic/custom.noun.txt");

        String key = "";
        key = tf.strReplace(key);
        String[] keylist = key.split("\\s+");

        Map<String, String> map = new HashMap<String, String>();
        for(int i = 0; i< keylist.length ; i++){
            if(tf.combineCheck(ath.alphaTohan(keylist[i])) && !ds.search(keylist[i])){
                map.put(keylist[i],keylist[i]);
            }
        }
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String iKey = (String) iterator.next();
            System.out.println(iKey);
        }
    }
}
