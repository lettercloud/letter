package org.letter.metrics.api;

/**
 * MetricsHistogram
 *
 * @author wuhao
 */

public interface MetricsHistogram {
	void update(int value);

	void update(long value);
	long getCount();
}
