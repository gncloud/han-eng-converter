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