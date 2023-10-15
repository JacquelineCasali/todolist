package br.com.springboot.todolist.filtroseguranca;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.springboot.todolist.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{


        System.out.println(request.getAttribute("idUser"));

        var servletPath = request.getServletPath();
//        System.out.println("PATH" + servletPath);
        if (servletPath.startsWith("tarefa")) {

// Pegar a autenticação (usuario e senha)
            var authorization = request.getHeader("Authorization");
            // pegando a autenticação (usuario e senha)
            var authEncoded = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

//validar usuario
            var user =this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401, "usuário sem autorização");
            } else {
//validar senha
                var verificacao = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (verificacao.verified) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        }else{
            filterChain.doFilter(request, response);
        }


}}






