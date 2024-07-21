package com.saket.dice.verticles;

import io.vertx.core.Promise;
import io.vertx.core.AbstractVerticle;

public class VerticleA extends AbstractVerticle{
 @Override
 public void start(final Promise<Void> startPromise) throws Exception {
     System.out.println("start :" + getClass().getName());
     vertx.deployVerticle(new VerticleAA(),whenDeployed->{
        System.out.println("Deployed" + VerticleAA.class.getName());
        vertx.undeploy(whenDeployed.result());
     });
     vertx.deployVerticle(new VerticleAB(),whenDeployed->{
        System.out.println("Deployed" + VerticleAB.class.getName());
       // vertx.undeploy(whenDeployed.result());
     });
     startPromise.complete();
 }
}
