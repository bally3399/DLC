package africa.semicolon.com.dlc.exceptions;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException(String invalidUsernameOrPassword) {
        super(invalidUsernameOrPassword);
    }
}
