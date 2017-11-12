package httpclient;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class TestAPIService {
	
	@Test
	public void test() throws Exception{
		APIService service = new APIService();
		
		Map<String, Object> map  = new HashMap<>();
	    map.put("username", "zhou");
	    map.put("password", "123456");
	    
		HttpResult doPost = service.doPost("http://localhost:8480/httpserver/login", map);
		
		System.out.println(doPost.getBody());
		System.out.println(doPost.getCode());
	}
}
