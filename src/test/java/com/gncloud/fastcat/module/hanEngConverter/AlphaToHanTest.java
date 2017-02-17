package com.gncloud.fastcat.module.hanEngConverter;

import org.junit.Test;
import static org.junit.Assert.*;


public class AlphaToHanTest {

	@Test
	public void testAlphaToHan() {
		AlphaToHan ath = new AlphaToHan();
		HanToAlpha hta = new HanToAlpha();

		AlphaToHanAll atha = new AlphaToHanAll();

		// 알파벳 한글 변환 테스트
		assertEquals("뷁뷁뷁뷁", ath.alphaToHan("qnpfrqnpfrqnpfrqnpfr"));
		assertEquals("무궁화꽃이피었습니다",ath.alphaToHan("anrndghkRhcdlvldjTtmqslek"));
		assertEquals("가나다라마바사", ath.alphaToHan("rkskekfkakqktk"));
		assertEquals("안녕 하세요", ath.alphaToHan("dkssud gktpdy"));
		assertEquals("까르뽀나라", ath.alphaToHan("RkfmQhskfk"));
//		assertEquals("강남", ath.alphaToHan("ㄱ ㅏ ㅇ 나 ㅁ"));
		assertEquals("", ath.alphaToHan(""));
//		assertEquals("ㄱㄴㅁㄴㅇ/ㄹ다라", ath.alphaToHan("rsasd/fekfk"));
		assertEquals("ㄱㄴㄷㄹ",atha.alphaToHanAll("rsef"));

		//한글 알파벳 변환 테스트
		assertEquals("gksrmf",hta.hanToAlpha("한글"));
		assertEquals("gks rmf",hta.hanToAlpha("한 글"));
		assertEquals("rsekfk",hta.hanToAlpha("ㄱㄴ다라"));
		assertEquals("rs ekfk",hta.hanToAlpha("ㄱㄴ 다라"));
//		assertEquals("rsasd/fekfk",hta.hanToAlpha("ㄱㄴasd/f다라"));
//		assertEquals("rfxefgkgkssef",hta.hanToAlpha("ㄱㄾㄷㄹ하하ㄴㄴㄷㄹ"));
	}
}
