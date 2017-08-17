package cn.com.ddhj.solr.util;

public class KeywordUtil{
	
    private KeywordUtil(){
		
	}
	
	/**
	 * 对用户输入的字符串进行处理,
	 * 如果字符串含有空格，将空格两端的字符串用AND进行连接
	 * @param keyword
	 * @return
	 */
	public static String getAndKeyword(String keyword) {
		String[] keywords = keyword.split(" +");
		if (keywords.length > 1) {
			StringBuffer sb = new StringBuffer();
			sb.append(keywords[0]);
			for (int i = 1; i < keywords.length; i++) {
				sb.append(" AND ").append(keywords[i]);				
			}
			keyword = sb.toString();
             
		}
		return keyword;
		
	}
	/**
	 * 对用户输入的字符串进行处理
	 * 判断是否为空，是否长度为1，是否还有特殊字符，是否含有空格
	 * @param keyword
	 * @return
	 */
	public static String replaceInvalidWord(String keyword) {
		String newKeyword = null;	
		// 当输入的字符串为空或者都是空格时 ，直接返回空
		if (StringUtil.isEmpty(keyword)) {
			//输入的字符串为空
			return null;
		} else {
			//字符串长度为1
			if (!StringUtil.overLength(keyword.trim())) {
				return null;
			} else {
				String[] moreKeyword = keyword.split(" +");
				if (moreKeyword.length == 1) {
					newKeyword = getNewKeyword(moreKeyword[0]);				
				} else {
					StringBuffer sb = new StringBuffer();
					for (String key : moreKeyword) {
						key = getNewKeyword(key);
						if (!StringUtil.isEmpty(key)) {
							sb.append(key);
						}
					}
					newKeyword = sb.toString();
					
				}
			}
		}
		return newKeyword;
	}
    /**
     * 将字符串中的非法字符去掉
     * @param keyword
     * @return
     */
	public static String getNewKeyword(String keyword) {
		
		keyword = StringUtil.removeAllPunct(keyword.toLowerCase());
//        System.out.println("用户输入的查询字符串");
		if (StringUtil.isEmpty(keyword) || StringUtil.isEmpty(keyword.trim())) {

			return null;
		} else {		
			keyword = keyword.replaceAll("\\s+", " ");
		}
		return keyword;
		
	}
	
	/**
	 * 获取用户输入的查询词
	 * 
	 * @param mustKeyword
	 *            搜索结果中必须要包含的词
	 * @param orKeyword
	 *            搜索结果中可能出现的词
	 * @param maxLikelihoodKeyword
	 *            搜索结果中必须包含跟输入完全一样的词
	 * @param notKeyword
	 *            搜索结果中不能出现的词（当上面三个框中都没有输入内容时，这个框中输入的词就是必须要出现的词）
	 * @return 一个新的字符串
	 */
	public static String getNewKeyword(String mustKeyword, String orKeyword, String maxLikelihoodKeyword,String notKeyword) {
		StringBuffer sb = new StringBuffer();
		/**
		 * 如果前三个框为空或者是一些不合法字符，直接返回给用户需要输入合法的字符串
		 */
		if ((StringUtil.isEmpty(mustKeyword) || StringUtil.isEmpty(StringUtil.replaceStr(mustKeyword).trim()))
				&& (StringUtil.isEmpty(orKeyword) || StringUtil.isEmpty(StringUtil.replaceStr(orKeyword).trim()))
				&& (StringUtil.isEmpty(maxLikelihoodKeyword) || StringUtil.isEmpty(StringUtil.replaceStr(
						maxLikelihoodKeyword).trim()))) {
			return null;
			// 当前面的三个输入框中有不为null和全为空格的情况，将几个框中的查询词拼成一个字符串，作为新的查询词
		} else {
			if (!StringUtil.isEmpty(mustKeyword)) {
				mustKeyword = StringUtil.replaceStr(mustKeyword).trim();
				if (!StringUtil.isEmpty(mustKeyword)) {
					sb.append("+(").append(mustKeyword).append(")");
				}
			}
			if (!StringUtil.isEmpty(orKeyword)) {
				orKeyword = StringUtil.replaceStr(orKeyword).trim();
				if (!StringUtil.isEmpty(orKeyword)) {
					sb.append(" (").append(orKeyword).append(")");
				}
			}
			if (!StringUtil.isEmpty(maxLikelihoodKeyword)) {
				maxLikelihoodKeyword = StringUtil.replaceStr(maxLikelihoodKeyword).trim();
				if (!StringUtil.isEmpty(maxLikelihoodKeyword)) {
					sb.append(" +\"").append(maxLikelihoodKeyword).append("\"");
				}
			}
			if (!StringUtil.isEmpty(notKeyword)) {
				notKeyword = StringUtil.replaceStr(notKeyword).trim();
				if (!StringUtil.isEmpty(notKeyword)) {
					sb.append(" -(").append(notKeyword).append(")");
				}
			}
		}

		String keyword = sb.toString();
		return keyword;
	}


}
