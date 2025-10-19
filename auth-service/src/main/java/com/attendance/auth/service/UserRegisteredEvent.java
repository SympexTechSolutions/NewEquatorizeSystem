package com.attendance.auth.service;

import org.springframework.context.ApplicationEvent;

import com.attendance.auth.entity.User;

public class UserRegisteredEvent extends ApplicationEvent {
	
	private final User user;
	
	public UserRegisteredEvent(Object source,User user) {
		super(source);
		this.user=user;
	}
	
	public User getUser() {
        return user;
    }
}
