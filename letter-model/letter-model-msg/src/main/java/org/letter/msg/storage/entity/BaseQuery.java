package org.letter.msg.storage.entity;

/**
 * BaseModel
 *
 * @author wuhao
 * @description: BaseModel
 * @createTime 2024/04/18 23:12:00
 */

public class BaseQuery {
	private long appId;
	private String userId;

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
