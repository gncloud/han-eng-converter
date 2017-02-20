package com.gncloud.fastcat.module.hanEngConverter;

/**
 * Created by gncloud on 2017-02-20.
 */
public class AlphaToHanFinal {

//    private static final String JASTR = "rRseEfaqQtTdwWczxvg";
    private static final String JASTR = "rRsSeEfFaAqQtTdDwWcCzZvVxXgG";
    private static final String MOSTR = "kKiIjJuUhHyYnNbBmMlLoOpP";
    private static final String ENG_KEY = "rRseEfaqQtTdwWczxvgkoiOjpuPhynbml";

    private static final String[] KORCHOKEYTOENG = { "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", "T", "d", "w",
            "W", "c", "z", "x", "v", "g" };

    private static final String[] KORJUNKEYTOENG = { "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl", "y",
            "n", "nj", "np", "nl", "b", "m", "ml", "l" };
    private static final String[] KORJONKEYTOENG = { "", "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", "fq",
            "ft", "fx", "fv", "fg", "a", "q", "qt", "t", "T", "d", "w", "c", "z", "x", "v", "g" };

    private static final String[] CONVERTKEY = {// ㄱ~ ㅣ 자음모음 순서대로 나열한 값
            "r","R","rt","s","sw","sg","e","E","f","fr", "fa", "fq", "ft", "fx", "fv", "fg", "a", "q", "Q", "qt", "t",
            "T", "d", "w", "W", "c", "z", "x", "v", "g", "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl",
            "y", "n", "nj", "np", "nl", "b", "m", "ml", "l" };

    public static void main(String[] args){
        AlphaToHanFinal athf = new AlphaToHanFinal();
        System.out.println(athf.alphaTohan("channel"));
    }

    private String completeCombine(String cho, String jun, String jon){
        int choIdx = 0, junIdx = 0, jongIdx = 0;

        for(int i = 0; i<KORCHOKEYTOENG.length; i++){
            if(KORCHOKEYTOENG[i].equals(cho)){
                choIdx = i * 28 * 21;
            }
        }
        for(int i = 0; i<KORJUNKEYTOENG.length;i++){
            if(KORJUNKEYTOENG[i].equals(jun)){
                junIdx = i* 28;
            }
        }
        for(int i = 0 ; i<KORJONKEYTOENG.length;i++){
            if(KORJONKEYTOENG[i].equals(jon)){
                jongIdx = i + 1;
            }
        }
        String combineString = Character.toString((char) (0xAC00 + choIdx + junIdx + jongIdx));

        return combineString;
    }

    public String alphaTohan(String keyword){
        String convertString = "";
        int nCho = -1, nJun = -1, nJon = -1;

        for( int i = 0; i<keyword.length();i++){
            char ch = keyword.charAt(i);
            int p = ENG_KEY.indexOf(ch);
            if(p == -1){
                if(nCho != -1){
                    if(nJun != -1){
                        convertString += completeCombine(
                                Character.toString((char) nCho),
                                Character.toString((char) nJun),
                                Character.toString((char) nJon));
                    }else{
                        convertString += KORCHOKEYTOENG[nCho];
                    }
                } else {
                    if(nJun != -1){
                        convertString += KORJUNKEYTOENG[nJun];
                    }else if(nJon != -1){
                        convertString += KORJONKEYTOENG[nJon];
                    }
                }
                nCho = -1;
                nJun = -1;
                nJon = -1;
            }else if( p < 19){ // 자음
                if (nJun != -1){
                    if(nCho == -1){
                        convertString += KORJUNKEYTOENG[nJun];
                        nJun = -1;
                        for(int k = 0; k<KORCHOKEYTOENG.length;k++){

                        }

                    }else{

                    }
                }
            }
        }

        return convertString;
    }
}
