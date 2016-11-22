package com.scarecrow.cai.metadata;

public enum CaptchaValueType {
	CHARACTER_AND_DIGIT("1000", "character and digit"), CHARACTER("2000", "character only"), DIGIT("3000",
			"digit only");

	private String code;;
	private String name;

	private CaptchaValueType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public boolean isCharacterAndDigit(String code) {
		return code == CHARACTER_AND_DIGIT.getCode();
	}

	public boolean isCharacter(String code) {
		return code == CHARACTER.getCode();
	}

	public boolean isDigit(String code) {
		return code == DIGIT.getCode();
	}

	public static CaptchaValueType typeOf(String code) {
		switch (code) {
		case "1000":
			return CHARACTER_AND_DIGIT;
		case "2000":
			return CHARACTER;
		case "3000":
			return DIGIT;
		default:
			return null;
		}
	}
}
