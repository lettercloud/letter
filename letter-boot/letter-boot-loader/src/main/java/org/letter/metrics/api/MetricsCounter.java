package org.letter.metrics.api;

/**
 * MetricsCounter
 *
 * @author wuhao
 */

public interface MetricsCounter {
	default void inc() {
		inc(1);
	}

	void inc(long n);

	default void dec() {
		dec(1);
	}

	void dec(long n);


}
