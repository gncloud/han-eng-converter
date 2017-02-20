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

    public static void search(String keyword){
        long start = System.currentTimeMillis();
        try {
            for(int i = 0; i<1000; i++){
                JWNL.initialize(new FileInputStream("./dic/file_properties.xml"));
                Dictionary dic = Dictionary.getInstance();
                System.out.println(dic.getIndexWord(POS.NOUN, keyword));
                System.out.println(dic.getIndexWord(POS.ADVERB,"pretty"));
                System.out.println(dic.getIndexWord(POS.VERB,"run"));
                System.out.println(dic.getIndexWord(POS.ADJECTIVE,keyword));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JWNLException e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
    }

    public static void main(String[] args){
        DicSearch ds = new DicSearch();
        ds.search("beautiful");
    }
}
