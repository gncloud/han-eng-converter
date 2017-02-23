package com.gncloud.fastcat.module.hanEngConverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swsong on 2017. 2. 16..
 *
 */
public class AutoConverterTest {
    @Test
    public void testAutoConvert() throws Exception{
//        DicRepository.getInstance();
        DicSearch dicSearch = new DicSearch("./dic/custom.noun.txt");
        Converter convert = new convert();

        assertEquals("버버리", convert.convert("버qj리"));
        assertEquals("버버리", convert.convert("qjqjfl"));
        assertEquals("qjqjflst", convert.convert("qjqjflst"));
        assertEquals("버버리st", convert.convert("버버리st"));

        assertEquals("클릭", convert.convert("zmfflr"));
        assertEquals("사용자", convert.convert("사용자"));

        assertEquals("adidas", convert.convert("adidas"));
        assertEquals("아디다스", convert.convert("dkelektm"));

        assertEquals("nike",convert.convert("nike"));
        assertEquals("nike", convert.convert("ㅜㅑㅏㄷ"));
        assertEquals("nike", convert.convert("ㅜㅑke"));

        assertEquals("asodkfj", convert.convert("asodkfj"));
        assertEquals("asodkjfoaskdj", convert.convert("asodkjfoaskdj"));
        assertEquals("asodkjf/asdf", convert.convert("asodkjf/asdf"));
        assertEquals("asodkjf/asdf", convert.convert("asodkjf/asdf"));

        assertEquals("한글", convert.convert("한rmf"));
        assertEquals("한글", convert.convert("하srmf"));
        assertEquals("한끌", convert.convert("GKSRMF"));

        assertEquals("샤넬", convert.convert("tispf"));
        assertEquals("chanel", convert.convert("chanel"));
        assertEquals("chanel", convert.convert("초무디"));
        assertEquals("CHANEL",convert.convert("CHANEL"));
        assertEquals("chanel",convert.convert("초anel"));

        assertEquals("and",convert.convert("뭉"));
        assertEquals("when", convert.convert("조두"));

        assertEquals("COMPUTER", convert.convert("COMPUTER"));
        assertEquals("computer",convert.convert("채ㅡㅔㅕㅅㄷㄱ"));
        assertEquals("computer",convert.convert("comㅔㅕㅅㄷㄱ"));
        assertEquals("computer",convert.convert("coㅡㅔㅕㅅㄷㄱ"));
        assertEquals("computer",convert.convert("coㅡㅔㅕㅅer"));
    }
}
