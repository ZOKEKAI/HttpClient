package httpclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 提供给别的系统使用的业务接口
 */
public class APIService {
	
	//带参数的get请求
	public HttpResult doGet(String url,Map<String, Object> map) throws Exception{
		//封装httpclient 发送请求 处理业务 得到数据响应  httpresult
		
		//1.创建httpclient 
		CloseableHttpClient httpClient = HttpClients.createDefault();
			
		//3.参数设置
		URIBuilder builder = new URIBuilder(url);
		
		if(map!=null){
			Set<Entry<String,Object>> entrySet = map.entrySet();
			
			for (Entry<String, Object> entry : entrySet) {
				builder.setParameter(entry.getKey(), entry.getValue().toString());
			}
		}
		//2.创建httpget请求  
		HttpGet get = new HttpGet(builder.build());
		
		//4.执行请求   得到响应结果
		CloseableHttpResponse response = httpClient.execute(get);
		
		int code = response.getStatusLine().getStatusCode();
		
		HttpEntity entity = response.getEntity();
		String body= null; 
		if(entity!=null){
			 body = EntityUtils.toString(entity, "utf-8");
		}
		
		HttpResult result = new HttpResult(code, body);
		
		//5.解析成httpresult
		return result;	
	}
	
	//不带参数的get请求
	public HttpResult doGet(String url) throws Exception{
		return doGet(url, null);
	}
	
	//带参数的Post请求
	public HttpResult doPost(String url,Map<String, Object> map) throws Exception{
		//1.创建httpclient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//2.创建httppost请求
		HttpPost post =new HttpPost(url);
		
		//3.为httppost请求设置表单实体
		if(map!=null){
			Set<Entry<String,Object>> entrySet = map.entrySet();
			
			List<BasicNameValuePair> parameters = new ArrayList<>();
			
			for (Entry<String, Object> entry : entrySet) {
				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
				
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters , "utf-8");
			post.setEntity(entity);
		}
		
		//4.执行请求
		CloseableHttpResponse response = httpClient.execute(post);
		
		//5.解析成httpresult
		int code = response.getStatusLine().getStatusCode();
		
		HttpEntity entity = response.getEntity();
		String body= null; 
		if(entity!=null){
			 body = EntityUtils.toString(entity, "utf-8");
		}
		
		HttpResult result = new HttpResult(code, body);
		
		return result;	
	}
	
	//不带参数的post请求
	public HttpResult doPost(String url) throws Exception{
		return doPost(url, null);
	}
}