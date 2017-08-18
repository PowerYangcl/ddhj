package cn.com.ddhj.solr.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * solr数据校验转换类 <br/>
 * 描述 : solr数据校验类 <br/>
 * 创建时间 : 2015-08-15 09:25:31 <br/>
 * 文件版本 : V1.0 <br/>
 * 修改历史 : V1.0
 */
public class StringUtil {
	
	 /**
     * 一个字符串是不是全为数字
     * @param value
     * @return
     */
    public static boolean IsNumeric(String value) {
    	value = value.trim();
    	Pattern p = Pattern.compile("^[0-9]+$");
		Matcher m = p.matcher(value);
		return m.matches();
    } 
    
    /**
     * 特殊字符过滤
     * @param value
     * @return
     */
    public static boolean IsSpecial(String value){
    	Pattern p = Pattern.compile("\\w+|[\u4e00-\u9fa5]+");
		Matcher m = p.matcher(value);
		if(m.find()){
			return  true;
		}else{
			return false;
		}
    }
    
    /**
     * 一个字符串必须字母开头，而且是用字母和数字组合
     * @param value
     * @return
     */
    public static boolean IsString(String value) {
    	value = value.trim();
    	Pattern p = Pattern.compile("^[a-zA-Z]\\w{0,300}$");
		Matcher m = p.matcher(value);
		return m.matches();
    } 
    
    /**
     * 判断一个字符串是不是全是字母
     * @param value
     * @return
     */
    public static boolean IsLetter(String value) {
	  value = value.trim();
	  Pattern p = Pattern.compile("^[A-Za-z]+$");
	  Matcher m = p.matcher(value);
	  return m.matches();
    }
    
    
    /***
	 * 日期格式化
	 * @param year
	 * @return
	 */
	public static Date getYear(String year){
		SimpleDateFormat sdfDate =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date dateRiQi= null;
		Date date = null;
		String  value = "";
		try {
			date= sdfDate.parse(year);
			value = sdf.format(date);
			dateRiQi = sdf.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateRiQi;
	}
	
	
	
	/**
	 * 对字符串进行替换
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceStr(String str) {
		if (!StringUtil.isEmpty(str)) {
			str = str.replaceAll("[^\\u4e00-\\u9fa5 | 0-9| a-zA-Z]+", " ");
		}
		return str;
	}

	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.isEmpty())
			return true;

		return false;
	}
	
	public static String wrapXmlContent(String content) {
		StringBuffer appender = new StringBuffer("");

		if ((content != null) && (!content.trim().isEmpty())) {
			appender = new StringBuffer(content.length());

			for (int i = 0; i < content.length(); i++) {
				char ch = content.charAt(i);
				if ((ch == '\t') || (ch == '\n') || (ch == '\r') || ((ch >= ' ') && (ch <= 55295))
						|| ((ch >= 57344) && (ch <= 65533)) || ((ch >= 65536) && (ch <= 1114111))) {
					appender.append(ch);
				}
			}
		}
		String result = appender.toString();
		return result;
	}
	/**
	 * 合并两个字符串
	 * @return
	 */
    public static String[] connat(String[] str1, String[] str2){
    	if(str1.length == 0 || str2.length ==0){
    		return null;
    	}
    	List<String> list = new ArrayList<String>();
    	String [] str3 = new String[str1.length + str2.length];
    	for(String s : str1){
    		list.add(s);
    	}
    	for(String s : str2){
    		list.add(s);
    	}
    	
		return list.toArray(new String[str3.length]);   	
    }
    
	private static final String NUM_REG = "(\\+|\\-)?\\s*\\d+(\\.\\d+)?";

	/**
	 * 判断是否数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isEmpty(str))
			return false;

		if (str.trim().matches(NUM_REG))
			return true;

		return false;
	}

	/**
	 * 判读是否包含数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean containNumber(String str) {
		return contain(str, "\\d");
	}

	/**
	 * 判断是否包含a-zA-Z_0-9
	 * 
	 * @param str
	 * @return
	 */
	public static boolean containWord(String str) {
		return contain(str, "\\w");
	}
	/**
	 * 判断字符串的长度
	 * @param str
	 * @return
	 */
    public static boolean overLength(String str){
    	if(str.length() == 0){
    		return false;
    	}
		return true;
    	
    }
	public static boolean contain(String str, String regex) {
		if (isEmpty(str) || isEmpty(regex))
			return false;

		if (str.trim().matches(regex))
			return true;

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find())
			return true;

		return false;
	}

