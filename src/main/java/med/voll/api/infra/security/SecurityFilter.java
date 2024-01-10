package med.voll.api.infra.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);

        System.out.println(tokenJWT);

        filterChain.doFilter(request, response);// passar para o próximo filtro
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader =  request.getHeader("Authorization");// nome do cabeçalho, a aplicação frontend deve mandar o token gerado pelo backend via cabeçalho
        if(authorizationHeader != null){ // se não existir cabeçalho é devolvido nulo
            return authorizationHeader.replace("Bearer", "");
        }else {
            throw new RuntimeException("Token JWT não enviado no cabeçalho authorization");
        }
    }
}
