package dev.mileski.smartstock.client;

import dev.mileski.smartstock.client.dto.AuthRequest;
import dev.mileski.smartstock.client.dto.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authClient", url = "${app.auth-url}")
public interface AuthClient {

    @PostMapping(path = "/api/token")
    ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request);

}

