package br.com.springproject.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.springproject.todolist.repository.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component // informa que é uma classe que deve ser gerenciada pelo spring
public class FilterTaskAuth extends OncePerRequestFilter {


    @Autowired
    private IUserRepository repository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var serveletPath = request.getServletPath();

        if (serveletPath.equals("/tasks/")) {

            // vai pegar a autenticaça
            var authorization = request.getHeader("Authorization");
            // pega o basic pelo seu tamanho e remove do base 64 jutamente com os espaços em branco
            var authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // vai validar o usuario

            var user = this.repository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                // validar a senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser",user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }

            }
        }else {
            filterChain.doFilter(request, response);
        }

    }
}

