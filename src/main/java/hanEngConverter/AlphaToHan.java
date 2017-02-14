package hanEngConverter;

/**
 * Created by gncloud on 2017-02-13.
 */

public class AlphaToHan {
    enum CodeType  {
        chosung, jungsung, jongsung
    }

    public String alphaToHan(String engString){
        int choCode = 0, junCode = 0, jonCode = 0;
        int tempJunCode, tempJonCode;
        StringBuffer result = new StringBuffer();

        for( int i = 0; i < engString.length(); i++){
            choCode = getCode(CodeType.chosung, engString.substring(i, i + 1) );
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
        return result.toString();
    }

    private int getDoubleJon(int idx, String engString){
        int result;
        if((idx + 2)>engString.length()){
            return -1;
        }else{
            result = getCode(CodeType.jongsung, engString.substring(idx, idx+2));
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
            result = getCode(CodeType.jungsung, engString.substring(idx, idx+2));
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