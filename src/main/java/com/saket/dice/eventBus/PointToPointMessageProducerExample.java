package com.saket.dice.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageProducer;

public class PointToPointMessageProducerExample {

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new Sender());
    vertx.deployVerticle(new Receiver());
  }

  public static class Sender extends AbstractVerticle {

    MessageProducer<String> messageProducer;

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
   
      vertx.setPeriodic(1000, id -> {
        // Send a message every second
        vertx.eventBus().send(Sender.class.getName(),"Sending a message...");
       ;
      });
    }

    @Override
    public void stop(final Promise<Void> stopPromise) throws Exception {
      messageProducer.close(whenDone -> stopPromise.complete());
    }
  }

  public static class Receiver extends AbstractVerticle {

  //  private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(Sender.class.getName(), message -> {
        System.out.println("Received:"+ message.body());
      });
    }
  }
}