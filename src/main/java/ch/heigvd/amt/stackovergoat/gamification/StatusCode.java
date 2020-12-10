package ch.heigvd.amt.stackovergoat.gamification;

public enum StatusCode {
    OK(201),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404);

    private int code;
    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
