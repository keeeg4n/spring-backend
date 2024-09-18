# SpringBoot with GraphQL
This README features how to use GraphQL with SpringBoot.

#### Use Case
One of the use cases of GraphQL is when different clients need different fields from the API.

## Steps to get started with GraphQL and SpringBoot
1. Add the dependency to the `POM.xml`
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-graphql</artifactId>
</dependency>

<!-- Test Dependency -->
<dependency>
    <groupId>org.springframework.graphql</groupId>
    <artifactId>spring-graphql-test</artifactId>
    <scope>test</scope>
</dependency>
```

2. In the resources tab we can see there is a graphql folder created for us where we will
be creating the schema for our GraphQL.

3. Create a file called `schema.graphqls`, this file will contain our schema.

4. Different types have to be created for getting different data from GraphQL. 
    1. type Response or Request
    ```graphql
      type Player {
      id: ID
      name: String
   }
   ```
   2. type Query
      1. type Query is used for sharing the data from API to Client.
         ```graphql
             type Query {
              findAll: Player
                 findOne(id: ID): Player
             }
         ```
      2. Now these functions need to be implemented in the Controllers of SpringBoot as such:
          ```java
          import org.springframework.stereotype.Controller;
          import org.springframework.graphql.data.method.annotation.MutationMapping;

          @Controller
          public class PlayerController {
                   
              private final PlayerService playerService;

              public PlayerController(PlayerService playerService) {
                  this.playerService = playerService;
              }
    
              @QueryMapping
              public List<Player> findAll() {
                  return playerService.findAll();
              }
         }
          ```
         3. We use `@Argument` to take argument values from GraphQL. Implemetation:
         ```java 
         import org.springframework.stereotype.Controller;
         import org.springframework.graphql.data.method.annotation.MutationMapping;

         @Controller
         public class PlayerController {
                   
             private final PlayerService playerService;

             public PlayerController(PlayerService playerService) {
                 this.playerService = playerService;
             }
    
             @QueryMapping
             public Player findOne(@Argument Long id) {
                 return playerService.findOne(id);
             }
         }
         ```
   3. type Mutation
      1. type Mutation is used for making any changes to the data i.e. Create, Update, Delete
      ```graphql
      type Mutation {
          createPlayer(name: String, team: Team): Player
          updatePlayer(id: ID, name: String, team: Team): Player
          deletePlayer(id: ID): Player
      }
      ```
      2. Implementation would be as follows:
      ```java
      import org.springframework.graphql.data.method.annotation.Argument;
      import org.springframework.stereotype.Controller;
      import org.springframework.graphql.data.method.annotation.MutationMapping;
            
      @Controller
      public class PlayerController {
          private final PlayerService playerService;
            
          public PlayerController(PlayerService playerService) {
              this.playerService = playerService;
          }
            
          @MutationMapping
          public Player createPlayer(@Argument String name, @Argument Team team) {
              return playerService.create(name, team);
          }
      }
      ```
          
## Some configurations for GraphQL with SpringBoot
GraphQL has a play ground within the SpringBoot application.
```yaml
spring:
  graphiql:
    enabled: true
    path: /graphiql
```

#### References
[Master GraphQL with SpringBoot](https://youtu.be/eD-1KTK7fGc?feature=shared)

#### Further
[Spring Boot GraphQL - How to secure your GraphQL APIs in Java](https://youtu.be/PkhsQPPFgOo?feature=shared)