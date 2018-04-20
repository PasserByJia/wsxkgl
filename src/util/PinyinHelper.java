package util;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 致谢：From http://ask.csdn.net/questions/27636
 */
public class PinyinHelper {

    /**
     * 得到 全拼
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = net.sourceforge.pinyin4j.PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 得到中文拼音首字母
     * @param cnString 汉字字串
     * @return
     */
    public static String getPinYinInitialChar(String cnString) {
        String initialChars = "";
        for (int j = 0; j < cnString.length(); j++) {
            char cnChar = cnString.charAt(j);
            String[] pinyinArray = net.sourceforge.pinyin4j.PinyinHelper
                    .toHanyuPinyinStringArray(cnChar);
            if (pinyinArray != null) {
                initialChars += pinyinArray[0].charAt(0);
            } else {
                initialChars += cnChar;
            }
        }
        return initialChars;
    }

    /**
     * 将字符串转移为ASCII码
     *
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    public static void main(String[] args) {
        String cnStr = "张一鸣";
        System.out.println(getPingYin(cnStr));
        System.out.println(getPinYinInitialChar(cnStr));
        System.out.println(getCnASCII(cnStr));
    }
}