package com.saket.dice.verticles;

import io.vertx.core.Promise;
import io.vertx.core.AbstractVerticle;

public class VerticleAA extends AbstractVerticle{
 @Override
 public void start(final Promise<Void> startPromise) throws Exception {
     System.out.println("start :" + getClass().getName());
     startPromise.complete();
 }

 @Override
 public void stop(Promise<Void> stopPromise) throws Exception {
    System.out.println("Stop"+ getClass().getName());
    stopPromise.complete();
 }
}
