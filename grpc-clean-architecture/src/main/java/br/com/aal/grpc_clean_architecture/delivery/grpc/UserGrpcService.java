package br.com.aal.grpc_clean_architecture.delivery.grpc;


import br.com.aal.grpc_clean_architecture.application.usecase.GetUserUseCase;
import br.com.aal.grpc_clean_architecture.domain.model.User;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import br.com.aal.grpc.*;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final GetUserUseCase useCase;

    public UserGrpcService(GetUserUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void getUser(UserRequest request,
                        StreamObserver<UserResponse> responseObserver) {

        User user = useCase.execute(request.getId());

        UserResponse response = UserResponse.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
