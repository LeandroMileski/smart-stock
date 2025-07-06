package dev.mileski.smartstock.service;

import dev.mileski.smartstock.client.AuthClient;
import dev.mileski.smartstock.client.dto.AuthRequest;
import dev.mileski.smartstock.config.AppConfig;
import dev.mileski.smartstock.exception.SmartStockException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private static final String GRANT_TYPE = "client_credentials";
    private static String token;
    private static LocalDateTime expiresIn;

    private final AuthClient authClient;
    private final AppConfig appConfig;

    public AuthService(AuthClient authClient, AppConfig appConfig) {
        this.authClient = authClient;
        this.appConfig = appConfig;
    }

    public String getToken(){
        if(token==null){
            generateToken();
        } else if (expiresIn.isBefore(LocalDateTime.now())){
            generateToken();
        }
    return token;
    }

    private void generateToken() {
        var request = new AuthRequest(
                GRANT_TYPE,
                appConfig.getClientId(),
                appConfig.getClientSecret()
        );

        var response = authClient.authenticate(request);

        if ( response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            token = response.getBody().accessToken();
            expiresIn = LocalDateTime.now().plusSeconds(response.getBody().expiresIn());
        } else {
            // Handle error case, e.g., throw an exception or log an error
            throw new SmartStockException("Failed to authenticate and retrieve token," +
                    "status code: " + response.getStatusCode() +
                    ", body: " + (response.getBody() != null ? response.getBody().toString() : "null"));
        }
    }

}
