package com.training.weather.ingestor.core.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class ConcurrentUtils {
  public static void executeWithTimeout(Runnable task, long timeout) {
    Callable<Object> callable = () -> {
      task.run();
      return null;
    };

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future future = executorService.submit(callable);

    try {
      future.get(timeout, TimeUnit.MILLISECONDS);
    } catch (InterruptedException | ExecutionException | TimeoutException ex) {
      future.cancel(true);
    }
  }
}
