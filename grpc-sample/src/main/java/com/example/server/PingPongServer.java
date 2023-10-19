package com.example.server;

import com.example.stub.PingPongGrpc;
import com.example.stub.RequestDto;
import com.example.stub.ResponseDto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class PingPongServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(8080)
                .addService(new PingPongImpl())
                .build()
                .start();

        System.out.println("Server started");
        server.awaitTermination();
    }

    static class PingPongImpl extends PingPongGrpc.PingPongImplBase {
        @Override
        public void getResponse(RequestDto request, StreamObserver<ResponseDto> responseObserver) {
            String requestMessage = request.getMessage();
            System.out.println("request message: " + requestMessage);

            ResponseDto response = ResponseDto.newBuilder()
                    .setMessage("pong")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

}
