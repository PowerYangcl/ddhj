package cn.com.ddhj.solr.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.solr.data.SolrData;

/**
 * SolrUtilData
 * 索引库操作类<br/>
 * 描述 : 单例模式初始化  索引的CRUD操作<br/>
 * 创建时间 : 2015-09-01 17:44:31 <br/>
 * 文件版本 : V1.0 <br/>
 * 修改历史 : V1.0
 */
public class SolrClientAgent {
	/**yes为集群  no代表为单机**/
	 public static final String CLUSTER = PropHelper.getValue("clusterSolr"); 
	 /**zookeeper的连接地址**/
	 public static final String ZKHOST = PropHelper.getValue("zkHostSolr");
	 /**单机的请求url地址**/
	 public static final String HTTP_URL = PropHelper.getValue("httpurlSolr");
	 /**集群初始化的请求时间**/
	 public static final int ZKCLIENTTIMEOUT = Integer.parseInt(PropHelper.getValue("clientTimeSolr")); 
	 /**集群的连接时间**/
	 public static final int ZKCONNECTTIMEOUT = Integer.parseInt(PropHelper.getValue("connectTimeSolr"));  
	 private static Map<String,SolrClient> map = new ConcurrentHashMap<String,SolrClient>();
	 /**请求查询类**/
	 private static SolrClient solrClient;
	 
	/***
	 * 2016-02-29修改单利模式 
	 * 该方法为CloudSolrClient和HttpSolrClient的单利创建方法，首先会去map集合里面没有该collection的
	 * value,如果map里面Wienull会创建该collection的单利
	 * @param collection 系统的索引库
	 * @return
	 */
	public SolrClient getSolrSingleton(final String collection){
		
		if(CLUSTER.equals("yes")){
			if(map.get(collection+"_JQ")==null){
				solrClient = getColudClient(ZKHOST,collection);
				map.put(collection+"_JQ", solrClient);
			}else{
				solrClient = map.get(collection+"_JQ");
			}
		}else{
			if(map.get(collection)==null){
				solrClient = getHttpClient(HTTP_URL,collection);
				map.put(collection, solrClient);
			}else{
				solrClient = map.get(collection);
			}
		}
		return solrClient; 
	}
	
	
	
	
	/**
	 * 集群模式单利
	 * @param solrClient   单利初始化对象
	 * @param zkHost       zk地址
	 * @param collection   搜索区域
	 * @return
	 */
	private  synchronized CloudSolrClient getColudClient(String zkHost,String collection){
			CloudSolrClient solrClient = new CloudSolrClient(zkHost);
			solrClient.setDefaultCollection(collection);
			solrClient.setZkClientTimeout(ZKCLIENTTIMEOUT);
			solrClient.setZkConnectTimeout(ZKCONNECTTIMEOUT);
			solrClient.connect();
		return solrClient;
	}
	
	 /**
	  * 单机模式单利
	  * @param solrClient 单利初始化对象
	  * @param collection 搜索区域
	  * @return
	  */
	private  synchronized HttpSolrClient getHttpClient(String HttpUrl,String collection) {
			HttpSolrClient solrClient = new HttpSolrClient(HttpUrl+collection);
			solrClient.setAllowCompression(true);
			solrClient.setSoTimeout(60000); 
			solrClient.setConnectionTimeout(100); 
			solrClient.setDefaultMaxConnectionsPerHost(100); 
			solrClient.setMaxTotalConnections(100); 
			solrClient.setFollowRedirects(false); 
			solrClient.setAllowCompression(true); 
        return solrClient;    
	 } 
	
	
	/**
	 * 创建索引库的字段
	 * @param data  索引库实体对象
	 * @return
	 */
	public  SolrInputDocument entityParese(SolrData data){
		SolrInputDocument doc = new SolrInputDocument();
		    doc.addField("k1", data.getK1());
	        doc.addField("s1", data.getS1());
	        doc.addField("s2", data.getS2());
	        doc.addField("d1",data.getD1());
		return doc;
	}
	
