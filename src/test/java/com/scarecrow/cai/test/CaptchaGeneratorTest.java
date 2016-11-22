package com.scarecrow.cai.test;

import java.util.List;

import com.scarecrow.cai.metadata.CaptchaValueType;
import com.scarecrow.cai.util.CaptchaGenerator;

public class CaptchaGeneratorTest {

	public static void main(String[] args) {
		CaptchaValueType type = CaptchaValueType.typeOf("3000");
		List<String> captchaList = CaptchaGenerator.getCapthas("9987", type, 3, 1000);
		for (String captcha : captchaList) {
			System.out.println(captcha);
		}
	}

}
