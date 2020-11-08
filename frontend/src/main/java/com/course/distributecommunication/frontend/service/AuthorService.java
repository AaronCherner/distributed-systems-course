package com.course.distributecommunication.frontend.service;

import com.course.distributecommunication.frontend.dto.AuthorDto;
import com.google.protobuf.Empty;
import com.tradeix.core.grpc.authors.AllAuthorsResponse;
import com.tradeix.core.grpc.authors.AuthorsServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @GrpcClient("authors")
    private AuthorsServiceGrpc.AuthorsServiceBlockingStub authorsClient;

    public List<AuthorDto> getAllAuthors() {
        AllAuthorsResponse authors = authorsClient.getAllAuthors(Empty.newBuilder().build());
        return authors.getAuthorsList().stream().map(author ->
                new AuthorDto(author.getId(), author.getFirstName(), author.getLastName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}