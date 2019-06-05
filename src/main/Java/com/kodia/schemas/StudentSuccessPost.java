package com.kodia.schemas;

public class StudentSuccessPost {
    private int id;
    private String status;
    private String message;

    public StudentSuccessPost() {
    }

    public StudentSuccessPost(int id, String status, String message) {
        this.id = id;
        this.status = status;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
