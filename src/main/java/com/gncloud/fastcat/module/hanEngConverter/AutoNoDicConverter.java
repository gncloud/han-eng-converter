package com.gncloud.fastcat.module.hanEngConverter;

/**
 * Created by 전제현 on 2017. 5. 22.
 * AutoConverter와는 달리 검색엔진 사전을 사용하지 않고 변환을 한다.
 */
public class AutoNoDicConverter {

    private static String ENG = "ENG";
    private static String HAN = "HAN";
    private String convertType;

    public AutoNoDicConverter(String convertType) {
        if (convertType.equalsIgnoreCase(ENG) || convertType.equalsIgnoreCase(HAN)) {
            this.convertType = convertType;
        } else {
            this.convertType = ENG;
        }
    }

    public String convert(String keyword) throws Exception {

        String[] wordList = keyword.split("\\s+");
        StringBuilder sb = new StringBuilder();
        HanToAlpha hta = new HanToAlpha();
        AlphaToHan ath = new AlphaToHan();

        for (int i = 0; i < wordList.length; i++) {

            String word = wordList[i];
            String englishWord = hta.hanToAlpha(word);
            String hangulWord = ath.alphaToHan(word);

            if (ENG.equalsIgnoreCase(convertType)) {
                if(sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(englishWord);
            } else if (HAN.equalsIgnoreCase(convertType)) {
                if(sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(hangulWord);
            } else {
                if(sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(word);
            }
        }

        return sb.toString();
    }
}
