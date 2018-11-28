package com.xabe.thread.pool;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ThreadPoolTest {

	@Test
	public void givenATaskWhenInvokeThreadPoolThenReturn() throws Exception {
		final int threads = 1;
		final ThreadPool pool = new ThreadPool(threads);
		final Logger logger = mock(Logger.class);

		final Runnable runnable = () -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Hello");
		};
		pool.execute(runnable);

		verify(logger, timeout(3000)).info(any(String.class));
		
//		await().atLeast(Duration.ONE_MINUTE).until(() -> assertThat(captor.getValue(), is("Hello")));

	}

}
