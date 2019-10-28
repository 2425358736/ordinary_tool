package com.dia.ordinary.tool;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 开发公司：xx公司
 * 版权：xx公司
 * <p>
 * PinyinUtils
 *
 * @author 刘志强
 * @created Create Time: 2019/1/28
 */
public class PinyinUtils {

    /**
     * 汉字转拼音(全拼)
     */
    public static String HanziToQuanpin(String str){
        char[] charArray = str.toCharArray();
        StringBuffer pinyin = new StringBuffer();
        for(int i = 0; i<charArray.length; i++){
            if(Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")){
                String string = PinyinHelper.toHanyuPinyinStringArray(charArray[i])[0];
                pinyin.append(string.substring(0,string.length()-1));
            }else{
                pinyin.append(charArray[i]);
            }
        }
        return pinyin.toString();
    }

    /**
     * 汉字转拼音(首字母)
     */
    public static String HanziToShouZimu(String str){
        char[] charArray = str.toCharArray();
        StringBuffer pinyin = new StringBuffer();
        for(int i = 0; i<charArray.length; i++){
            if(Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")){
                String string = PinyinHelper.toHanyuPinyinStringArray(charArray[i])[0];
                pinyin.append(string.substring(0,1).toUpperCase());
            }else{
                pinyin.append(charArray[i]);
            }
        }
        return pinyin.toString();
    }
}
