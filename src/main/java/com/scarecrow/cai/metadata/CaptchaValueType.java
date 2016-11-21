package com.scarecrow.cai.metadata;

public enum CaptchaValueType {
	CHARACTER_AND_DIGIT(0, 62), CHARACTER(1, 52), DIGIT(2, 10);

	private int type = 0;
	private int length = 0;

	private CaptchaValueType(int type, int length) {
		this.type = type;
		this.length = length;
	}

	public int getType() {
		return type;
	}

	public int getLength() {
		return length;
	}

	public boolean isCharacterAndDigit(int type) {
		return type == CHARACTER_AND_DIGIT.getType();
	}

	public boolean isCharacter(int type) {
		return type == CHARACTER.getType();
	}

	public boolean isDigit(int type) {
		return type == DIGIT.getType();
	}

	public static CaptchaValueType valueOf(int type) {
		switch (type) {
		case 0:
			return CHARACTER_AND_DIGIT;
		case 1:
			return CHARACTER;
		case 2:
			return DIGIT;
		default:
			return CHARACTER_AND_DIGIT;
		}
	}
}
