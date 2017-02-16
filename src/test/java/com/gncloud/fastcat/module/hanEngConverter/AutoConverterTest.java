package com.gncloud.fastcat.module.hanEngConverter;

import org.junit.Test;

/**
 * Created by swsong on 2017. 2. 16..
 */
public class AutoConverterTest {

    @Test
    public void testAutoConvert() {

        //assertEquals("버버리", converter.test("버버fl"));
        //assertEquals("버버리", converter.test("qjqj리"));
        //assertEquals("버버리st", converter.test("버버flst"));린ㅅ 버버린t, 버버리st => 우리는 못함.
        //assertEquals("mouse", converter.test("ㅡㅐㅕse"));
        //assertEquals("남성가방", converter.test("skatjdrk방"));
        //assertEquals("남성가방", converter.test("남성가qkd"));



        /*

            쉽게 생각.
            0. 한글조합(초+중+종)이 안된다면? 그부분만 영문변환시도.
            1. 영문이 사전에 없다면, 좌우에 영문을 붙여서 다시시도. 그 영문으로 한글변환시도.
            2. 한글결과가 조합이 괜찮다면 그대로 사용.
            3. 조합이 이상하면 버리고 기존 영문사용.

            0. 영문이 사전에 없다면? 한글변환시도.
            1. 한글결과가 조합이 괜찮다면 그대로 사용.
            2. 조합이 이상하면 버리고 기존 영문사용.


        *
        * */
    }
}
