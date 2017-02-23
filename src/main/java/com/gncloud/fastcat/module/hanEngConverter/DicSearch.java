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
            String result = customNounDictionary.get(keyword.toLowerCase());
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
            if(resAdverb != null){
                return true;
            }
            IndexWord resVerb =wordnetDictionary.getIndexWord(POS.VERB, keyword);
            if(resVerb != null){
                return true;
            }
            return false;
        } catch (Exception ignore){
            return false;
        }
    }
}
