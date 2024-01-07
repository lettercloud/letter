package org.letter.thread.spi;

/**
 * ObservableThreadExecuteNop
 *
 * @author wuhao
 */
public class ObservableThreadExtNop implements IObservableThreadExt {

    @Override
    public Runnable refreshRunnable(Runnable runnable) {
        return runnable;
    }
}
