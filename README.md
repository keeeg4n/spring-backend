# SpringBoot With JWT
All the projects involved are created while learning to implement features from [roadmap.sh/backend](http://roadmap.sh/backend).

## Implementation Steps
1. Add dependencies
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.5</version>
</dependency>
```
2. Implement User
   1. Create a User Entity and Extend it with the UserDetails class. The extended class will ask for additional methods
   which should be overridden.
   ```java
    public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String firstName;

        private String lastName;

        private String email;

        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(role.name()));
        }

        @Override
        public String getUsername() {
            return email;
        }
    }
   ```
   2. Create Role enum and repository for the User class.
#### References
[Complete JWT Authentication and Authorization in SpringBoot](https://youtu.be/qvAoUVXgpZg?feature=shared)