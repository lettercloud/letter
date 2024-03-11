package org.letter.console.admin;

import com.alibaba.fastjson.JSONObject;
import org.letter.console.admin.user.domain.User;
import org.letter.console.admin.user.service.UserService;

/**
 * InitData
 *
 * @author letter
 */
public class InitData {
	
	public static InitData getInstance(){
		return INSTANCE;
	}

	/**
	 * 仅供测试使用
	 * 
	 */
	private static InitData INSTANCE = new InitData();
	
	public void initUser(UserService userService){
		try {
			userService.create(getUser());
		} catch (Exception e){
			e.printStackTrace();
		}

	}
	
	public User getUser() {
		String data = """
			{
				"contacts":"string",
				"createBy":"demo",
				"createTime":1707040177208,
				"email":"demo",
				"maintainer":true,
				"nickname":"demo",
				"password":"demo",
				"phone":"demo",
				"portrait":"demo",
				"roles":"1",
				"updateBy":"demo",
				"updateTime":1707040177208,
				"username":"demo"
			}
			""";
		User user = JSONObject.parseObject(data, User.class);
		System.out.println(JSONObject.toJSONString(user, true));
		return user;
	}
}
