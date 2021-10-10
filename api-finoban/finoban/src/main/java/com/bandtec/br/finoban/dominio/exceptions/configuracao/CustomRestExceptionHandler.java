package com.bandtec.br.finoban.dominio.exceptions.configuracao;

import com.bandtec.br.finoban.dominio.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AcessoNaoEncontradoException.class)
    public final ResponseEntity<Object> handleAcessoNaoEncontradoException(Exception ex, WebRequest request) {
        AcessoNaoEncontradoException exception = new AcessoNaoEncontradoException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AvaliacaoNaoEncontradaException.class)
    public final ResponseEntity<Object> handleAvaliacaoNaoEncontradaException(Exception ex, WebRequest request) {
        AvaliacaoNaoEncontradaException exception = new AvaliacaoNaoEncontradaException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AvalPositivoIncorretoException.class)
    public final ResponseEntity<Object> handleAvalPositivoIncorretoException(Exception ex, WebRequest request) {
        AvalPositivoIncorretoException exception = new AvalPositivoIncorretoException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public final ResponseEntity<Object> handleClienteNaoEncontradoException(Exception ex, WebRequest request) {
        ClienteNaoEncontradoException exception = new ClienteNaoEncontradoException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailNaoEncontradoException.class)
    public final ResponseEntity<Object> handleEmailNaoEncontradoException(Exception ex, WebRequest request) {
        EmailNaoEncontradoException exception = new EmailNaoEncontradoException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FalhaAoEnviarEmailException.class)
    public final ResponseEntity<Object> handleEnviarEmailException(Exception ex, WebRequest request) {
        FalhaAoEnviarEmailException exception = new FalhaAoEnviarEmailException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeedbackNullException.class)
    public final ResponseEntity<Object> handleFeedbackNullException(Exception ex, WebRequest request) {
        FeedbackNullException exception = new FeedbackNullException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegiaoNaoEncontradaException.class)
    public final ResponseEntity<Object> handleRegiaoNaoEncontradaException(Exception ex, WebRequest request) {
        RegiaoNaoEncontradaException exception = new RegiaoNaoEncontradaException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SenhaIncorretaException.class)
    public final ResponseEntity<Object> handleSenhaIncorretaException(Exception ex, WebRequest request) {
        SenhaIncorretaException exception = new SenhaIncorretaException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenExpiradoException.class)
    public final ResponseEntity<Object> handleTokenExpiradoException(Exception ex, WebRequest request) {
        TokenExpiradoException exception = new TokenExpiradoException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenInvalidoException.class)
    public final ResponseEntity<Object> handleTokenInvalidoException(Exception ex, WebRequest request) {
        TokenInvalidoException exception = new TokenInvalidoException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    public final ResponseEntity<Object> handleUsuarioJaCadastradoException(Exception ex, WebRequest request) {
        UsuarioJaCadastradoException exception = new UsuarioJaCadastradoException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioLogadoException.class)
    public final ResponseEntity<Object> handleUsuarioLogadoException(Exception ex, WebRequest request) {
        UsuarioLogadoException exception = new UsuarioLogadoException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioNaoLogadoException.class)
    public final ResponseEntity<Object> handleUsuarioNaoLogadoException(Exception ex, WebRequest request) {
        UsuarioNaoLogadoException exception = new UsuarioNaoLogadoException();
        ApiError apiError = new ApiError(exception.getCode(), exception.getMessage());
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PermissaoNaoEncontradaException.class)
    public final ResponseEntity<Object> handlePermissaoNaoEncontradaException() {
        PermissaoNaoEncontradaException permissaoNaoEncontradaException = new PermissaoNaoEncontradaException();
        return new ResponseEntity(new ApiError(permissaoNaoEncontradaException.getCode(),
                permissaoNaoEncontradaException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AdminNaoEncontradoException.class)
    public final ResponseEntity<Object> handleAdminNaoEncontradoException() {
        AdminNaoEncontradoException adminNaoEncontradoException = new AdminNaoEncontradoException();
        return new ResponseEntity(new ApiError(adminNaoEncontradoException.getCode(),
                adminNaoEncontradoException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CargoInexistenteException.class)
    public final ResponseEntity<Object> handleCargoNaoExistenteException() {
        CargoInexistenteException cargoInexistenteException = new CargoInexistenteException();
        return new ResponseEntity(new ApiError(cargoInexistenteException.getCode(),
                cargoInexistenteException.getMessage()), HttpStatus.NOT_FOUND);
    }
}
