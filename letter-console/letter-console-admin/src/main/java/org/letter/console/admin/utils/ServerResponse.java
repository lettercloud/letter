package org.letter.console.admin.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * ServerResponse
 * @author letter
 */
@Getter
@Setter
public class ServerResponse {
	private Object dat = "";
	private Object err = "";

	public ServerResponse(Object dat, Object err) {
		this.dat = dat;
		this.err = err;
	}

	public static ServerResponse build(Object dat, Object err){
		return new ServerResponse(dat, err);
	}
}
