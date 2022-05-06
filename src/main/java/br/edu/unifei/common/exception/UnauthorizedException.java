package br.edu.unifei.common.exception;

public class UnauthorizedException extends BusinessRuleException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
