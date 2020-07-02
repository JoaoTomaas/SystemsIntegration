import com.project.grpc.GrpcProto.*;
import com.project.grpc.*;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

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


  //Write Message (ProtoBuffers)
  public void connect (String name) {
    screenmsg.info ("Trying to connect to " + name + "...");
    ConnectionRequest request = ConnectionRequest.newBuilder().setName(name).build();
    ConnectionReply response;
    try{
      response = blockingStub.greetAndConnect(request);
    } catch (StatusRuntimeException e) {
      screenmsg.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }

    screenmsg.info("Greeting: " + response.getMessage());
  }

  public static void main(String[] args) throws Exception {
    Client client = new Client("localhost", 9000);

    try {
      String user = "neste";
      if (args.length > 0){
        user = args[0];
      }
      client.connect(user);
    } finally {
      client.shutdown();
    }

  }


}
