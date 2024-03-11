package org.letter.console.admin;

import redis.embedded.RedisServer;

/**
 * TestRedisServer
 *
 * @author letter
 */
public class TestRedisServer {

	public static void startServer() {

		try {
			RedisServer redisServer = new RedisServer(6379);
			redisServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}