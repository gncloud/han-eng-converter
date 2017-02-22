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



    private int combineCheck(String keyword){
        // 조합 안됨 return 0;
        // 조합 가능 return 1;
        AlphaToHan ath = new AlphaToHan();
        String convertKeyword = ath.alphaTohan(keyword);

        for(int i = 0; i<convertKeyword.length();i++){
            if(convertKeyword.charAt(i) < 0xAC00 || convertKeyword.charAt(i) > 0xD7AF)
                return 0;
        }
        return 1;
    }

    public String Converter(String keyword) throws Exception{
        String convertString = "";
        String tempString = "";
        AlphaToHan ath = new AlphaToHan();
        HanToAlpha hta = new HanToAlpha();
        DicSearch ds = new DicSearch();

        String convertAlpha = hta.hanToAlpha(keyword);
        String convertHan = ath.alphaTohan(keyword);
        String returnHan = ath.alphaTohan(convertAlpha);

        boolean searchRes = ds.search(convertAlpha); // 알파벳 검색

        if(!searchRes){
            if(combineCheck(returnHan) == 0) { //조합불가 글자가 포함
                return keyword;
            }else{
                return returnHan;
            }
        }else{
            return convertAlpha;
        }
//
//        switch (searchRes){
//            case -1 :
//                convertString = "사전검색 오류";
//                break;
//            case 0 : // 매칭되는 사전 검색없음
//                if(combineCheck(returnHan) == 0){ //조합불가 글자가 포함
//                    return keyword;
//                }else{
//                    return returnHan;
//                }
//            case 1 : // 사전 검색 결과가 있음
//                if(combineCheck(convertHan) == 0){
//                    return convertAlpha;
//                }else{
//                    return convertAlpha;
//                }
//            default:
//                break;
//        return convertString;
    }

}