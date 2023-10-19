package com.example.client;

import com.example.stub.PingPongGrpc;
import com.example.stub.RequestDto;
import com.example.stub.ResponseDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PingPongClient {

    public static void main(String[] args) {
        RequestDto request = RequestDto.newBuilder().setMessage("ping").build();

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        PingPongGrpc.PingPongBlockingStub stub = PingPongGrpc.newBlockingStub(channel);

        ResponseDto response = stub.getResponse(request);

        System.out.println("response message: " + response.getMessage());

        channel.shutdown();
    }

}
