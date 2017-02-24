package com.gncloud.fastcat.module.hanEngConverter;


import net.sf.extjwnl.data.*;
import net.sf.extjwnl.dictionary.Dictionary;
import org.fastcatsearch.ir.dictionary.SetDictionary;

import java.util.Map;
import java.util.Set;

/**
 * Created by gncloud on 2017-02-16.
 *
 */
public class WordnetSearcher {

    private Dictionary wordnetDictionary;

    private static final String WORDNET_DIC_CONF = "/net/sf/extjwnl/data/wordnet/wn31/res_properties.xml";

    public WordnetSearcher() {
        try {
            wordnetDictionary = Dictionary.getResourceInstance(WORDNET_DIC_CONF);
        } catch (Exception ignore) { }
    }

    public boolean search(String keyword)  {
        try {
            IndexWord resNoun = wordnetDictionary.getIndexWord(POS.NOUN,keyword);
            if(resNoun != null) {
                return true;
            }
            IndexWord resAdjective =wordnetDictionary.getIndexWord(POS.ADJECTIVE,keyword);
            if(resAdjective != null) {
                return true;
            }
            IndexWord resAdverb =wordnetDictionary.getIndexWord(POS.ADVERB, keyword);
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
