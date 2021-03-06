/**
 * Copyright 2018 Netifi Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netifi.proteus.demo.helloworld.service;

import com.netifi.proteus.demo.helloworld.HelloWorldRequest;
import com.netifi.proteus.demo.helloworld.HelloWorldResponse;
import com.netifi.proteus.demo.helloworld.HelloWorldService;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DefaultHelloWorldService implements HelloWorldService {

    @Override
    public Mono<HelloWorldResponse> sayHello(HelloWorldRequest message, ByteBuf metadata) {
        System.out.println("Received Hello Request for: " + message.getName());

        return Mono.fromSupplier(() -> HelloWorldResponse.newBuilder()
                .setMessage(String.format("Hello, %s!", message.getName()))
                .build());
    }
}
