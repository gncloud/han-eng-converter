package com.gncloud.fastcat.module.hanEngConverter;

/**
 * Created by gncloud on 2017-02-17.
 *
 */
public class AlphaToHanAll {
    enum CodeType  {
        chosung, jungsung, jongsung
    }

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
        AlphaToHanAll atha = new AlphaToHanAll();
//        System.out.println(atha.alphaToHanAll("가rk o/kj나다pgmlzoxckjv"));
//        System.out.println(atha.alphaToHanAll("adidas"));
        System.out.println(atha.alphaToHanAll("annrel"));
    }

    public String alphaToHanAll(String engString){ // 최종 변환값 스트링 형태로 리턴해주는 메소드
        int choCode = 0, junCode = 0, jonCode = 0;
        int tempJunCode, tempJonCode;
        StringBuffer result = new StringBuffer();
        
        for(int i = 0; i<engString.length();i++){
            int temp_match = CHOSTR.indexOf(engString.charAt(i));
            int temp_match_next = i<= engString.length() ? JUNSTR.indexOf(engString.charAt(i+1)):-1;

            if(engString.substring(i,i+1).isEmpty()){
                result.append(" ");
                i++;
            }

            if(temp_match == -1 || temp_match_next <= -1){
                result.append(!engString.substring(i,i+1).isEmpty() ? engString.substring(i,i+1):"");
            }else{
                choCode = getCode(CodeType.chosung, engString.substring(i, i + 1));
                i++;

                tempJunCode = getDoubleJun(i, engString);
                if(tempJunCode != -1){
                    junCode = tempJunCode;
                    i += 2;
                }else {
                    junCode = getSingleJun(i, engString);
                    i++;
                }

                tempJonCode = getDoubleJon(i,engString);
                if(tempJonCode != -1){
                    jonCode = tempJonCode;
                    tempJunCode = getSingleJun(i + 2, engString);
                    if(tempJunCode != -1){
                        jonCode = getSingleJon(i, engString);
                    }else{
                        i++;
                    }
                }else{
                    tempJunCode = getSingleJun(i + 1 , engString);

                    if(tempJunCode != -1){
                        jonCode = 0;
                        i --;
                    }else {
                        jonCode = getSingleJon(i, engString);
                        if(jonCode == -1)
                            jonCode = 0;
                    }
                }
                result.append((char) (0xAC00 + choCode + junCode + jonCode));
            }
        }
        return result.toString();
    }

    private int getDoubleJon(int idx, String engString){
        int result;
        if((idx + 2)>engString.length()){
            return -1;
        }else{
            result = getCode(CodeType.jongsung, engString.substring(idx, idx+2).toLowerCase());
            if(result != -1){
                return result;
            }else{
                return -1;
            }
        }
    }

    private int getSingleJon(int idx, String engString){
        if((idx + 1 ) <= engString.length()){
            return getCode(CodeType.jongsung, engString.substring(idx, idx +1));
        }else{
            return -1;
        }
    }

    private int getDoubleJun(int idx,String engString){
        int result;
        if((idx + 2)>engString.length()){
            return -1;
        }else{
            result = getCode(CodeType.jungsung, engString.substring(idx, idx+2).toLowerCase());
            if(result != -1){
                return result;
            }else{
                return -1;
            }
        }
    }

    private int getSingleJun(int idx, String engString){
        if((idx + 1 ) <= engString.length()){
            return getCode(CodeType.jungsung, engString.substring(idx, idx +1));
        }else{
            return -1;
        }
    }


    private int getCode(CodeType type, String ch){
        String init = "rRseEfaqQtTdwWczxvg";
        String[] mid = { "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl", "y", "n", "nj", "np", "nl", "b",
                "m", "ml", "l" };
        String[] fin = { "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", "fq", "ft", "fx", "fv", "fg", "a", "q",
                "qt", "t", "T", "d", "w", "c", "z", "x", "v", "g" };

        switch (type){
            case chosung:
                int idx = init.indexOf(ch);
                if( idx != -1){
                    return idx * 21 * 28;
                }
                break;
            case jungsung:
                for (int i = 0; i< mid.length; i++){
                    if(mid[i].equals(ch)){
                        return i * 28;
                    }
                }
                break;
            case jongsung:
                for (int i = 0; i< fin.length; i++){
                    if (fin[i].equals(ch)){
                        return i + 1;
                    }
                }
                break;
            default:
                System.out.println("error");
        }
        return -1;
    }
}
