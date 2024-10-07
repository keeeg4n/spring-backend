package dev.keeg4n.service;

import com.keeg4n.BookAuthorServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BookAuthorServerService extends BookAuthorServiceGrpc.BookAuthorServiceImplBase {

}
