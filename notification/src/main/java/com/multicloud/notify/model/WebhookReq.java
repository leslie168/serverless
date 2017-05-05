package com.multicloud.notify.model;

import java.util.NoSuchElementException;

public class WebhookReq {
	public Event[] events;

	public static class Event {
		public String replyToken;
		public String type; // message, follow, unfollow, join, leave, postback, beacon
		public long timestamp;
		public Source source;
		public Message message;
		public Postback postback;
		public Beacon beacon;
	}
	
	public static class Source {
		public String type; // user, group, room
		public String userId;
		public String groupId;
		public String roomId;
		
		public String getId() {			
			if (userId != null) {
				return userId;
			} else if (groupId != null) {
				return groupId;				
			} else if (roomId != null) {
				return roomId;
			}
			
			throw new NoSuchElementException();
		}
	}
	
	public static class Message {
		public String id;
		public String type; // text, image, video, audio, location
		
		public String text;
		
		public String packageId;
		public String stickerId;
		
		public String title;
		public String address;
		public Double latitude;
		public Double longitude;
	}
	
	public static class Postback {
		public String data;
	}
	
	public static class Beacon {
		public String hwid;
		public String type;
	}
}
