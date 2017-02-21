package com.gncloud.fastcat.module.hanEngConverter;


import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.JWNLIOException;
import net.sf.extjwnl.data.*;
import net.sf.extjwnl.dictionary.Dictionary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * Created by gncloud on 2017-02-21.
 */
public class DicCustomInsert {
    public void insertKeyword(String keyword) {
        try{
            FileInputStream inputStream = new FileInputStream("./dic/file_properties.xml");
            Dictionary dictionary = Dictionary.getInstance(inputStream);
            Iterator<Synset> synset = dictionary.getSynsetIterator(POS.NOUN);

            dictionary.edit();
            Synset newSynset = new Synset(dictionary, POS.NOUN);
            IndexWord idxWord = new IndexWord(dictionary, keyword, POS.NOUN,newSynset);
            Synset topmostSynset = synset.next();
            Pointer newPointer = new Pointer(PointerType.HYPONYM, topmostSynset, newSynset);
            topmostSynset.getPointers().add(newPointer);
            dictionary.save();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (JWNLIOException e){
            e.printStackTrace();
        } catch (JWNLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        DicCustomInsert dci = new DicCustomInsert();
        dci.insertKeyword("chanel");
    }
}
