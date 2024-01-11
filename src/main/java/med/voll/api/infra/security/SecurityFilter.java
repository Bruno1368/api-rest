package med.voll.api.infra.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = recuperarToken(request);
        if(tokenJWT != null){// ta vindo o token
            var subject = tokenService.getSubject(tokenJWT);// se checou, valida, se está válida, pegar o subject, o login(email) entao:
            var usuario = repository.findByLogin(subject); // encontra o objeto usuario pelo login, subject, com o repositorio

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); // dto do spring
            SecurityContextHolder.getContext().setAuthentication(authentication); // força  autenticação
        }



        filterChain.doFilter(request, response);// passar para o próximo filtro
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader =  request.getHeader("Authorization");// nome do cabeçalho, a aplicação frontend deve mandar o token gerado pelo backend via cabeçalho

        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer", "").trim();
        }
        return null;
    }
}
