package com.gncloud.fastcat.module.hanEngConverter;

public class HanToAlpha {
	private static final String[] KORCHOKEYTOENG = { "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", "T", "d", "w",
			"W", "c", "z", "x", "v", "g" };
	private static final String[] KORJUNKEYTOENG = { "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk", "ho", "hl", "y",
			"n", "nj", "np", "nl", "b", "m", "ml", "l" };
	private static final String[] KORJONKEYTOENG = { "", "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa", "fq",
			"ft", "fx", "fv", "fg", "a", "q", "qt", "t", "T", "d", "w", "c", "z", "x", "v", "g" };

	public String hanToAlpha(String hanString){
		String convertString = "";
		for(int i = 0; i<hanString.length();i++){
			char hanChar = hanString.charAt(i);
			
			if((int) hanChar >= 0xAC00){
				char alphaChar = (char) (hanChar - 0xAC00);
			
				int cho = (((alphaChar - (alphaChar % 28)) / 28) / 21);
				int jun = (((alphaChar - (alphaChar % 28)) / 28) % 21);
				int jon = (alphaChar % 28);
				
				convertString += KORCHOKEYTOENG[cho];
				convertString += KORJUNKEYTOENG[jun];
				if(!KORJONKEYTOENG[jon].equalsIgnoreCase(""))
					convertString += KORJONKEYTOENG[jon];
			}			
		}
		return convertString;
	}
}
