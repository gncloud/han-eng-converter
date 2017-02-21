package com.gncloud.fastcat.module.hanEngConverter;


import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.JWNLIOException;
import net.sf.extjwnl.data.*;
import net.sf.extjwnl.dictionary.Dictionary;

import java.io.*;
import java.util.Iterator;

/**
 * Created by gncloud on 2017-02-16.
 */

public class DicSearch {
    public int searchResultCount(String keyword){
        // 0 -> 검색결과 없음
        // -1 -> 사전검색 Exception
        // 1 -> 검색결과 있음
        FileInputStream dicInputStream = null;
        String filepath = "./dic/dict/custom.txt";
        BufferedReader in = null;
        Dictionary dictionary = null;
        int match = 0;
        try{
            dicInputStream = new FileInputStream("./dic/file_properties.xml");
            dictionary = Dictionary.getInstance(dicInputStream);
            String resNone = dictionary.getIndexWord(POS.NOUN, keyword) != null ? dictionary.getIndexWord(POS.NOUN, keyword).toString() :"";
            String resAdjective = dictionary.getIndexWord(POS.ADJECTIVE, keyword) != null ? dictionary.getIndexWord(POS.ADJECTIVE, keyword).toString() :"";
            String resAdverb = dictionary.getIndexWord(POS.ADVERB, keyword) != null ? dictionary.getIndexWord(POS.ADVERB, keyword).toString() :"";
            String resVerb = dictionary.getIndexWord(POS.VERB, keyword) != null ? dictionary.getIndexWord(POS.VERB, keyword).toString() :"";
            in = new BufferedReader(new FileReader(filepath));
            String s = "";
            while((s = in.readLine())!=null){
                if(s.equalsIgnoreCase(keyword)){
                    match += 1;
                    break;
                }
            }
            if(!resNone.isEmpty()){
                match = 1;
            }else if(!resAdverb.isEmpty()){
                match = 1;
            }else if(!resAdjective.isEmpty()){
                match = 1;
            }else if(!resVerb.isEmpty()){
                match = 1;
            }else {
                match += 0;
            }

            dictionary.close();
            dicInputStream.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return -1;
        } catch (JWNLIOException e){
            e.printStackTrace();
            return -1;
        } catch (JWNLException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }finally {
            if(in != null && dicInputStream !=null && dictionary != null){
                try {
                    dictionary.close();
                    in.close();
                    dicInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return -1;
                } catch (JWNLException e) {
                    e.printStackTrace();
                    return -1;
                }
            }
        }
        return match;
    }

    public static void main(String[] args){
        DicSearch ds = new DicSearch();
//        System.out.println(ds.searchResultCount("nike"));
        System.out.println(ds.searchResultCount("asdf"));
//        System.out.println(ds.searchResultCount("chanel"));
//        System.out.println(ds.searchResultCount("channel"));
    }
}
