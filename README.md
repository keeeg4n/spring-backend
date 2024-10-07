# SpringBoot and gRPC
## Project Setup
### Proto
1. First create a module named proto.
2. Dependencies:
   1. grpc-stub
   2. grpc-protobuf
3. Build
   1. Extenstions:
      1. os-maven-plugin
   2. Plugin
      1. Protobuf maven plugin
4. Create the schema.proto
```protobuf
syntax = "proto3";

package com.keeg4n;

option java_multiple_files = true;

// creates a class of Book
message Book {
  int32 book_id = 1;
  string title = 2;
  float price = 3;
  int32 pages = 4;
  int32 author_id = 5;
}

message Author {
  int32 author_id = 1;
  string first_name = 2;
  string last_name = 3;
  string gender = 4;
  int32 book_id = 5;
}

// defining the methods of grpc service
service BookAuthorService {
  rpc getAuthor(Author) returns (Author) {};
}
```
5. Then compile the application with a success message.

### gRPC Server
1. Create a new module named grpc-server, which will act like server for our case
2. Add grpc-server-starter-dependency and address proto module into it

12:34