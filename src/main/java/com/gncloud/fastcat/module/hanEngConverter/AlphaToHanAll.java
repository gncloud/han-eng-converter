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
public class AlphaToHanAll {


    public static void main(String[] args){
        try {
            JWNL.initialize(new FileInputStream("./dic/file_properties.xml"));
            Dictionary dic = Dictionary.getInstance();
            System.out.println(dic.getIndexWord(POS.ADJECTIVE, "beautiful"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JWNLException e){
            e.printStackTrace();
        }
    }


}
