package pl.mskreczko.restapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.mskreczko.restapi.user.User;
import pl.mskreczko.restapi.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AccessTokenAuthenticationSucessHandler implements AuthenticationSuccessHandler {

    private final AccessTokenProvider accessTokenProvider;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final User user = (User) authentication.getPrincipal();
        final String accessToken = accessTokenProvider.getAccessToken(
                userService.loadUserByUsername(user.getUsername()).toString(),
                user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );

        response.setHeader("access_token", accessToken);
    }
}
