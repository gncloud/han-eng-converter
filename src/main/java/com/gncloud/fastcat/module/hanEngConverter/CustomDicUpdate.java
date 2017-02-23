package com.gncloud.fastcat.module.hanEngConverter;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by gncloud on 2017-02-21.
 *
 */
public class CustomDicUpdate {
    private String CustomDicPath ;
    public CustomDicUpdate(String customDicPath){
        this.CustomDicPath = customDicPath;
    }
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
    public void insertKeyword(String keyword){
        AlphaToHan ath = new AlphaToHan();
        DicSearch ds = new DicSearch(CustomDicPath);
        Map<String, String> map = new HashMap<String, String>();

        String key = keyword;
        FileWriter fw;
        key = this.strReplace(key);
        String[] keylist = key.split("\\s+");
        for(String k : keylist){
            if(this.combineCheck(ath.alphaTohan(k)) && !ds.search(k)){
                map.put(k,k);
            }
        }
        Iterator<String> iterator = map.keySet().iterator();
        try{
            fw = new FileWriter(CustomDicPath, true);
            while(iterator.hasNext()){
                String iKey = (String) iterator.next();
                fw.write("\n"+iKey.toLowerCase());
            }
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
