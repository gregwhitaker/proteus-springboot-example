package com.netifi.proteus.demo.helloworld;

/**
 */
@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.7.15)",
    comments = "Source: helloworld.proto")
public interface BlockingHelloWorldService {
  String SERVICE_ID = "com.netifi.proteus.demo.helloworld.HelloWorldService";
  String METHOD_SAY_HELLO = "SayHello";

  /**
   */
  com.netifi.proteus.demo.helloworld.HelloWorldResponse sayHello(com.netifi.proteus.demo.helloworld.HelloWorldRequest message, io.netty.buffer.ByteBuf metadata);
}
