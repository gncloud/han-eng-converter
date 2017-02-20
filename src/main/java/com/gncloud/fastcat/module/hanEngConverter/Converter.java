package com.gncloud.fastcat.module.hanEngConverter;

/**
 * Created by gncloud on 2017-02-17.
 */
public class Converter { //입력된 랜덤한 스트링을 최적의 검색결과로 전달 해주는 클래스

    private static final String[] KORCHOKEYTOENG = { "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", "T", "d", "w",
            "W", "c", "z", "x", "v", "g" };

    private static final String CHOSTR = "rRseEfaqQtTdwWczxvg";
    private static final String JUNSTR = "kKiIjJuUhHyYnNbBmMlLoOpP";

    private static final String[] KORJUNKEYTOENG = { "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl", "y",
            "n", "nj", "np", "nl", "b", "m", "ml", "l" };
    private static final String[] KORJONKEYTOENG = { "", "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", "fq",
            "ft", "fx", "fv", "fg", "a", "q", "qt", "t", "T", "d", "w", "c", "z", "x", "v", "g" };

    private static final String[] CONVERTKEY = {// ㄱ~ ㅣ 자음모음 순서대로 나열한 값
            "r","R","rt","s","sw","sg","e","E","f","fr", "fa", "fq", "ft", "fx", "fv", "fg", "a", "q", "Q", "qt", "t",
            "T", "d", "w", "W", "c", "z", "x", "v", "g", "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl",
            "y", "n", "nj", "np", "nl", "b", "m", "ml", "l" };

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
        String tempString = "";
        AlphaToHanAll atha = new AlphaToHanAll();
        HanToAlpha hta = new HanToAlpha();

        String convertAlpha = hta.hanToAlpha(keyword);
        String convertHan = atha.alphaToHanAll(keyword);

        int i = 0;

        boolean engFlag = true;
        //입력된 키워드에 대한 사전검색은 알파벳 변환후 한번만 하도록
        //hanToAlpha



        while (i < keyword.length()){
            char tempChar = keyword.charAt(i);
            if(tempChar >= 0xAC00 && tempChar <= 0xD7AF){ //완성형 한글
                convertString = hta.hanToAlpha(keyword);
            }else if(tempChar >= 0x3131 && tempChar <= 0x318E){
                convertString = atha.alphaToHanAll(keyword);
            }else{
                convertString = hta.hanToAlpha(keyword);

            }
        }

        return convertString;
    }

    private String hanToEng(String keyword){
        return "";
    }

    private String engToHan(String keyword){
        return "";
    }

}
