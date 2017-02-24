package com.gncloud.fastcat.module.hanEngConverter;

import net.sf.extjwnl.dictionary.Dictionary;
import org.fastcatsearch.ir.dictionary.SetDictionary;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DicRepository {
    private static DicRepository instance;
    private static final String WORDNET_DIC_CONF = "/net/sf/extjwnl/data/wordnet/wn31/res_properties.xml";
    private Dictionary wordnetDictionary;
    private SetDictionary userDictionary;

    private DicRepository(SetDictionary userDictionary) {
        try {
            wordnetDictionary = Dictionary.getResourceInstance(WORDNET_DIC_CONF);
        } catch (Exception ignore) { }
        this.userDictionary = userDictionary;
    }
    public static synchronized DicRepository getInstance(SetDictionary userDictionary) {
        if (instance == null) {
            instance = new DicRepository(userDictionary);
        }
        return instance;
    }
    public Dictionary getWordnetDictionary(){
        return wordnetDictionary;
    }
    public SetDictionary getUserDictionary(){
        return userDictionary;
    }
}


