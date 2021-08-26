package com.lzx.peachutil.utils.pinyin;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * pinyin4j
 *
 * @author lizhixin
 * @date 2021/8/12 15:33
 */
@Slf4j
public abstract class PinYin4jUtil {

    private static final int C_LENGTH = 255;

    /**
     * 将汉字转换为全拼
     *
     * @author lizhixin
     * @date 2021/8/12 15:33
     */
    public static String getPinYin(String inputString) {

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        char[] input = inputString.trim().toCharArray();
        StringBuilder output = new StringBuilder("");

        try {
            for (int i = 0; i < input.length; i++) {
                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output.append(temp[0]);
                } else {
                    output.append(input[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("BadHanYuPinyinOutputFormatCombination", e);
        }
        return output.toString();
    }


    /**
     * 提取每个汉字的首字母
     *
     * @author lizhixin
     * @date 2021/8/12 15:33
     */
    public static String getPinYinHeadChar(String str) {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                result.append(pinyinArray[0].charAt(0));
            } else {
                result.append(word);
            }
        }
        return result.toString();
    }


    public static void main(String[] args) {
//        String str = "TVアニメ『宝石の国』30秒SPOT第2弾";
//
//        String a = "朝阳 朝霞 长阳 长大_wewedfdsdsds ~!@#$%^&*()_+{}|:\"<>?/.,';\\][/。，‘；；’“：？》《+——）（*&……%¥#@！～。。》】【「」、｜1212123323232";
//        a = a.replaceAll("[`~!@#$%^&*()+=|{}':;'\",\\[\\].<>/?~！@#￥%……&*（）——+|{}【】》《～「」｜‘；：”“’。，、？\\s¥\\\\]", "");
//        System.out.println(a);
//        System.out.println(getPinYin(a));
    }
}
