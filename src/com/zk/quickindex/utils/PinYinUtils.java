package com.zk.quickindex.utils;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtils {

	/**
	 * ��ָ������ת����ƴ��
	 * @param string Ҫת���İ������ֵ��ַ���
	 * @return ƴ��
	 */
	public static String getPinyin(String string) {

		// ����
		//   ��   ��
		// ��GHJT4324��I&^*
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// û������
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE); // ���������дƴ��
		
		char[] charArray = string.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			
			if(Character.isWhitespace(c)){
				continue; // ������ǰѭ��
			}
			if(c >= -128 && c <= 127){
				sb.append(c);
			}else {
				try {
					// ������ת����ƴ��   �� -> HEI, �� -> DAN, SHAN
					String s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
					sb.append(s);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

}
