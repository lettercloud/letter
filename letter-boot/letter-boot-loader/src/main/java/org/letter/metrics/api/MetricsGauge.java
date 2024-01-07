package org.letter.metrics.api;

/**
 * MetricsGauge
 *
 * @author wuhao
 */

public interface MetricsGauge<T> {
	/**
	 * getValue
	 *
	 * @return
	 */
	T getValue();
}
