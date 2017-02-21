package com.gncloud.fastcat.module.hanEngConverter;


import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.dictionary.Dictionary;
import net.didion.jwnl.dictionary.file.DictionaryFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by gncloud on 2017-02-16.
 */

public class DicSearch {

    public void search(String keyword){
        try {
            long start = System.currentTimeMillis();
            JWNL.initialize(new FileInputStream("./dic/file_properties.xml"));
            Dictionary dic = Dictionary.getInstance();
            String resNone = dic.getIndexWord(POS.NOUN, keyword) != null ? dic.getIndexWord(POS.NOUN, keyword).toString():"";
            String resAdjective = dic.getIndexWord(POS.ADJECTIVE, keyword) !=null? dic.getIndexWord(POS.ADJECTIVE, keyword).toString():"";
            String resAdverb = dic.getIndexWord(POS.ADVERB, keyword) !=null? dic.getIndexWord(POS.ADVERB, keyword).toString():"";
            String resVerb = dic.getIndexWord(POS.VERB,keyword) != null? dic.getIndexWord(POS.VERB, keyword).toString():"";

            long end = System.currentTimeMillis();
            System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JWNLException e){
            e.printStackTrace();
        }
    }

    public int searchResultCount(String keyword){
        // 0 -> 검색결과 없음
        // -1 -> 사전검색 Exception
        // 1 -> 검색결과 있음

        try {
            JWNL.initialize(new FileInputStream("./dic/file_properties.xml"));
            Dictionary dic = Dictionary.getInstance();
            String resNone = dic.getIndexWord(POS.NOUN, keyword) != null ? dic.getIndexWord(POS.NOUN, keyword).toString():"";
            String resAdjective = dic.getIndexWord(POS.ADJECTIVE, keyword) !=null? dic.getIndexWord(POS.ADJECTIVE, keyword).toString():"";
            String resAdverb = dic.getIndexWord(POS.ADVERB, keyword) !=null? dic.getIndexWord(POS.ADVERB, keyword).toString():"";
            String resVerb = dic.getIndexWord(POS.VERB,keyword) != null? dic.getIndexWord(POS.VERB, keyword).toString():"";

            if(resNone.length() + resAdjective.length() + resAdverb.length() + resVerb.length() <= 0){
                return 0;
            }
            return 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (JWNLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args){
        DicSearch ds = new DicSearch();
        System.out.println(ds.searchResultCount("nike"));
        System.out.println(ds.searchResultCount("adidas"));
    }
}
