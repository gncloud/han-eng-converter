package com.gncloud.fastcat.module.hanEngConverter;

import net.sf.extjwnl.dictionary.Dictionary;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DicRepository {
    private static DicRepository instance;
    private static final String WORDNET_DIC_CONF = "/net/sf/extjwnl/data/wordnet/wn31/res_properties.xml";
    private static String customDictionaryPath;
    private static Dictionary wordnetDictionary;
    private static Map<String, String> customNounDictionary;

    private DicRepository(String customDictionaryPath) {
        try {
            this.customDictionaryPath = customDictionaryPath;
            wordnetDictionary = Dictionary.getResourceInstance(WORDNET_DIC_CONF);
            customNounDictionary = new ConcurrentHashMap<String, String>();
            //TODO customDictionaryPath 파일에서 한줄씩 읽어와서 KEYWORD 로...


            //while(ㅇㅇㅇ) {
                // String keyword = "";
                //customNounDictionary.put(keyword, keyword);
           //}
        } catch (Exception ignore) { }
    }

    public static synchronized DicRepository getInstance(String customDictionaryPath) {
        if (instance == null) {
            instance = new DicRepository(customDictionaryPath);
        }
        return instance;
    }
    public Dictionary getWordnetDictionary(){
        return wordnetDictionary;
    }

    public Map<String, String> getCustomNounDictionary(){
        return customNounDictionary;
    }

    public void saveCustomNounDictionary(){

        ///TODO map 을 ITERATOR로 받은뒤 while로 돌면서 파일에 한줄식 쓰기. BufferedWriter. customDictionaryPath 에 ...
        ///todo TESTCASE 만들기. fILE.CREAT TMPFILE....
    }
}


