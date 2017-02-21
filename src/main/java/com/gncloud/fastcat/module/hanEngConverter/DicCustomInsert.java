package com.gncloud.fastcat.module.hanEngConverter;


//import net.sf.extjwnl.JWNLException;
//import net.sf.extjwnl.JWNLIOException;
//import net.sf.extjwnl.data.*;
//import net.sf.extjwnl.dictionary.Dictionary;

import java.io.*;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Iterator;

/**
 * Created by gncloud on 2017-02-21.
 */
public class DicCustomInsert {
//    public void insertKeyword(String keyword) {
//        try{
//            FileInputStream inputStream = new FileInputStream("./dic/file_properties.xml");
//            Dictionary dictionary = Dictionary.getInstance(inputStream);
//            Iterator<Synset> synset = dictionary.getSynsetIterator(POS.NOUN);
//
//            dictionary.edit();
//            Synset newSynset = new Synset(dictionary, POS.NOUN);
//            IndexWord idxWord = new IndexWord(dictionary, keyword, POS.NOUN,newSynset);
//            Synset topmostSynset = synset.next();
//            Pointer newPointer = new Pointer(PointerType.HYPONYM, topmostSynset, newSynset);
//            topmostSynset.getPointers().add(newPointer);
//
//            dictionary.save();
//            dictionary.close();
//            inputStream.close();
//
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }catch (JWNLIOException e){
//            e.printStackTrace();
//        } catch (JWNLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void insertKeyword(String keyword){
        int match = 0;
        BufferedReader in = null;
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            in = new BufferedReader(new FileReader("./dic/dict/custom.txt"));
            String s = "";
            while((s = in.readLine())!=null){
                if(s.equalsIgnoreCase(keyword)){
                    match = 1;
                    break;
                }
            }
            in.close();
            if(match != 1) {
                fw = new FileWriter("./dic/dict/custom.txt", true);
                fw.write("\n"+keyword);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null && fw !=null){
                try {
                    in.close();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args){
        DicCustomInsert dci = new DicCustomInsert();
        dci.insertKeyword("adidas");
    }
}
