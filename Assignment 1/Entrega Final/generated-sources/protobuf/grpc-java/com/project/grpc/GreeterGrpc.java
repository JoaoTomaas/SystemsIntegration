package com.project.grpc;

import com.google.protobuf.InvalidProtocolBufferException;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *Definir o serviço de greeting
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: grpcp.proto")
public final class GreeterGrpc {

  private GreeterGrpc() {}

  public static final String SERVICE_NAME = "Greeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.project.grpc.ConnectionRequest,
      com.project.grpc.ConnectionReply> getGreetAndConnectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GreetAndConnect",
      requestType = com.project.grpc.ConnectionRequest.class,
      responseType = com.project.grpc.ConnectionReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.project.grpc.ConnectionRequest,
      com.project.grpc.ConnectionReply> getGreetAndConnectMethod() {
    io.grpc.MethodDescriptor<com.project.grpc.ConnectionRequest, com.project.grpc.ConnectionReply> getGreetAndConnectMethod;
    if ((getGreetAndConnectMethod = GreeterGrpc.getGreetAndConnectMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getGreetAndConnectMethod = GreeterGrpc.getGreetAndConnectMethod) == null) {
          GreeterGrpc.getGreetAndConnectMethod = getGreetAndConnectMethod =
              io.grpc.MethodDescriptor.<com.project.grpc.ConnectionRequest, com.project.grpc.ConnectionReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GreetAndConnect"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.grpc.ConnectionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.project.grpc.ConnectionReply.getDefaultInstance()))
              .setSchemaDescriptor(new GreeterMethodDescriptorSupplier("GreetAndConnect"))
              .build();
        }
      }
    }
    return getGreetAndConnectMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterStub newStub(io.grpc.Channel channel) {
    return new GreeterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreeterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreeterFutureStub(channel);
  }

  /**
   * <pre>
   *Definir o serviço de greeting
   * </pre>
   */
  public static abstract class GreeterImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *Estabeler contacto
     * </pre>
     */
    public void greetAndConnect(com.project.grpc.ConnectionRequest request,
        io.grpc.stub.StreamObserver<com.project.grpc.ConnectionReply> responseObserver) throws InvalidProtocolBufferException {
      asyncUnimplementedUnaryCall(getGreetAndConnectMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGreetAndConnectMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.project.grpc.ConnectionRequest,
                com.project.grpc.ConnectionReply>(
                  this, METHODID_GREET_AND_CONNECT)))
          .build();
    }
  }

  /**
   * <pre>
   *Definir o serviço de greeting
   * </pre>
   */
  public static final class GreeterStub extends io.grpc.stub.AbstractStub<GreeterStub> {
    private GreeterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    /**
     * <pre>
     *Estabeler contacto
     * </pre>
     */
    public void greetAndConnect(com.project.grpc.ConnectionRequest request,
        io.grpc.stub.StreamObserver<com.project.grpc.ConnectionReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGreetAndConnectMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *Definir o serviço de greeting
   * </pre>
   */
  public static final class GreeterBlockingStub extends io.grpc.stub.AbstractStub<GreeterBlockingStub> {
    private GreeterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *Estabeler contacto
     * </pre>
     */
    public com.project.grpc.ConnectionReply greetAndConnect(com.project.grpc.ConnectionRequest request) {
      return blockingUnaryCall(
          getChannel(), getGreetAndConnectMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *Definir o serviço de greeting
   * </pre>
   */
  public static final class GreeterFutureStub extends io.grpc.stub.AbstractStub<GreeterFutureStub> {
    private GreeterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *Estabeler contacto
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.project.grpc.ConnectionReply> greetAndConnect(
        com.project.grpc.ConnectionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGreetAndConnectMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GREET_AND_CONNECT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GREET_AND_CONNECT:
          try {
            serviceImpl.greetAndConnect((ConnectionRequest) request,
                (io.grpc.stub.StreamObserver<ConnectionReply>) responseObserver);
          } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
          }
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreeterBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.project.grpc.GrpcProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Greeter");
    }
  }

  private static final class GreeterFileDescriptorSupplier
      extends GreeterBaseDescriptorSupplier {
    GreeterFileDescriptorSupplier() {}
  }

  private static final class GreeterMethodDescriptorSupplier
      extends GreeterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreeterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreeterFileDescriptorSupplier())
              .addMethod(getGreetAndConnectMethod())
              .build();
        }
      }
    }
    return result;
  }
}
