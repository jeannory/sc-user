package software.craftsmanship.scuser.exceptions;

public class CustomTransactionalException extends RuntimeException {

    public CustomTransactionalException() {
    }

    public CustomTransactionalException(String message) {
        super(message);
    }
}
