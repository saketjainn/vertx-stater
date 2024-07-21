package com.saket.dice.eventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class RequestResponseExample {

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
    vertx.deployVerticle(new ResponseVerticle());
  }

  public static class RequestVerticle extends AbstractVerticle {

  //  private static final Logger LOG = LoggerFactory.getLogger(RequestVerticle.class);
    static final String ADDRESS = "my.request.address";

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      var eventBus = vertx.eventBus();
      final String message = "Hello World!";
      System.out.println("Sending: "+ message);
      eventBus.<String>request(ADDRESS, message, reply -> {
        System.out.println("Response: "+ reply.result().body());
      });
    }
  }

  public static class ResponseVerticle extends AbstractVerticle {

   // private static final Logger LOG = LoggerFactory.getLogger(ResponseVerticle.class);

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(RequestVerticle.ADDRESS, message -> {
        System.out.println("Received Message: {}"+message.body());
        message.reply("Received your message. Thanks!");
      });
    }
  }
}
