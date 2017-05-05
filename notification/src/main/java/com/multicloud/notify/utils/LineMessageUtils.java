package com.multicloud.notify.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.multicloud.notify.model.LinePush;
import com.multicloud.notify.model.LinePush.Message;

public class LineMessageUtils {

	
	public static void main(String[] args){
		try {
			Message m=new Message("text","test");
			m.text="hello yyy";
			LineMessageUtils.push(null, "Uf1e8cceebfe93c774ac77eb03f6465e0", m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//public static String mtoken="iklLqLaE+0fia7Rll+TG/yZpswO8MOBp6HUWMY/tswT2qBEEhW1TGKP8acPwUaj4qnLKvKvFBt5tSyoQIRSEBHFU9j4RNctQxHkdJIkRh4QI5vrjiyBINKgnuCkNDLxTf4+vYJXjH/4zDzVBZW5JRQdB04t89/1O/w1cDnyilFU=";
	
	
	public static void push(String token, String to, LinePush.Message... messages) throws IOException {
		HttpClient client = HttpClientBuilder.create().build();
			
		HttpPost post = new HttpPost("https://api.line.me/v2/bot/message/push");
		post.setHeader("Authorization", "Bearer " + token);
		
		LinePush push = new LinePush();
		push.to = to;
		push.messages = new ArrayList<LinePush.Message>();
		
		for (LinePush.Message m : messages) {		
			push.messages.add(m); // copy array elements into list 
		}
	
		String json = JsonUtils.toJson(push);
	    StringEntity entity = new StringEntity(json);
	    post.setEntity(entity);
	    post.setHeader("Accept", "application/json");
	    post.setHeader("Content-type", "application/json");
	 
	    HttpResponse response = client.execute(post);
	    
		System.out.println(response.getStatusLine().getStatusCode());
		
    }
}
