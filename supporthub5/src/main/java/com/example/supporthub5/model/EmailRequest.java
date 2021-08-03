package com.example.supporthub5.model;

import lombok.Data;

@Data
public class EmailRequest {

    private String from;
    private String message;
    private String subject;


    @Override
    public String toString() {
        return "EmailRequest:{" +
                "from=' " + from + '\'' +
                ", subject= ' " + subject + '\'' +
                ", message='" + message + '\'' +
                 '}';
    }
}
