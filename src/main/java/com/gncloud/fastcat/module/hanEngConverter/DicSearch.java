package com.gncloud.fastcat.module.hanEngConverter;


import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.JWNLIOException;
import net.sf.extjwnl.data.*;
import net.sf.extjwnl.dictionary.Dictionary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by gncloud on 2017-02-16.
 */

public class DicSearch {
    public int searchResultCount(String keyword){
        // 0 -> 검색결과 없음
        // -1 -> 사전검색 Exception
        // 1 -> 검색결과 있음

        try{
            FileInputStream inputStream = new FileInputStream("./dic/file_properties.xml");
            Dictionary dictionary = Dictionary.getInstance(inputStream);

            System.out.println(dictionary.getIndexWord(POS.NOUN, keyword));

            dictionary.close();
            inputStream.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (JWNLIOException e){
            e.printStackTrace();
        } catch (JWNLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void main(String[] args){
        DicSearch ds = new DicSearch();
//        System.out.println(ds.searchResultCount("nike"));
        System.out.println(ds.searchResultCount("adidas"));
//        System.out.println(ds.searchResultCount("chanel"));
//        System.out.println(ds.searchResultCount("channel"));
    }
}
