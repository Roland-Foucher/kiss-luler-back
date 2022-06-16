// package co.simplon.alt3.kisslulerback.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import co.simplon.alt3.kisslulerback.repo.UserRepo;

// @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
// @RestController
// @RequestMapping("/auth")
// public class AuthController {

// 	@Autowired
// 	AuthenticationManager authenticationManager;

// 	@Autowired
// 	UserRepo userRepository;

// 	@Autowired
// 	PasswordEncoder encoder;

// 	@Autowired
// 	JwtProvider jwtProvider;

// 	@PostMapping("/signin")
// 	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

// 		Authentication authentication = authenticationManager.authenticate(
// 				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

// 		SecurityContextHolder.getContext().setAuthentication(authentication);

// 		String jwt = jwtProvider.generateJwtToken(authentication);
// 		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

// 		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
// 	}
	
// }

