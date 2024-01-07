package org.letter.metrics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.letter.common.extension.ExtensionLoader;

/**
 * MetricsFactoryTest
 *
 * @author wuhao
 */

class MetricsFactoryTest {
	@Test
	void testMetrics() {
		Assertions.assertNull(MetricsFactory.timer("test", "test1"));
		Assertions.assertNull(MetricsFactory.counter("test", "test1"));
		Assertions.assertNull(MetricsFactory.meter("test", "test1"));
		Assertions.assertNull(MetricsFactory.histogram("test", "test1"));
		MetricsFactory.gauge(() -> 1,"test", "test1");
	}

	@Test
	void testLoader(){
		 MetricsBuilder builder = ExtensionLoader.loadOrByDefaultFactory(MetricsBuilder.class,
				"default", MetricsBuilderNop::new);
		 Assertions.assertTrue(builder instanceof MetricsBuilderSimple);
	}
}
