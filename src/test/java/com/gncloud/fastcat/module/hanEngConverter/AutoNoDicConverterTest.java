package com.gncloud.fastcat.module.hanEngConverter;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by jhjeon on 2017. 5. 22..
 */
public class AutoNoDicConverterTest {

    @Test
    public void testAutoNoDicConvert() throws Exception{

        AutoNoDicConverter engConvert = new AutoNoDicConverter("ENG");
        AutoNoDicConverter hanConvert = new AutoNoDicConverter("HAN");

        assertEquals("angry bird", engConvert.convert("뭏교 ㅠㅑㄱㅇ"));
        assertEquals("batman dark knight", engConvert.convert("batㅡ무 ㅇㅁ가 ㅏㅜight"));

        assertEquals("그 많던 치즈는 누가 다 먹었을까", hanConvert.convert("rm aksgejs clwmsms snrk ek ajrdjTdmfRk"));
        assertEquals("고양이 육구", hanConvert.convert("rhdiddl dbr구"));
        assertEquals("까치집", hanConvert.convert("Rk치wlㅂ"));
    }
}
