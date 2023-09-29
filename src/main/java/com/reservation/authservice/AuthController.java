package com.reservation.authservice;
import com.reservation.authservice.auth.AuthRequest;
import com.reservation.authservice.auth.AuthResponse;
import com.reservation.authservice.exception.BusAuthException;
import com.reservation.authservice.model.Customer;
import com.reservation.authservice.repository.CustomerRepository;
import com.reservation.authservice.service.AuthService;
import com.reservation.authservice.util.BusResponse;
import com.reservation.authservice.util.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService service;

    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    @Autowired
    AuthController(AuthService service, AuthenticationManager authenticationManager,
                   CustomerRepository customerRepository) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customers/register")
    public ResponseEntity<BusResponse> registerCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok().body(service.registerCustomer(customer));
    }

    @PostMapping("/customers/login")
    public ResponseEntity<AuthResponse> loginCustomer(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            Customer customer = customerRepository.findByUserName(authRequest.getUserName()).orElse(null);
            return  ResponseEntity.ok().body(new AuthResponse(RequestStatus.SUCCESS,service.generateToken(authRequest.getUserName()),customer.getId()));
        } else {
            throw new BusAuthException("Invalid credentials");
        }
    }

    @GetMapping("/customers/validate")
    public ResponseEntity validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return ResponseEntity.ok().build();
    }
}
