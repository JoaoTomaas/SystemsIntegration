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

/*
import com.project.protob.CarOwnerProtoB.Owner;
import com.project.protob.CarOwnerProtoB.OwnerList;
import com.project.protob.CarOwnerProtoB.Car;
import com.project.protob.CarOwnerProtoB.CarList;
*/
import io.grpc.Server;


public class TheServer {

  static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void greetAndConnect (ConnectionRequest request, StreamObserver<ConnectionReply> responseObserver) throws InvalidProtocolBufferException {

      //XML
      if (request.hasMsgXml()) {//DUARTE TRABALHA AQUI
          long startTime = System.nanoTime(); //Inicia o cronómetro

          XmlToObject xmltoobject=new XmlToObject("teste1.xml");
          OwnerList owners=xmltoobject.work(request.getMsgXml());
        /*System.out.println("recebemos a lista de owners\n---------------------------");
        for(Owner e:owners.getOwner_list()){
          System.out.println(e.getName());
        }*/
          CarList cars=listOfCarsXml(owners);
        /*System.out.println("lista de caroos\n-----------------");
        for(Car e:cars.getCar_list()){
          System.out.println(e.getBrand());
        }*/
          ObjectToXml cenas=new ObjectToXml("teste2.xml",cars);

          long estimatedTime = System.nanoTime() - startTime;
          System.out.println("Tempo total XML(servidor): " + estimatedTime/1000);

          ConnectionReply reply=ConnectionReply.newBuilder().setRpXml(cenas.teste2()).build();
          responseObserver.onNext(reply);
          responseObserver.onCompleted();
      }
      //PROTO
      else {//JOAO TRABALHA AQUI
          long startTime = System.nanoTime(); //Inicia o cronómetro

          //Deserialization (Protob -> Object)
          com.project.protob.CarOwnerProtoB.OwnerList lista_donos = com.project.protob.CarOwnerProtoB.OwnerList.parseFrom(request.getMsgPb());
          //OwnerList lista_donos = OwnerList.parseFrom(request.getMsgPb());

          //Method that receives the list of owners and returns the list of cars per owner
          com.project.protob.CarOwnerProtoB.CarList retorna_carros = null;
          try {
              retorna_carros = devolve_carros(lista_donos);
          } catch (IOException e) {
              e.printStackTrace();
          }

          //Searilize the returned value of the method and send it as a reply to the client
          ByteString serial_reply = null;
          if (retorna_carros != null) {
              serial_reply = retorna_carros.toByteString();
          }
          long estimatedTime = System.nanoTime() - startTime;
          System.out.println("Tempo total PROTOCOL BUFFERS(servidor): " + estimatedTime/1000);

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
  public static CarList database;

  private void start() throws IOException {
      GetCars cenas=new GetCars("Car.txt");
      try {
          CarList teste=cenas.work();
          database=teste;
      } catch (IOException e) {
          e.printStackTrace();
      }

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
  public static com.project.protob.CarOwnerProtoB.CarList devolve_carros(com.project.protob.CarOwnerProtoB.OwnerList owners) throws IOException {
    //Ver se é preciso separar por owner ou se pode ser todos em conjunto
    com.project.protob.CarOwnerProtoB.CarList.Builder retorna_garagem = com.project.protob.CarOwnerProtoB.CarList.newBuilder();

      //Ler do ficheiro de texto (inputStream)
      com.project.protob.CarOwnerProtoB.CarList cars_file = com.project.protob.CarOwnerProtoB.CarList.parseFrom(new FileInputStream("output_cars_pb.txt"));

      //System.out.println("Carros: " + Arrays.deepToString(cars_file.getCarsList().toArray()));
      //System.out.println("Donos: " + Arrays.deepToString(owners.getOwnersList().toArray()));

      for (com.project.protob.CarOwnerProtoB.Owner o: owners.getOwnersList()){  //Procurar por id do owner
          for (com.project.protob.CarOwnerProtoB.Car c: cars_file.getCarsList()){
            if (c.getOwnerId() == o.getId()){
                System.out.println("Id do owner: " + o.getId() + " Id do owner do carro" + c.getOwnerId());
                retorna_garagem.addCars(c);
            }
        }
      }

      return retorna_garagem.build();
  }

    public static CarList listOfCarsXml(OwnerList listOfOwners){
        CarList devolve=new CarList();
        ArrayList<Car> aux=new ArrayList<>();
        int tamanho=listOfOwners.getOwner_list().size();
        for(Owner owner:listOfOwners.getOwner_list()){
            for(Car carro: database.getCar_list()){
                if(carro.getOwner_id()==owner.getId()){
                    aux.add(carro);
                }

            }
        }
        devolve.setCar_list(aux);
        return devolve;
    }
}
