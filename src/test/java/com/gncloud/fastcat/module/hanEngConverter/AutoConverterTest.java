package com.gncloud.fastcat.module.hanEngConverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swsong on 2017. 2. 16..
 */
public class AutoConverterTest {

    @Test
    public void testAutoConvert() {

        Converter convert = new Converter();

        assertEquals("버버리", convert.Converter("버qj리"));
        assertEquals("샤넬", convert.Converter("tispf"));
        assertEquals("chanel", convert.Converter("chanel"));
        assertEquals("asodkfj", convert.Converter("asodkfj"));
        assertEquals("버버리", convert.Converter("qjqjfl"));
        assertEquals("클릭", convert.Converter("zmfflr"));
        assertEquals("사용자", convert.Converter("사용자"));
        assertEquals("chanel", convert.Converter("초무디"));

        assertEquals("adidas", convert.Converter("adidas"));
        assertEquals("아디다스", convert.Converter("dkelektm"));
        assertEquals("asodkjfoaskdj", convert.Converter("asodkjfoaskdj"));
        assertEquals("nike",convert.Converter("nike"));
        assertEquals("nike", convert.Converter("ㅜㅑㅏㄷ"));
        assertEquals("nike", convert.Converter("ㅜㅑke"));

        assertEquals("asodkjf/asdf", convert.Converter("asodkjf/asdf"));
        assertEquals("한글", convert.Converter("한rmf"));

        assertEquals("한글", convert.Converter("하srmf"));
        assertEquals("asodkjf/asdf", convert.Converter("asodkjf/asdf"));

        assertEquals("한끌", convert.Converter("GKSRMF"));

        assertEquals("CHANEL",convert.Converter("CHANEL"));
        assertEquals("chanel",convert.Converter("초anel"));
        assertEquals("channel",convert.Converter("channel"));
        assertEquals("channel",convert.Converter("channel"));

        assertEquals("COMPUTER", convert.Converter("COMPUTER"));
        assertEquals("computer",convert.Converter("채ㅡㅔㅕㅅㄷㄱ"));
        assertEquals("computer",convert.Converter("comㅔㅕㅅㄷㄱ"));
        assertEquals("computer",convert.Converter("coㅡㅔㅕㅅㄷㄱ"));
        assertEquals("computer",convert.Converter("coㅡㅔㅕㅅer"));

    }
}
