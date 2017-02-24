package com.gncloud.fastcat.module.hanEngConverter;

import org.fastcatsearch.ir.dic.CommonDictionary;
import org.fastcatsearch.ir.dictionary.SetDictionary;
import org.fastcatsearch.ir.io.CharVector;

import java.util.Set;

/**
 * Created by gncloud on 2017-02-17.
 */
public class Converter { //입력된 랜덤한 스트링을 최적의 검색결과로 전달 해주는 클래스

    private static final String USER_DICTIONARY_NAME = "user";

    private WordnetSearcher wordnetSearcher;
    private CommonDictionary commonDictionary;
    private Set<String> testDictionary;

    public Converter() {
        this.wordnetSearcher = new WordnetSearcher();
    }
    public Converter(CommonDictionary commonDictionary) {
        this.wordnetSearcher = new WordnetSearcher();
        this.commonDictionary = commonDictionary;
    }

    public Converter(Set<String> testDictionary) {
        this.wordnetSearcher = new WordnetSearcher();
        this.testDictionary = testDictionary;
    }
//    private boolean isAllHangul(String word) {
//        for (int i = 0; i < word.length(); i++) {
//            if (word.charAt(i) < 0xAC00 || word.charAt(i) > 0xD7AF) {
//                return false;
//            }
//        }
//        return true;
//    }

    public String convert(String keyword) throws Exception {
        String[] wordList = keyword.split("\\s+");
        StringBuilder sb = new StringBuilder();
        HanToAlpha hta = new HanToAlpha();
        AlphaToHan ath = new AlphaToHan();
        for (int i = 0; i < wordList.length; i++) {
            String word = wordList[i];
            if (foundInDictionary(word)) {
                if(sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(word);

            } else {
                String english = hta.hanToAlpha(word);
                if (foundInDictionary(english)) {
                    if(sb.length() > 0) {
                        sb.append(" ");
                    }
                    sb.append(english);
                } else {
                    String hangul = ath.alphaToHan(word);
                    if (foundInDictionary(hangul)) {
                        if(sb.length() > 0) {
                            sb.append(" ");
                        }
                        sb.append(hangul);
                    } else {
                        if(sb.length() > 0) {
                            sb.append(" ");
                        }
                        sb.append(word);
                    }
                }
            }
        }

        return sb.toString();
    }

    private boolean foundInDictionary(String word) {
        if(wordnetSearcher.search(word)){
            return true;
        } else {
            if(commonDictionary != null) {
                CharVector term = new CharVector(word);
                term.setIgnoreCase(); //대소문자구분 안함.
                if(commonDictionary.find(term) != null) {
                    return true;
                } else {
                    SetDictionary dic = (SetDictionary) commonDictionary.getDictionary(USER_DICTIONARY_NAME);
                    if(dic != null) {
                        return dic.set().contains(term);
                    }
                }
            }
            if(testDictionary != null) {
                return testDictionary.contains(word);
            }
        }
        return false;
    }
}