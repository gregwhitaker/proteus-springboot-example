package com.netifi.proteus.demo.helloworld;

@javax.annotation.Generated(
    value = "by Proteus proto compiler (version 0.7.15)",
    comments = "Source: helloworld.proto")
@io.netifi.proteus.annotations.internal.ProteusGenerated(
    type = io.netifi.proteus.annotations.internal.ProteusResourceType.CLIENT,
    idlClass = HelloWorldService.class)
public final class HelloWorldServiceClient implements HelloWorldService {
  private final io.rsocket.RSocket rSocket;
  private final java.util.function.Function<? super org.reactivestreams.Publisher<com.netifi.proteus.demo.helloworld.HelloWorldResponse>, ? extends org.reactivestreams.Publisher<com.netifi.proteus.demo.helloworld.HelloWorldResponse>> sayHello;

  public HelloWorldServiceClient(io.rsocket.RSocket rSocket) {
    this.rSocket = rSocket;
    this.sayHello = java.util.function.Function.identity();
  }

  public HelloWorldServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry) {
    this.rSocket = rSocket;
    this.sayHello = io.netifi.proteus.metrics.ProteusMetrics.timed(registry, "proteus.client", "service", HelloWorldService.SERVICE, "method", HelloWorldService.METHOD_SAY_HELLO);
  }

  public reactor.core.publisher.Mono<com.netifi.proteus.demo.helloworld.HelloWorldResponse> sayHello(com.netifi.proteus.demo.helloworld.HelloWorldRequest message) {
    return sayHello(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<com.netifi.proteus.demo.helloworld.HelloWorldResponse> sayHello(com.netifi.proteus.demo.helloworld.HelloWorldRequest message, io.netty.buffer.ByteBuf metadata) {
    return reactor.core.publisher.Mono.defer(new java.util.function.Supplier<reactor.core.publisher.Mono<io.rsocket.Payload>>() {
      @java.lang.Override
      public reactor.core.publisher.Mono<io.rsocket.Payload> get() {
        final io.netty.buffer.ByteBuf metadataBuf = io.netifi.proteus.frames.ProteusMetadata.encode(io.netty.buffer.ByteBufAllocator.DEFAULT, HelloWorldService.SERVICE, HelloWorldService.METHOD_SAY_HELLO, metadata);
        io.netty.buffer.ByteBuf data = serialize(message);
        return rSocket.requestResponse(io.rsocket.util.ByteBufPayload.create(data, metadataBuf));
      }
    }).map(deserializer(com.netifi.proteus.demo.helloworld.HelloWorldResponse.parser())).transform(sayHello);
  }

  private static io.netty.buffer.ByteBuf serialize(final com.google.protobuf.MessageLite message) {
    int length = message.getSerializedSize();
    io.netty.buffer.ByteBuf byteBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.buffer(length);
    try {
      message.writeTo(com.google.protobuf.CodedOutputStream.newInstance(byteBuf.internalNioBuffer(0, length)));
      byteBuf.writerIndex(length);
      return byteBuf;
    } catch (Throwable t) {
      byteBuf.release();
      throw new RuntimeException(t);
    }
  }

  private static <T> java.util.function.Function<io.rsocket.Payload, T> deserializer(final com.google.protobuf.Parser<T> parser) {
    return new java.util.function.Function<io.rsocket.Payload, T>() {
      @java.lang.Override
      public T apply(io.rsocket.Payload payload) {
        try {
          com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
          return parser.parseFrom(is);
        } catch (Throwable t) {
          throw new RuntimeException(t);
        } finally {
          payload.release();
        }
      }
    };
  }
}
