package com.gncloud.fastcat.module.hanEngConverter;

import org.fastcatsearch.ir.dic.CommonDictionary;
import org.fastcatsearch.ir.dictionary.SetDictionary;
import org.fastcatsearch.ir.io.CharVector;

/**
 * Created by gncloud on 2017-02-17.
 */
public class Converter { //입력된 랜덤한 스트링을 최적의 검색결과로 전달 해주는 클래스

    private WordnetSearcher wordnetSearcher;
    private CommonDictionary commonDictionary;

    public Converter(CommonDictionary commonDictionary) {
        this.wordnetSearcher = new WordnetSearcher();
        this.commonDictionary = commonDictionary;
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
                    String hangul = ath.alphaTohan(word);
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
        } else if(commonDictionary != null) {
            CharVector term = new CharVector(word);
            if(commonDictionary.find(term) != null) {
                return true;
            } else {
                SetDictionary dic = (SetDictionary) commonDictionary.getDictionary("user");
                if(dic != null) {
                    return dic.set().contains(term);
                }
            }
        }
        return false;
    }
}