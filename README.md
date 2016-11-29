# Captcha
### 批量生成不重复随机验证码算法

通过预生成百万随机验证码，并生成相应验证码图片，构造验证码池来提高验证码获取及验证性能，不需每次生成验证码及图片。

1. 可指定验证码长度及组成，纯数字或数字字母。
2. 可随机生成也可以分批生成，分批生产时需传入上一批最后一个验证码，以确保后续生成的不会也之前的重复。
3. 通过模拟62进制循环进位实现不重复验证码，验证码之间每次增量为1-n之间的一个随机数(n表示最大可能增量)。

### CaptchaGenerator示例
    List<String> captchaList = CaptchaGenerator.getCapthas(4, CaptchaValueType.CHARACTER_AND_DIGIT, 100000, 100000);
    一次性生成10万条长度为4位不重复验证码，包含数字及大小写字母