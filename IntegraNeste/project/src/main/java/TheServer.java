import com.project.grpc.ConnectionReply;
import com.project.grpc.ConnectionRequest;
import com.project.grpc.GreeterGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.Server;

public class TheServer {

  private static final Logger msg = Logger.getLogger(Server.class.getName());

  private Server server;

  private void start() throws IOException {
    int port = 9000; //Porto no qual o servidor deve correr
    server = ServerBuilder.forPort(port).addService(new GreeterImpl()).build().start();

    msg.info("Servidor iniciado, à escuta no porto " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.err.println("Shutting down gRpc server since JVM is shutting down");
        TheServer.this.stop();
        System.err.println("Server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }


  public static void main(String[] args) throws IOException, InterruptedException {
    final TheServer server = new TheServer();
    server.start();
    server.blockUntilShutdown();
  }

  static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void greetAndConnect (ConnectionRequest request, StreamObserver<ConnectionReply> responseObserver) {
      ConnectionReply reply = ConnectionReply.newBuilder().setMessage("Ola mano " + request.getName()).build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }

  }


  //Method to receive the list of owners and return a list of thei cars in response
  public void listOfCars (ArrayList listOfOwners){ //Mudar depois o tipo de void para ArrayList
    //Ver se é preciso separar por owner ou se pode ser todos em conjunto

    //Procurar por id do owner
  }


  //Method to deserealize ProtoBuffer


  //Method to deserealize ProtoBuffer



  //Method to deserealize XML


  //Method to serealize XML


  //static class GreeterImpl extends Greeter

}
