/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.letter.console.modules.security.config.bean;

import lombok.Data;

/**
 * Jwt参数配置
 *
 * @author Zheng Jie
 * @date 2019年11月28日
 */
@Data
public class SecurityProperties {

	/**
	 * Request Headers ： Authorization
	 */
	private String header = "Authorization";

	/**
	 * 令牌前缀，最后留个空格 Bearer
	 */
	private String tokenStartWith = "Bearer";

	/**
	 * 必须使用最少88位的Base64对该令牌进行编码
	 */
	private String base64Secret = "ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=";

	/**
	 * 令牌过期时间 此处单位/毫秒
	 */
	private Long tokenValidityInSeconds = 14400000L;

	/**
	 * 在线用户 key，根据 key 查询 redis 中在线用户的数据
	 */
	private String onlineKey = "online-token:";

	/**
	 * 验证码 key
	 */
	private String codeKey = "captcha-code:";

	/**
	 * token 续期检查
	 */
	private Long detect = 1800000L;

	/**
	 * 续期时间
	 */
	private Long renew = 3600000L;

	public String getTokenStartWith() {
		return tokenStartWith + " ";
	}
}
