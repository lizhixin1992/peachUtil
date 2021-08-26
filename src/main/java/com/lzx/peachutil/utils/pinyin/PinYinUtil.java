package com.lzx.peachutil.utils.pinyin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.nlpcn.commons.lang.pinyin.Pinyin;

import java.util.List;

/**
 * 拼音转换工具类
 *
 * @author lizhixin
 * @date 2021/8/12 13:39
 */
@Slf4j
public class PinYinUtil {

    /**
     * nlp 获取拼音首字母
     *
     * @author lizhixin
     * @date 2021/8/12 13:39
     */
    public static String getPinYinFirstLetterUseNLP(boolean keepNullPinYin, String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder firstLetter = new StringBuilder();
        List<String> pinyinList = Pinyin.firstChar(str);
        for (int i = 0; i < pinyinList.size(); i++) {
            String firstChar = pinyinList.get(i);
            if (firstChar == null && keepNullPinYin) {
                firstLetter.append(str.charAt(i));
            }
            if (firstChar != null) {
                firstLetter.append(firstChar);
            }
        }
        return firstLetter.toString();
    }

    /**
     * nlp 获取全拼音
     *
     * @author lizhixin
     * @date 2021/8/12 13:40
     */
    public static String getPinYinFullLetterUseNLP(boolean keepNullPinYin, String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder fullLetter = new StringBuilder();
        List<String> pinyinList = Pinyin.pinyin(str);
        for (int i = 0; i < pinyinList.size(); i++) {
            String fullChar = pinyinList.get(i);
            if (fullChar == null && keepNullPinYin) {
                fullLetter.append(str.charAt(i));
            }
            if (fullChar != null) {
                fullLetter.append(fullChar);
            }
        }
        return fullLetter.toString();
    }

    public static void main(String[] args) {
//        System.out.println(getPinYinFirstLetterUseNLP(true,"朝阳 长阳 长大"));
//        System.out.println(getPinYinFullLetterUseNLP(true,"朝阳 长阳 长大"));

//        String a = "朝阳 长阳 长大_wewedfdsdsds ~!@#$%^&*()_+{}|:\"<>?/.,';\\][/。，‘；；’“：？》《+——）（*&……%¥#@！～。。》】【「」、｜1212123323232";
//        a = a.replaceAll("[`~!@#$%^&*()+=|{}':;'\",\\[\\].<>/?~！@#￥%……&*（）——+|{}【】》《～「」｜‘；：”“’。，、？\\s¥\\\\]", "");
//        System.out.println(a);
//        System.out.println(getPinYinFullLetterUseNLP(false, a));
    }
}