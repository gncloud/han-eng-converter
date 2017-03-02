package com.gncloud.fastcat.module.hanEngConverter;

public class AlphaToHan {

	private static String ENG_KEY = "rRseEfaqQtTdwWczxvgkoiOjpuPhynbml";
	private static String KOR_KEY = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎㅏㅐㅑㅒㅓㅔㅕㅖㅗㅛㅜㅠㅡㅣ";
	private static String CHO_DATA = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ";
	private static String JUNG_DATA = "ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅘㅙㅚㅛㅜㅝㅞㅟㅠㅡㅢㅣ";
	private static String JONG_DATA = "ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ";
	private static String UPKEY = "qwertop";

	public String upToLow(String keyword){
		String convertKey = "";
		for(int i = 0; i<keyword.length();i++){
			char compareKey = Character.toLowerCase(keyword.charAt(i));
			if(UPKEY.indexOf(compareKey) != -1){
				convertKey += keyword.charAt(i);
			}else{
				String temp = Character.toString(Character.toLowerCase(keyword.charAt(i)));
				convertKey += temp;
			}
		}
		return convertKey;
	}


	private String makeHangul(int nCho, int nJung, int nJong){
		String res = Character.toString((char) (0xAC00 + nCho * 21 * 28 + nJung * 28 + nJong + 1));
		return res;
	}

