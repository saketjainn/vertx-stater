package com.saket.dice.verticles;

import io.vertx.core.Promise;
import io.vertx.core.AbstractVerticle;

public class VerticleN extends AbstractVerticle{
 @Override
 public void start(final Promise<Void> startPromise) throws Exception {
    // System.out.println("start :" + getClass().getName() + "Thread :" +Thread.currentThread().getName());
    System.out.println("start :" + getClass().getName() + "with config" + config().toString());
     startPromise.complete();
 }
}
