package com.gncloud.fastcat.module.hanEngConverter;


import net.sf.extjwnl.data.*;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.Map;

/**
 * Created by gncloud on 2017-02-16.
 *
 */

public class DicSearch {

    private Dictionary wordnetDictionary;
    private Map<String, String> customNounDictionary;

    public DicSearch(String customDictPath) {
        DicRepository repo = DicRepository.getInstance(customDictPath);
        wordnetDictionary = repo.getWordnetDictionary();
        customNounDictionary = repo.getCustomNounDictionary();
    }

    public synchronized boolean search(String keyword)  {
        try {
            String result = customNounDictionary.get(keyword);
            if(result != null) {
                return true;
            }
            IndexWord resNoun = wordnetDictionary.getIndexWord(POS.NOUN,keyword);
            if(resNoun != null) {
                return true;
            }
            IndexWord resAdjective =wordnetDictionary.getIndexWord(POS.ADJECTIVE,keyword);
            if(resAdjective != null) {
                return true;
            }
            IndexWord resAdverb =wordnetDictionary.getIndexWord(POS.ADVERB,keyword);
            //TODO
            IndexWord resVerb =wordnetDictionary.getIndexWord(POS.VERB, keyword);
            //TODO
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
