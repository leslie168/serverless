package com.multicloud.notify.model;

import java.util.List;

public class LinePush {
	public String to;
	public List<Message> messages;
	
	public static class Message {
		public String type;
		public String text;
		public String originalContentUrl;
		public String previewImageUrl;
		
		public Message(String type) {
			this.type = type;
		}
		
		public Message(String type, String text) {
			this.type = type;
			this.text = text;
		}
	}
}