package com.gncloud.fastcat.module.hanEngConverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HanToAlphaTest {

	@Test
	 public void test() {
		HanToAlpha hta = new HanToAlpha();
		assertEquals("buqjfl Rhcwkzpt",hta.hanToAlpha("bu버리 꽃자켓"));
		assertEquals("apple",hta.hanToAlpha("메ㅔㅣㄷ"));
		assertEquals("gksrmf",hta.hanToAlpha("한글"));
		assertEquals("gks rmf",hta.hanToAlpha("한 글"));
		assertEquals("rsekfk",hta.hanToAlpha("ㄱㄴ다라"));
		assertEquals("rs ekfk",hta.hanToAlpha("ㄱㄴ 다라"));
//		assertEquals("rsasd/fekfk",hta.hanToAlpha("ㄱㄴasd/f다라"));
//		assertEquals("rfxefgkgkssef",hta.hanToAlpha("ㄱㄾㄷㄹ하하ㄴㄴㄷㄹ"));
	}


}
