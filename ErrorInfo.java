package io.bytesbank.registration.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data @Builder
public class ErrorInfo {
    private String message;
    private int errorCode;
    private Date time;
}
