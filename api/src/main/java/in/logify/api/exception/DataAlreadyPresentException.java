package in.logify.api.exception;

public class DataAlreadyPresentException extends Throwable {
    private String message;
    public DataAlreadyPresentException(String message) {
        super(message);
        this.message = message;
    }
}
