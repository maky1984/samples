package com.test.notifier.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Token {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Column
	private String deviceId;

	@Column
	private String pushToken;

	@ManyToOne(fetch = FetchType.LAZY)
	private DevicePlatform devidePlatform;

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setDevidePlatform(DevicePlatform devidePlatform) {
		this.devidePlatform = devidePlatform;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public DevicePlatform getDevidePlatform() {
		return devidePlatform;
	}

	public long getId() {
		return id;
	}

	public String getPushToken() {
		return pushToken;
	}

	public User getUser() {
		return user;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Token token = (Token) obj;
		return id == token.id;
	}

	@Override
	public int hashCode() {
		return Long.valueOf(id).hashCode();
	}
}
