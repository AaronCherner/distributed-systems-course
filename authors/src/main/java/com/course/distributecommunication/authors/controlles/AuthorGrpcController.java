package com.course.distributecommunication.authors.controlles;

import com.course.distributecommunication.authors.services.AuthorService;
import com.google.protobuf.Empty;
import com.tradeix.core.grpc.authors.AllAuthorsResponse;
import com.tradeix.core.grpc.authors.AuthorDto;
import com.tradeix.core.grpc.authors.AuthorsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.Collectors;

@GrpcService
public class AuthorGrpcController extends AuthorsServiceGrpc.AuthorsServiceImplBase {
    AuthorService authorService;

    @Autowired
    public AuthorGrpcController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void getAllAuthors(Empty request, StreamObserver<AllAuthorsResponse> responseObserver) {
        ArrayList<AuthorDto> authors = authorService.getAuthors().stream().map(author ->
                AuthorDto.newBuilder()
                        .setId(author.getId())
                        .setFirstName(author.getFirstName())
                        .setLastName(author.getLastName())
                        .build()
        ).collect(Collectors.toCollection(ArrayList::new));

        AllAuthorsResponse response = AllAuthorsResponse.newBuilder().addAllAuthors(authors).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}