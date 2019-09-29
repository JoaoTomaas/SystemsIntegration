import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.project.grpc.ConnectionReply;
import com.project.grpc.ConnectionRequest;
import com.project.grpc.GreeterGrpc;
import com.project.protob.CarOwnerProtoB;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.protob.CarOwnerProtoB.Owner;
import com.project.protob.CarOwnerProtoB.OwnerList;

import com.project.protob.CarOwnerProtoB.Car;
import com.project.protob.CarOwnerProtoB.CarList;

import io.grpc.Server;


public class TheServer {

  static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void greetAndConnect (ConnectionRequest request, StreamObserver<ConnectionReply> responseObserver) throws InvalidProtocolBufferException {

      //XML
      if (request.hasMsgXml()) {
        //DUARTE TRABALHA AQUI

      }
      //PROTO
      else {
        //JOAO TRABALHA AQUI

          //Deserialization (Protob -> Object)
          OwnerList lista_donos = OwnerList.parseFrom(request.getMsgPb());

          //Method that receives the list of owners and returns the list of cars per owner
          CarList retorna_carros = null;
          try {
              retorna_carros = devolve_carros(lista_donos);
          } catch (IOException e) {
              e.printStackTrace();
          }

          //Searilize the returned value of the method and send it as a reply to the client
          ByteString serial_reply = retorna_carros.toByteString();

          //Resposta que vai ser enviada para o cliente
        //ConnectionReply reply = ConnectionReply.newBuilder().setRpPb(request.getMsgPb()).build();
          ConnectionReply reply = ConnectionReply.newBuilder().setRpPb(serial_reply).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
      }
    }
  }

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


  //Method to receive the list of owners and return a list of their cars in response
  public static CarList devolve_carros(OwnerList owners) throws IOException {
    //Ver se é preciso separar por owner ou se pode ser todos em conjunto
    CarList.Builder retorna_garagem = CarList.newBuilder();

      //Ler do ficheiro de texto (inputStream)
    CarList cars_file = CarList.parseFrom(new FileInputStream("ficheiro1.txt"));

      //System.out.println("Carros: " + Arrays.deepToString(cars_file.getCarsList().toArray()));
      //System.out.println("Donos: " + Arrays.deepToString(owners.getOwnersList().toArray()));

        for (Car c: cars_file.getCarsList()){
            for (Owner o: owners.getOwnersList()){  //Procurar por id do owner
            if (c.getOwnerId() == o.getId()){
                System.out.println("Id do owner: " + o.getId() + " Id do owner do carro" + c.getOwnerId());
                retorna_garagem.addCars(c);
            }
        }
      }

      return retorna_garagem.build();
  }

  //Method to deserealize XML

  //Method to serealize XML
}
