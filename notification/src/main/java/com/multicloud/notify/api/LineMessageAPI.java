package com.multicloud.notify.api;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.multicloud.notify.model.LinePush;
import com.multicloud.notify.model.Friend;
import com.multicloud.notify.model.WebhookReq;
import com.multicloud.notify.utils.JsonUtils;
import com.multicloud.notify.utils.LineMessageUtils;


@RestController
public class LineMessageAPI {

	public  String token="iklLqLaE+0fia7Rll+TG/yZpswO8MOBp6HUWMY/tswT2qBEEhW1TGKP8acPwUaj4qnLKvKvFBt5tSyoQIRSEBHFU9j4RNctQxHkdJIkRh4QI5vrjiyBINKgnuCkNDLxTf4+vYJXjH/4zDzVBZW5JRQdB04t89/1O/w1cDnyilFU=";
    private String defaultTo="Uf1e8cceebfe93c774ac77eb03f6465e0";	
	
	@RequestMapping(value = "/line/webhook", method = RequestMethod.POST)
    void getFollower(HttpServletRequest request) {
		try {
			
		
		
		InputStreamReader isr = new InputStreamReader(request.getInputStream(), "UTF-8");
    	String board = IOUtils.toString(isr);
    	
    	WebhookReq req = JsonUtils.fromJson(board, WebhookReq.class);
    	for (WebhookReq.Event event : req.events) {
    		if ("follow".equals(event.type)) {
    			Friend f = new Friend();
    			f.timestamp = event.timestamp;
    			f.type = event.source.type;
    			f.id = event.source.getId();
    			
    			//String key = String.format("gce.line.friend.%s", f.id);    			
    			//redis.set(key, f);    			
    			
    		} else if ("unfollow".equals(event.type)) {    			
    			String key = String.format("gce.line.friend.%s", event.source.getId());    			
    			//redis.del(key);
    		}
    	}
    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping(value = "/line/send/{mesg}", method = RequestMethod.GET)
	String sendMessage(@PathVariable String mesg){
		/*StringWriter sw = new StringWriter();
    	PrintWriter pw = new PrintWriter(sw);
    	Enumeration<String> en = request.getHeaderNames();
		while (en.hasMoreElements()) {
			String key = en.nextElement();
			String val = request.getHeader(key);
			
			pw.printf("%s = %s\n", key, val);
		}
		pw.flush();
		
		headers = sw.toString();		
    	
		response.setContentType("text/plain");
    	PrintWriter out = response.getWriter();
		
    	InputStreamReader isr = new InputStreamReader(request.getInputStream(), "UTF-8");
    	message = IOUtils.toString(isr);
    	*/
		try {
			//String mesg="hello ttt";
	    	LinePush.Message lpm = new LinePush.Message("text", mesg); 
	    	LineMessageUtils.push(token, defaultTo, lpm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sendOk";
		
    	/*Set<String> ids = redis.keys("gce.line.friend.*");
		for (String id : ids) {
			Friend f = redis.get(id, Friend.class);
			String to = f.id;			
			
			out.printf("Push line message to [%s] %s\n", f.type, f.id);
			
			LineMessageUtils.push(token, to, lpm);
		}*/
	}
	
	
	
}
