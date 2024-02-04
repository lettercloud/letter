package org.letter.console.admin.user.service.impl;

import org.letter.console.admin.user.service.CacheProxy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * CacheProxyImpl
 * @author wuhao
 */
@Service
public class CacheProxyImpl implements CacheProxy {
	@Override
	public boolean expire(String key, long time) {
		return false;
	}

	@Override
	public boolean expire(String key, long time, TimeUnit timeUnit) {
		return false;
	}

	@Override
	public long getExpire(Object key) {
		return 0;
	}

	@Override
	public List<String> scan(String pattern) {
		return null;
	}

	@Override
	public List<String> findKeysForPage(String patternKey, int page, int size) {
		return null;
	}

	@Override
	public boolean hasKey(String key) {
		return false;
	}

	@Override
	public void del(String... keys) {

	}

	@Override
	public void scanDel(String pattern) {

	}

	@Override
	public Object get(String key) {
		return null;
	}

	@Override
	public List<Object> multiGet(List<String> keys) {
		return null;
	}

	@Override
	public boolean set(String key, Object value) {
		return false;
	}

	@Override
	public boolean set(String key, Object value, long time) {
		return false;
	}

	@Override
	public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
		return false;
	}

	@Override
	public Object hget(String key, String item) {
		return null;
	}

	@Override
	public Map<Object, Object> hmget(String key) {
		return null;
	}

	@Override
	public boolean hmset(String key, Map<String, Object> map) {
		return false;
	}

	@Override
	public boolean hmset(String key, Map<String, Object> map, long time) {
		return false;
	}

	@Override
	public boolean hset(String key, String item, Object value) {
		return false;
	}

	@Override
	public boolean hset(String key, String item, Object value, long time) {
		return false;
	}

	@Override
	public void hdel(String key, Object... item) {

	}

	@Override
	public boolean hHasKey(String key, String item) {
		return false;
	}

	@Override
	public double hincr(String key, String item, double by) {
		return 0;
	}

	@Override
	public double hdecr(String key, String item, double by) {
		return 0;
	}

	@Override
	public Set<Object> sGet(String key) {
		return null;
	}

	@Override
	public boolean sHasKey(String key, Object value) {
		return false;
	}

	@Override
	public long sSet(String key, Object... values) {
		return 0;
	}

	@Override
	public long sSetAndTime(String key, long time, Object... values) {
		return 0;
	}

	@Override
	public long sGetSetSize(String key) {
		return 0;
	}

	@Override
	public long setRemove(String key, Object... values) {
		return 0;
	}

	@Override
	public List<Object> lGet(String key, long start, long end) {
		return null;
	}

	@Override
	public long lGetListSize(String key) {
		return 0;
	}

	@Override
	public Object lGetIndex(String key, long index) {
		return null;
	}

	@Override
	public boolean lSet(String key, Object value) {
		return false;
	}

	@Override
	public boolean lSet(String key, Object value, long time) {
		return false;
	}

	@Override
	public boolean lSet(String key, List<Object> value) {
		return false;
	}

	@Override
	public boolean lSet(String key, List<Object> value, long time) {
		return false;
	}

	@Override
	public boolean lUpdateIndex(String key, long index, Object value) {
		return false;
	}

	@Override
	public long lRemove(String key, long count, Object value) {
		return 0;
	}

	@Override
	public void delByKeys(String prefix, Set<Long> ids) {

	}
}
