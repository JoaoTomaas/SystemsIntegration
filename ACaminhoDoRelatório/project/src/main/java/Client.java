import com.google.protobuf.InvalidProtocolBufferException;
import com.project.grpc.GrpcProto.*;
import com.project.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


//Imports paa testar as cenas dos Protocol Buffers
import com.project.protob.CarOwnerProtoB.CarList;
import com.project.protob.CarOwnerProtoB.Car;
import com.project.protob.CarOwnerProtoB.OwnerList;
import com.project.protob.CarOwnerProtoB.Owner;
import java.io.*;

public class Client {

  //Constantes para o if da main
  private static final int XML_CONST = 1;
  private static final int PROTO_CONST = 2;

  static Owner AddOwner () { //Ids dos owners adicionados até ao momento: 244 e 200
    Owner.Builder owner = Owner.newBuilder();

    owner.setId(244);
    owner.setName("João Tomás");
    owner.setTelephone(912543678);
    owner.setAddress("Eu nasci aqui");

    return owner.build();
  }


  private static final Logger screenmsg = Logger.getLogger(Client.class.getName()); //Find or create a name for a logged subsystem

  private final ManagedChannel channel;

  private final GreeterGrpc.GreeterBlockingStub blockingStub;

  public Client (String host, int port){
    this(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()); //Creates a channel with the target's address and port number
  }

  //Ver se é preciso criar dois tipos de stub ou se basta um
  public Client(ManagedChannel channel){
    this.channel = channel;
    blockingStub = GreeterGrpc.newBlockingStub(channel);

    //As stub classes são a API que o client usa para fazer rpc calls no service endpoint
    //Vou usar uma blockingStub porque é síncrona e vai esperar que a chamada rpc invocada não retorna enquanto não retornar uma resposta ou uma excepção
    //A classe ClientGrpc foi gerada a partir do .proto
  }

  public void shutdown() throws InterruptedException{
    channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
  }


  public void connectProto() throws IOException {
    OwnerList.Builder owners = OwnerList.newBuilder();

    //Ler de um ficheiro de texto
    try{
      owners.mergeFrom(new FileInputStream("input_owner_pb.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("input_owner_pb.txt " + "não encontrado, a criar um novo...");
    }

    //Adicionar um dono à lista de donos (NÃO APAGAR)
    //owners.addOwners(AddOwner());

    //Guardar num ficheiro de texto (NÃO APAGAR)
    //FileOutputStream output = new FileOutputStream("input_owner_pb.txt");
    //owners.build().writeTo(output);
    //output.close();


    screenmsg.info("Conectando ao servidor...");
    ConnectionRequest req = ConnectionRequest.newBuilder().setMsgPb(owners.build().toByteString()).build();
    ConnectionReply resp;

    try{
      resp = blockingStub.greetAndConnect(req);
    } catch (StatusRuntimeException e) {
      screenmsg.log(Level.WARNING, "RPC nao funfou: {0}", e.getStatus());
      return;
    }

    CarList lista = CarList.parseFrom(resp.getRpPb());

    screenmsg.info ("Resposta: " + Arrays.deepToString(lista.getCarsList().toArray()));
  }



  //Write Message (ProtoBuffers)
  public void connect (String name) {
    screenmsg.info ("Trying to connect to " + name + "...");
    ConnectionRequest request = ConnectionRequest.newBuilder().setMsgXml(name).build();
    ConnectionReply response;
    try{
      response = blockingStub.greetAndConnect(request);
    } catch (StatusRuntimeException e) {
      screenmsg.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }

    screenmsg.info("Greeting: " + response.getRpXml());
  }

  public static void main(String[] args) throws Exception {
    Client client = new Client("localhost", 9000);

    int flag = -1; //XML_CONST //PROTO_CONST

    try {
      String user = "neste";
      /*if (args.length > 0){
        user = args[0];
      }*/

      if (flag == XML_CONST){ //XML
        //client.connect(user);
      }
      else { //PROTO
        client.connectProto();
      }

    } finally {
      client.shutdown();
    }

  }

}