	/**
	 * 替换所有的（不区分大小写）
	 * 
	 * @param input
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public static String replaceAll(String input, String regex,
			String replacement) {
		return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(input)
				.replaceAll(replacement);
	}

	/**
	 * 移除所有的空格
	 * 
	 * @param text
	 * @return
	 */
	public static String removeAllSpace(String text) {
		if (isEmpty(text)) {
			return text;
		}

		text = text.replaceAll("[ ]+", "");
		return text;
	}

	private static final String PUNCT_REG = "[^a-zA-Z0-9\\u4e00-\\u9fa5]";

	/**
	 * 移除字符串中的所有的中英文标点符号
	 * 
	 * @param str
	 * @return
	 */
	public static String removeAllPunct(String str) {
		if (isEmpty(str))
			return str;

		str = str.replaceAll(PUNCT_REG, "");
		return str;
	}

//	public static List<String> removeAllPunct(List<String> list) {
//		if (CollectionUtil.isEmpty(list))
//			return list;
//
//		List<String> result = new ArrayList<String>();
//		for (String str : list) {
//			str = removeAllPunct(str);
//			result.add(str);
//		}
//
//		return result;
//	}

	/**
	 * 计算str中包含多少个子字符串sub
	 * @param str
	 * @param sub
	 * @return
	 */
	public static int countMatches(String str, String sub) {
		if (isEmpty(str) || isEmpty(sub)) {
			return 0;
		}

		int count = 0;
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != -1) {
			count++;
			idx += sub.length();
		}
		return count;
	}
	
	/**
	 * 获得源字符串的一个子字符串
	 * @param str：源字符串
	 * @param beginIndex：开始索引（包括）
	 * @param endIndex：结束索引（不包括）
	 * @return
	 */
	public static String substring(String str, int beginIndex, int endIndex) {
		if(isEmpty(str)) return str;
		
		int length = str.length();
		
		if(beginIndex >= length || endIndex <= 0 || beginIndex >= endIndex) {
			return null;
		}
		
		if(beginIndex < 0) beginIndex = 0;
		if(endIndex > length) endIndex = length;
		
		return str.substring(beginIndex, endIndex);
		
	}

	/**
	 * 计算str中包含子字符串sub所在位置的前一个字符或者后一个字符和sub所组成的新字符串
	 * @param str
	 * @param sub
	 * @return
	 */
	public static Set<String> substring(String str, String sub) {
		if (isEmpty(str) || isEmpty(sub)) {
			return null;
		}
		
		Set<String> result = new HashSet<String>();
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != -1) {
			String temp = substring(str, idx-1, idx+sub.length());
			if(! isEmpty(temp)) {
				temp = removeAllPunct(temp);
				if(! sub.equalsIgnoreCase(temp) && ! containWord(temp)) {
					result.add(temp);
				}
				
			}
			
			temp = substring(str, idx, idx+sub.length()+1);
			if(! isEmpty(temp)) {
				temp = removeAllPunct(temp);
				if(! sub.equalsIgnoreCase(temp) && ! containWord(temp)) {
					result.add(temp);
				}
			}
			
			idx += sub.length();
		}
		
		return result;
	}
	
	 /**
     * 
     * @param str
     * @return
     */
    public static String StringFilter(String str) {
    	if(StringUtils.isNotBlank(str)) {
			str = str.replaceAll("[^\\u4e00-\\u9fa5\\w\\s]", " ");
			str = str.replaceAll("\\s+", " ");
			str = str.replace("_", " ");
    	}
		return str;
	}
    
}
