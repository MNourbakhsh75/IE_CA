package itemException;

public class ItemAlreadyExistsException extends Exception {
    public ItemAlreadyExistsException(String message){
        super(message);
    }
}