	public String alphaToHan(String key){
//		String keyword = upToLow(key);
		String res = "";
		HanToAlpha hta = new HanToAlpha();
		String keyword = hta.hanToAlpha(key);
		int nCho = -1, nJung = -1, nJong = -1;
		for(int i = 0; i<keyword.length(); i++){
			char ch = keyword.charAt(i);
			int p = ENG_KEY.indexOf(ch);
			if( p == -1){
				// 남아있는 한글이 있으면 처리
				if (nCho != -1) {
					if (nJung != -1)				// 초성+중성+(종성)
						res += makeHangul(nCho, nJung, nJong);
					else							// 초성만
						res += CHO_DATA.charAt(nCho);
				} else {
					if (nJung != -1)				// 중성만
						res += JUNG_DATA.charAt(nJung);
					else if (nJong != -1)			// 복자음
						res += JONG_DATA.charAt(nJong);
				}
				nCho = -1;
				nJung = -1;
				nJong = -1;
				res += ch;
			}else if(p < 19){
				if (nJung != -1) {
					if (nCho == -1) {					// 중성만 입력됨, 초성으로
						res += JUNG_DATA.charAt(nJung);
						nJung = -1;
						nCho = CHO_DATA.indexOf(KOR_KEY.charAt(p));
					} else {							// 종성이다
						if (nJong == -1) {				// 종성 입력 중
							nJong = JONG_DATA.indexOf(KOR_KEY.charAt(p));
							if (nJong == -1) {			// 종성이 아니라 초성이다
								res += makeHangul(nCho, nJung, nJong);
								nCho = CHO_DATA.indexOf(KOR_KEY.charAt(p));
								nJung = -1;
							}
						} else if (nJong == 0 && p == 9) {			// ㄳ
							nJong = 2;
						} else if (nJong == 3 && p == 12) {			// ㄵ
							nJong = 4;
						} else if (nJong == 3 && p == 18) {			// ㄶ
							nJong = 5;
						} else if (nJong == 7 && p == 0) {			// ㄺ
							nJong = 8;
						} else if (nJong == 7 && p == 6) {			// ㄻ
							nJong = 9;
						} else if (nJong == 7 && p == 7) {			// ㄼ
							nJong = 10;
						} else if (nJong == 7 && p == 9) {			// ㄽ
							nJong = 11;
						} else if (nJong == 7 && p == 16) {			// ㄾ
							nJong = 12;
						} else if (nJong == 7 && p == 17) {			// ㄿ
							nJong = 13;
						} else if (nJong == 7 && p == 18) {			// ㅀ
							nJong = 14;
						} else if (nJong == 16 && p == 9) {			// ㅄ
							nJong = 17;
						} else {						// 종성 입력 끝, 초성으로
							res += makeHangul(nCho, nJung, nJong);
							nCho = CHO_DATA.indexOf(KOR_KEY.charAt(p));
							nJung = -1;
							nJong = -1;
						}
					}
				} else {								// 초성 또는 (단/복)자음이다
					if (nCho == -1) {					// 초성 입력 시작
						if (nJong != -1) {				// 복자음 후 초성
							res += JONG_DATA.charAt(nJong);
							nJong = -1;
						}
						nCho = CHO_DATA.indexOf(KOR_KEY.charAt(p));
					} else if (nCho == 0 && p == 9) {			// ㄳ
						nCho = -1;
						nJong = 2;
					} else if (nCho == 2 && p == 12) {			// ㄵ
						nCho = -1;
						nJong = 4;
					} else if (nCho == 2 && p == 18) {			// ㄶ
						nCho = -1;
						nJong = 5;
					} else if (nCho == 5 && p == 0) {			// ㄺ
						nCho = -1;
						nJong = 8;
					} else if (nCho == 5 && p == 6) {			// ㄻ
						nCho = -1;
						nJong = 9;
					} else if (nCho == 5 && p == 7) {			// ㄼ
						nCho = -1;
						nJong = 10;
					} else if (nCho == 5 && p == 9) {			// ㄽ
						nCho = -1;
						nJong = 11;
					} else if (nCho == 5 && p == 16) {			// ㄾ
						nCho = -1;
						nJong = 12;
					} else if (nCho == 5 && p == 17) {			// ㄿ
						nCho = -1;
						nJong = 13;
					} else if (nCho == 5 && p == 18) {			// ㅀ
						nCho = -1;
						nJong = 14;
					} else if (nCho == 7 && p == 9) {			// ㅄ
						nCho = -1;
						nJong = 17;
					} else {							// 단자음을 연타
						res += CHO_DATA.charAt(nCho);
						nCho = CHO_DATA.indexOf(KOR_KEY.charAt(p));
					}
				}
			}else{
				// 모음
				if (nJong != -1) {						// (앞글자 종성), 초성+중성
					// 복자음 다시 분해
					int newCho;			// (임시용) 초성
					if (nJong == 2) {					// ㄱ, ㅅ
						nJong = 0;
						newCho = 9;
					} else if (nJong == 4) {			// ㄴ, ㅈ
						nJong = 3;
						newCho = 12;
					} else if (nJong == 5) {			// ㄴ, ㅎ
						nJong = 3;
						newCho = 18;
					} else if (nJong == 8) {			// ㄹ, ㄱ
						nJong = 7;
						newCho = 0;
					} else if (nJong == 9) {			// ㄹ, ㅁ
						nJong = 7;
						newCho = 6;
					} else if (nJong == 10) {			// ㄹ, ㅂ
						nJong = 7;
						newCho = 7;
					} else if (nJong == 11) {			// ㄹ, ㅅ
						nJong = 7;
						newCho = 9;
					} else if (nJong == 12) {			// ㄹ, ㅌ
						nJong = 7;
						newCho = 16;
					} else if (nJong == 13) {			// ㄹ, ㅍ
						nJong = 7;
						newCho = 17;
					} else if (nJong == 14) {			// ㄹ, ㅎ
						nJong = 7;
						newCho = 18;
					} else if (nJong == 17) {			// ㅂ, ㅅ
						nJong = 16;
						newCho = 9;
					} else {							// 복자음 아님
						newCho = CHO_DATA.indexOf(JONG_DATA.charAt(nJong));
						nJong = -1;
					}
					if (nCho != -1)			// 앞글자가 초성+중성+(종성)
						res += makeHangul(nCho, nJung, nJong);
					else                    // 복자음만 있음
						res += JONG_DATA.charAt(nJong);

					nCho = newCho;
					nJung = -1;
					nJong = -1;
				}
				if (nJung == -1) {						// 중성 입력 중
					nJung = JUNG_DATA.indexOf(KOR_KEY.charAt(p));
				} else if (nJung == 8 && p == 19) {            // ㅘ
					nJung = 9;
				} else if (nJung == 8 && p == 20) {            // ㅙ
					nJung = 10;
				} else if (nJung == 8 && p == 32) {            // ㅚ
					nJung = 11;
				} else if (nJung == 13 && p == 23) {           // ㅝ
					nJung = 14;
				} else if (nJung == 13 && p == 24) {           // ㅞ
					nJung = 15;
				} else if (nJung == 13 && p == 32) {           // ㅟ
					nJung = 16;
				} else if (nJung == 18 && p == 32) {           // ㅢ
					nJung = 19;
				} else {			// 조합 안되는 모음 입력
					if (nCho != -1) {			// 초성+중성 후 중성
						res += makeHangul(nCho, nJung, nJong);
						nCho = -1;
					} else						// 중성 후 중성
						res += JUNG_DATA.charAt(nJung);
					nJung = -1;
					res += KOR_KEY.charAt(p);
				}
			}
		}
		// 마지막 한글이 있으면 처리
		if (nCho != -1) {
			if (nJung != -1)			// 초성+중성+(종성)
				res += makeHangul(nCho, nJung, nJong);
			else                		// 초성만
				res += CHO_DATA.charAt(nCho);
		} else {
			if (nJung != -1)			// 중성만
				res += JUNG_DATA.charAt(nJung);
			else {						// 복자음
				if (nJong != -1)
					res += JONG_DATA.charAt(nJong);
			}
		}


		return res;
	}

}