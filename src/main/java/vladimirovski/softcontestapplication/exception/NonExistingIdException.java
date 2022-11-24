package vladimirovski.softcontestapplication.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NonExistingIdException extends MyException{

    private Object entity;

    public NonExistingIdException(String message, Object entity) {
        super(message);
        this.entity = entity;
    }

    public NonExistingIdException(String message){
        super(message);
    }

    public Object getEntity() {
        return entity;
    }
}
