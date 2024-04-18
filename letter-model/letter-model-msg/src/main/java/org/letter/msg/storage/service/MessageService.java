package org.letter.msg.storage.service;

import org.letter.msg.storage.entity.MessageQuery;
import org.letter.msg.storage.entity.MessageQueryRet;
import org.letter.msg.storage.entity.MessageSave;
import org.letter.msg.storage.entity.MessageSaveRet;

/**
 * MessageService
 * @author wuhao
 * @description: UserGroupService
 * @createTime 2024/04/08 23:04:00
 */


public interface MessageService {
	/**
	 * save
	 * @param save
	 * @return MessageSaveRet
	 */
	MessageSaveRet save(MessageSave save);

	/**
	 * query
	 * @param query
	 * @return MessageQueryRet
	 */
	MessageQueryRet query(MessageQuery query);
}
