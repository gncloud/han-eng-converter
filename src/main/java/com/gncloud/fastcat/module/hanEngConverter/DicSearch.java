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

    public synchronized boolean search(String keyword)  {
        try {
            IndexWord resNoun = DicInit.getInstance().dictionary.getIndexWord(POS.NOUN,keyword);
            IndexWord resAdjective = DicInit.getInstance().dictionary.getIndexWord(POS.ADJECTIVE,keyword);
            IndexWord resAdverb = DicInit.getInstance().dictionary.getIndexWord(POS.ADVERB,keyword);
            IndexWord resVerb = DicInit.getInstance().dictionary.getIndexWord(POS.VERB, keyword);
            if(resNoun !=null || resVerb !=null || resAdverb !=null || resAdjective !=null){
                return true;
            }else {
                return false;
            }
        } catch (Exception ignore){
            return false;
        }
    }
}
