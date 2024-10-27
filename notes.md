#### How to secure another service (ex: customer-service) with the security service that we just created?

Now we've created a security service that can handel the authentication for our app and take the charge of encoding and decoding jwt tokens </br>

---

So now if we want to create  another service that uses the auth server that we've created we should proceed as follows:
1) create the project (a new maven module)
2) dependencies:
- spring web
- oauth2ResourceServer
3) create our rest controller
4) create a security configuration class with the following beans: </br>
@Bean
</br>public SecurityFilterChain securityFilterChain(HttpSecurity http) throw Exception {
    </br>return http.csrf(csrf -> csrf.disable())
                </br>.authorizeRequests(auth -> auth.anyRequest().authenticated())
                </br>.oauth2ResourceServer(OAuthResourceServerConfigurer::jwt)
                </br>.build()
</br>}

</br>@Bean 
</br>public JwtDecoder jwtDecoder(){
    </br>return NimbusJwtDecoder.withPublicKey(rsaKeysConfig.publicKey()).build();
</br>}

### If you stuck with the implementation, go watch this part of this ytb video: https://youtu.be/7ey2b5nTFIk?si=-456WAVerHMPVsDi&t=4616
