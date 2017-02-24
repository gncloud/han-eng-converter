package com.gncloud.fastcat.module.hanEngConverter;

import org.junit.Test;
import static org.junit.Assert.*;


public class AlphaToHanTest {

	@Test
	 public void testAlphaToHan() {
		AlphaToHan ath = new AlphaToHan();

		// 알파벳 한글 변환 테스트
		assertEquals("한글",ath.alphaToHan("하srmf"));
		assertEquals("뷁뷁뷁뷁", ath.alphaToHan("qnpfrqnpfrqnpfrqnpfr"));
		assertEquals("무궁화꽃이피었습니다",ath.alphaToHan("anrndghkRhcdlvldjTtmqslek"));
		assertEquals("가나다라마바사", ath.alphaToHan("rkskekfkakqktk"));
		assertEquals("안녕 하세요", ath.alphaToHan("dkssud gktpdy"));
		assertEquals("까르뽀나라", ath.alphaToHan("RkfmQhskfk"));
		assertEquals("ㄱ ㅏ ㅇ 나 ㅁ", ath.alphaToHan("ㄱ ㅏ ㅇ 나 ㅁ"));
		assertEquals("", ath.alphaToHan(""));
		assertEquals("ㄱㄴㅁㄴㅇ/ㄹ다라", ath.alphaToHan("rsasd/fekfk"));
		assertEquals("ㄱㄴㄷㄹ",ath.alphaToHan("rsef"));

	}


}
