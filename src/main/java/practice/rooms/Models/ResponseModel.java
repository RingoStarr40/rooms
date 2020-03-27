package practice.rooms.Models;

public class ResponseModel {

    private int code;
    private String message;

    public ResponseModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
