package com.netifi.proteus.demo.helloworld;

@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.7.15)",
    comments = "Source: helloworld.proto")
@io.netifi.proteus.annotations.internal.ProteusGenerated(
    type = io.netifi.proteus.annotations.internal.ProteusResourceType.CLIENT,
    idlClass = BlockingHelloWorldService.class)
public final class BlockingHelloWorldServiceClient implements BlockingHelloWorldService {
  private final com.netifi.proteus.demo.helloworld.HelloWorldServiceClient delegate;

  public BlockingHelloWorldServiceClient(io.rsocket.RSocket rSocket) {
    this.delegate = new com.netifi.proteus.demo.helloworld.HelloWorldServiceClient(rSocket);
  }

  public BlockingHelloWorldServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry) {
    this.delegate = new com.netifi.proteus.demo.helloworld.HelloWorldServiceClient(rSocket, registry);
  }

  public com.netifi.proteus.demo.helloworld.HelloWorldResponse sayHello(com.netifi.proteus.demo.helloworld.HelloWorldRequest message) {
    return sayHello(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  public com.netifi.proteus.demo.helloworld.HelloWorldResponse sayHello(com.netifi.proteus.demo.helloworld.HelloWorldRequest message, io.netty.buffer.ByteBuf metadata) {
    return delegate.sayHello(message, metadata).block();
  }

}

