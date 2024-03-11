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
package org.letter.console.admin.config.properties;

import lombok.Data;

/**
 * UserRsaProperties
 * RSA秘钥配置
 *
 * @author letter
 **/
@Data
public class UserRsaProperties {
	private boolean open;
	private String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA0vfvyTdGJkdbHkB8mp0f3FE0GYP3AYPaJF7jUd1M0XxFSE2ceK3k2kw20YvQ09NJKk+OMjWQl9WitG9pB6tSCQIDAQABAkA2SimBrWC2/wvauBuYqjCFwLvYiRYqZKThUS3MZlebXJiLB+Ue/gUifAAKIg1avttUZsHBHrop4qfJCwAI0+YRAiEA+W3NK/RaXtnRqmoUUkb59zsZUBLpvZgQPfj1MhyHDz0CIQDYhsAhPJ3mgS64NbUZmGWuuNKp5coY2GIj/zYDMJp6vQIgUueLFXv/eZ1ekgz2Oi67MNCk5jeTF2BurZqNLR3MSmUCIFT3Q6uHMtsB9Eha4u7hS31tj1UWE+D+ADzp59MGnoftAiBeHT7gDMuqeJHPL4b+kC+gzV4FGTfhR9q3tTbklZkD2A==";
	private String publicKey = "LS0tLS1CRUdJTiBQVUJMSUMgS0VZLS0tLS0KTUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FROEFNSUlCQ2dLQ0FRRUE3NjBPSGhGWVZ2WUFxRW9nWHE2agpSNGtrd2R3VTdBclZrVmpUa3pYRWF3SVUwTkY1eHlWVGdmaW0yWFo3R1J5NGMwcHlaR0hNTHpSdlJ4bVdqSGFTCkVDRzYvVUd6eExsYUl5Ymd6QmpralRHZVZQcHZMZDRGbUhyS0ZpQXdZT28yUnJ2bU44SFF0cFFaK2JjMjF1aTYKWkdhRDlMUVU3Q2FqVjYvM3RMNitRQkdrS0hrZHRlY3I4SUt6QjhRQ1pZdjNiMFpwcHpiaW1OTWU0VTNnVksvLwpLOFJMbmVES1I5SkFzVk0rUkgwaGNmQU5kNTVIM2F4ZUZBUEhML3k0TmFFTWN3N01UWXljRFJxd2JwVkVGaTBPCkprWGlhcmo1b3AxUHdYRVd3c05HcjI2OU44dG5nVktDSjI0TFFXbHFhWmFkYTFTUzhZNjlGYXFqUDJwcGdCZ3MKZ3dJREFRQUIKLS0tLS1FTkQgUFVCTElDIEtFWS0tLS0tCg==";

}