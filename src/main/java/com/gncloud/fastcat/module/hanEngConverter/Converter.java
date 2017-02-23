package com.gncloud.fastcat.module.hanEngConverter;

/**
 * Created by gncloud on 2017-02-17.
 *
 */
public class Converter { //입력된 랜덤한 스트링을 최적의 검색결과로 전달 해주는 클래스

    DicSearch ds;

    public Converter(String path){
        this.ds = new DicSearch(path);
    }

    private boolean combineCheck(String keyword){
        AlphaToHan ath = new AlphaToHan();
        String convertKeyword = ath.alphaTohan(keyword);
        for(int i = 0; i<convertKeyword.length();i++){
            if(convertKeyword.charAt(i) < 0xAC00 || convertKeyword.charAt(i) > 0xD7AF){
                return false;
            }
        }
        return true;
    }

    public String convert(String keyword) throws Exception{
        AlphaToHan ath = new AlphaToHan();
        HanToAlpha hta = new HanToAlpha();

        String convertAlpha = hta.hanToAlpha(keyword);
        String returnHan = ath.alphaTohan(convertAlpha);

        boolean searchRes = ds.search(convertAlpha); // 알파벳 검색

        if(!searchRes){
            if(combineCheck(returnHan)) { //조합가능
                return returnHan;
            }else{
                return keyword;
            }
        }else{
            return convertAlpha;
        }
    }
}