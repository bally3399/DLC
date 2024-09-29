package africa.semicolon.com.dlc.exceptions;

public class IncorrectPasswordException extends DlcExceptions {
    public IncorrectPasswordException(String invalidPasswordProvideAPassword) {
        super(invalidPasswordProvideAPassword);
    }
}
