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
package com.netifi.proteus.demo.helloworld;

import com.netifi.proteus.springboot.EnableProteus;
import io.netifi.proteus.annotations.ProteusClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableProteus(group = "helloworld.clients")
public class Client {

    /**
     * Main entry-point of the application.
     *
     * @param args command line args
     */
    public static void main(String... args) {
        SpringApplication.run(Client.class, args);
    }

    /**
     * Runs the demo client.
     */
    @Component
    public class ClientRunner implements CommandLineRunner {

        @ProteusClient(group = "helloworld.services")
        private HelloWorldServiceClient client;

        @Override
        public void run(String... args) throws Exception {
            HelloWorldRequest request = HelloWorldRequest.newBuilder()
                    .setName(getName(args))
                    .build();

            System.out.println(String.format("Sending '%s' to helloworld-service", request.getName()));

            HelloWorldResponse response = client.sayHello(request).block();

            System.out.println(String.format("Received from helloworld-service: %s", response.getMessage()));
        }

        /**
         * Gets name to send to helloworld-service from command line arguments.
         *
         * @param args command line arguments
         * @return name to send to helloworld-service
         */
        private String getName(String... args) {
            if (args.length > 0) {
                return args[0];
            } else {
                return "World";
            }
        }
    }
}