	/**
	 * 添加一批商品索引
	 * httpClientOne.add(list);
     * httpClientOne.optimize();
     * 有点像硬盘上整理磁盘碎片的操作。为了提高搜索速度，它会将索引重组在一起，然后移除需要被删除或是更新的文档，
     * 请注意，solr是没有update的这种操作的，只有增加与删除。
     * solr在优化时，将需要删除或是被替换的索引标记为deleted，然后再创建新的文档替换掉需要被替换的。
     * optimize就是执行此操作。所以在优化的时候，你的索引会增大，然后再减小。
     * optimize操作会创建一个全新的的索引结构，所以，你需要预备出2倍于你commit时索引大小的空间。
	 * httpClientOne.commit();
	 * 此处只是提交到索引中，不会出现在搜索结果中，而且性能较差
	 * 如果想立马搜索的到，使用setAction(UpdateRequest.ACTION.COMMIT, false, false ); 提交
	 */
	public void addSolrIndexList(List<SolrData> data,String sellerCode){
		List<SolrInputDocument> list = new ArrayList<SolrInputDocument>();
		for(SolrData da : data){
			SolrInputDocument document = entityParese(da);
			list.add(document);
		}
		
		try {
			SolrClient solrSingleton = getSolrSingleton(sellerCode);
			UpdateRequest req = new UpdateRequest(); 
			req.setAction(UpdateRequest.ACTION.COMMIT, false, false ); 
			req.add(list); 
			req.process(solrSingleton);
			list.clear();
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 添加一条商品索引
	 */
	public void addSolrIndexOne(SolrData data,String sellerCode){
		 SolrInputDocument doc = entityParese(data);
		 try {
			 
		    SolrClient solrSingleton = getSolrSingleton(sellerCode);
			UpdateRequest req = new UpdateRequest(); 
			req.setAction(UpdateRequest.ACTION.COMMIT, false, false ); 
			req.add(doc); 
			req.process(solrSingleton);
			doc.clear();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一条商品索引
	 */
	public void delSolrIndexOne(String productCode,String sellerCode){
		 try {
			 
		    SolrClient solrSingleton = getSolrSingleton(sellerCode);
			UpdateRequest req = new UpdateRequest(); 
			req.setAction(UpdateRequest.ACTION.COMMIT, false, false ); 
			req.deleteById(productCode);
			req.process(solrSingleton);
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除全部商品索引
	 */
	public void delSolrIndexAll(String sellerCode){
		 try {
			 
			SolrClient solrSingleton = getSolrSingleton(sellerCode);
			UpdateRequest req = new UpdateRequest(); 
			req.setAction(UpdateRequest.ACTION.COMMIT, false, false ); 
			req.deleteByQuery("*:*");
			req.process(solrSingleton);
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 联想词 显示商品名称
     * @param params 查询参数
     * @param sellerCode  索引库分片
     * @return
     */
    public  List<String> getSuggestProductName(SolrQuery params,String sellerCode){
    	List<String> sugValue = new ArrayList<String>();
    	if(params == null || sellerCode==null){
    		return sugValue;
    	}
    	QueryResponse responseSuggest = null;
		 try {
			 
			    SolrClient solrSingleton = getSolrSingleton(sellerCode);
			    responseSuggest=solrSingleton.query(params);
			    
				if(responseSuggest != null ){ 
			    	 TermsResponse termsResponse = responseSuggest.getTermsResponse(); 
			    	 if(termsResponse != null) {  
		                Map<String, List<TermsResponse.Term> > termsMap = termsResponse.getTermMap();  
		                for(Map.Entry<String, List<TermsResponse.Term> > termsEntry : termsMap.entrySet()) {  
		                    List<TermsResponse.Term> termList = termsEntry.getValue();  
		                    for(TermsResponse.Term term : termList) { 
		                    	sugValue.add(term.getTerm().toString());
		                    }  
		                }  
				    }
			    }
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
		 return sugValue;
	 }
	
    /**
     * 联想词 可根据拼音 拼音首字母联想
     * 描述：该方法联想出来的为词组 不在是商品名称
     * @param params 查询参数
     * @param sellerCode  索引库分片
     * @return
     */
    public  List<String> getSuggestLxc(SolrQuery params,String sellerCode){
    	List<String> sugValue = new ArrayList<String>();
    	if(params == null || sellerCode==null){
    		return sugValue;
    	}
    	 QueryResponse responseSuggest = null;
		 try {	 
			 
			 SolrClient solrSingleton = getSolrSingleton(sellerCode);
			 responseSuggest=solrSingleton.query(params);
			 
		     if(responseSuggest != null ){ 
	    	   SolrDocumentList results = responseSuggest.getResults();
	    	   if(results!=null && results.size()>0){
	    		  for (SolrDocument doc : results) {
	    			 sugValue.add(doc.get("s1").toString());
	    		  }
	    	   }
		     }
			   
		   } catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
		 return sugValue;
	 }
    
    /**
	 * 关键词 分类  品牌等搜索
	 * @param params 查询参数
     * @param sellerCode  索引库分片
     * @return
     */
    public Map<String,Object> getSearchData(SolrQuery params,String sellerCode){
    	Map<String,Object> map = new ConcurrentHashMap<String,Object>();
    	if(params == null || sellerCode==null){
    		return null;
    	}
    	QueryResponse responseProduct = null;
    	 try {
    		 SolrClient solrSingleton = getSolrSingleton(sellerCode);
    		 responseProduct=solrSingleton.query(params);
	    	
	    	 SolrDocumentList list = responseProduct.getResults();
             long counts= list.getNumFound();
             
	    	List<SolrData> items_rep = responseProduct.getBeans(SolrData.class);
	    	 
	    	List<FacetField> facetField = responseProduct.getFacetFields();
		    if(!items_rep.isEmpty()){
		    	map.put("items_rep", items_rep);
		    	map.put("counts", counts);
		    	 if(facetField!=null && facetField.size()>0){
		    		 map.put("facetField", facetField);
		    	 } 	 
		    	return map;
		    }
		    
    	 } catch (SolrServerException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
    	 
    	 return map;
    }
}
