package africa.semicolon.com.dlc.exceptions;

public class UserAlreadyExistException extends DlcExceptions {
    public UserAlreadyExistException(String emailAlreadyExist) {
        super(emailAlreadyExist);
    }
}
