package com.gncloud.fastcat.module.hanEngConverter;

/**
 * Created by gncloud on 2017-02-17.
 */
public class Converter { //입력된 랜덤한 스트링을 최적의 검색결과로 전달 해주는 클래스
    public static void main(String[] args){
        Converter con = new Converter();
        System.out.println(con.Converter("버qj리"));
    }

    // todo 1. 한글조합이 안된다면 그 부분만 영문변환 시도
    // todo 2. 영문이 사전에 없다면 좌우에 영문을 붙혀서 다시 시도
    // todo 3. 한글결과가 조합이 괜찮다면 그대로 사용.
    // todo 4. 조합이 이상하면 버리고 기존 영문사용.

    public String Converter(String keyword){
        String convertString = "";
        AlphaToHanAll atha = new AlphaToHanAll();
        HanToAlpha hta = new HanToAlpha();

        String convertAlpha = hta.hanToAlpha(keyword);
        String convertHan = atha.alphaToHanAll(keyword);

        if(convertAlpha.length() <= 2){ // 변환 알파벳의 크기가 2이상일때 알파벳 사전검색하도록
            return keyword;
        }else{

            return convertAlpha;
        }
    }

    private String hanToEng(String keyword){
        return "";
    }

    private String engToHan(String keyword){
        return "";
    }

}
