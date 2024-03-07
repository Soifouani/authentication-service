package org.auth.authenticationservice.services.servicesImpl;

import lombok.AllArgsConstructor;
import org.auth.authenticationservice.dtos.ApiResponseDTO;
import org.auth.authenticationservice.dtos.AuthRequestDTO;
import org.auth.authenticationservice.entities.User;
import org.auth.authenticationservice.enums.ApiResponseStatus;
import org.auth.authenticationservice.exceptions.BadCredentialsException;
import org.auth.authenticationservice.exceptions.GrantTypeException;
import org.auth.authenticationservice.jwt.TokenProvider;
import org.auth.authenticationservice.repositories.UserRepository;
import org.auth.authenticationservice.services.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.auth.authenticationservice.constants.Api.*;
import static org.auth.authenticationservice.constants.MessageKey.ERROR_BAD_CREDENTIAL;
import static org.auth.authenticationservice.constants.MessageKey.SUCCESS_LOGIN;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtDecoder jwtDecoder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public ApiResponseDTO<Map<String, String>> authenticate(AuthRequestDTO authRequestDTO) {
        Map<String, String> idToken = new HashMap<>();
        String subject = authRequestDTO.getUsername();
        String grantType = authRequestDTO.getGrantType();

        if (grantType == null) {
            throw  new GrantTypeException(ERROR_BAD_CREDENTIAL);
        }

        if (grantType.equals(PASSWORD)) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(subject, authRequestDTO.getPassword())
            );
            subject = authentication.getName();
        } else if (grantType.equals(REFRESH_TOKEN)) {
            Jwt decodedRefreshToken = null;
            decodedRefreshToken = jwtDecoder.decode(authRequestDTO.getRefreshToken());
            subject = decodedRefreshToken.getClaim(USERNAME);
        } else {
            throw  new GrantTypeException(ERROR_BAD_CREDENTIAL);
        }
        User user = findUserByUsernameOrEmail(subject);

        var accessToken = tokenProvider.accessToken(user);
        idToken.put(ACCESS_TOKEN, accessToken);

        if(authRequestDTO.isWithRefreshToken()){
            var refreshToken = tokenProvider.refreshToken(user);
            idToken.put(REFRESH_TOKEN, refreshToken);
        }

        return ApiResponseDTO.<Map<String, String>>builder()
            .success(true)
            .status(ApiResponseStatus.SUCCESS)
            .message(SUCCESS_LOGIN)
            .data(idToken)
            .build();
    }
    private User findUserByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()-> new BadCredentialsException(ERROR_BAD_CREDENTIAL));
    }
}
