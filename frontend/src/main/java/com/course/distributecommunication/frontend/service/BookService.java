package com.course.distributecommunication.frontend.service;

import com.course.distributecommunication.frontend.dto.BookDto;
import com.google.protobuf.Empty;
import com.tradeix.core.grpc.books.AllBooksResponse;
import com.tradeix.core.grpc.books.BooksServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @GrpcClient("books")
    private BooksServiceGrpc.BooksServiceBlockingStub booksClient;

    public List<BookDto> getAllBooks() {
        AllBooksResponse authors = booksClient.getAllBooks(Empty.newBuilder().build());
        return authors.getBooksList().stream().map(book ->
                new BookDto(book.getId(), book.getTitle(), book.getPages(), book.getAuthorId()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}