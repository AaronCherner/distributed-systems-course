package com.course.distributecommunication.books.controlles;

import com.course.distributecommunication.books.services.BookService;
import com.google.protobuf.Empty;
import com.tradeix.core.grpc.books.AllBooksResponse;
import com.tradeix.core.grpc.books.BookDto;
import com.tradeix.core.grpc.books.BooksServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.Collectors;

@GrpcService
public class BookGrpcController extends BooksServiceGrpc.BooksServiceImplBase {
    BookService bookService;

    @Autowired
    public BookGrpcController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void getAllBooks(Empty request, StreamObserver<AllBooksResponse> responseObserver) {
        ArrayList<BookDto> books = bookService.getBooks().stream().map(book ->
                BookDto.newBuilder()
                        .setId(book.getId())
                        .setTitle(book.getTitle())
                        .setPages(book.getPages())
                        .setAuthorId(book.getAuthorId())
                        .build()
        ).collect(Collectors.toCollection(ArrayList::new));

        AllBooksResponse response = AllBooksResponse.newBuilder().addAllBooks(books).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}