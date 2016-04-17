package org.xiongmaotailang.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component("configInfo")
@ConfigurationProperties(prefix = "hello",locations = "classpath:configinfo.properties")  
public class ConfigInfo {
    private String title;

    public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

    public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
        
}