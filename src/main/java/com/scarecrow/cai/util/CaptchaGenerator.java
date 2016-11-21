package com.scarecrow.cai.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.scarecrow.cai.metadata.CaptchaValueType;

public class CaptchaGenerator {
	// 48-57 0-9 //65-90 A-Z //97-122 a-z
	private static final char[] NUMBER = new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };

	private static final char[] UPPERCASE_LOWERCASE = new char[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77,
			78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108,
			109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };

	private static final char[] NUMBER_UPPERCASE_LOWERCASE = new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65,
			66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98,
			99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120,
			121, 122 };

	private static Random random = new Random();

	public static String getCatpha(int size, CaptchaValueType type, int number, int total) {
		char[] chars = getTpye(type);
		Integer[] offsets = new Integer[size];
		for (int i = 0; i < offsets.length; i++) {
			offsets[i] = random.nextInt(chars.length);
		}
		return generaterCode(offsets, chars);
	}

	public static List<String> getCapthas(int size, CaptchaValueType type, int number, int total) {
		List<String> result = new ArrayList<String>();
		char[] chars = getTpye(type);
		int maxStep = getMaxStep(type, size, total);
		Integer[] offsets = new Integer[size];
		for (int i = 0; i < offsets.length; i++) {
			offsets[i] = random.nextInt(chars.length);
		}
		for (int i = 0; i < number; i++) {
			updateOffsets(offsets, chars.length, maxStep);
			StringBuilder msb = new StringBuilder();
			for (int j = 0; j < size; j++) {
				msb.append(chars[offsets[j]]);
			}
			result.add(msb.toString());
		}
		return result;
	}

	public static List<String> getCapthas(int size, CaptchaValueType type, int number, int total, String beginCode) {
		List<String> result = new ArrayList<String>();
		char[] chars = getTpye(type);
		int maxStep = getMaxStep(type, size, total);
		Integer[] offsets = transferToOffset(beginCode, type);
		for (int i = 0; i < 100; i++) {
			updateOffsets(offsets, chars.length, maxStep);
			StringBuilder msb = new StringBuilder();
			for (int j = 0; j < offsets.length; j++) {
				msb.append(chars[offsets[j]]);
			}
			result.add(msb.toString());
		}
		return result;
	}

	private static void updateOffsets(Integer[] offsets, int length, int maxStep) {
		updateFromLastPosition(offsets, length, maxStep);
	}

	private static void updateFromLastPosition(Integer[] offsets, int length, int maxStep) {
		int offset = random.nextInt(maxStep) + 1;
		int divisor = (offset + offsets[offsets.length - 1]) / length;
		int remainder = (offset + offsets[offsets.length - 1]) % length;
		for (int i = 1, n = offsets.length; i <= n; i++) {
			offsets[offsets.length - i] = remainder;
			if (divisor == 0) {
				break;
			}
			if (i == n) {
				offsets[offsets.length - i] = (offsets[offsets.length - i] + divisor) % length;
			}
			remainder = (offsets[offsets.length - i - 1] + divisor) % length;
			divisor = (offsets[offsets.length - i - 1] + divisor) / length;
		}
	}

	private static String generaterCode(Integer[] offsets, char[] chars) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < offsets.length; j++) {
			sb.append(chars[offsets[j]]);
		}
		return sb.toString();
	}

	private static char[] getTpye(CaptchaValueType type) {
		char[] chars = null;
		switch (type) {
		case DIGIT:
			chars = NUMBER;
			break;
		case CHARACTER:
			chars = UPPERCASE_LOWERCASE;
			break;
		case CHARACTER_AND_DIGIT:
			chars = NUMBER_UPPERCASE_LOWERCASE;
			break;
		default:
			chars = NUMBER_UPPERCASE_LOWERCASE;
		}
		return chars;
	}

	private static Integer[] transferToOffset(String beginCode, CaptchaValueType type) {
		char[] code = beginCode.toCharArray();
		int length = code.length;
		Integer[] offset = new Integer[length];
		char[] chars = getTpye(type);
		for (int i = 0; i < length; i++) {
			offset[i] = calcIndexOfType(code[i], chars);
		}
		return offset;
	}

	private static int getMaxStep(CaptchaValueType type, int size, int total) {
		long maxValue = 1;
		long length = type.getLength();
		for (int i = 0; i < size; i++) {
			maxValue = maxValue * length;
		}
		int maxStep = (int) maxValue / total;
		// maxStep==0 exception
		return maxStep == 0 ? 1 : maxStep;
	}

	private static Integer calcIndexOfType(char code, char[] chars) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == code) {
				return i;
			}
		}
		// not find in char[] exception
		return -1;
	}
}
