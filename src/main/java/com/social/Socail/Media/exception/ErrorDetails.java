package com.social.Socail.Media.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetails {

    private String message;
    private String error;
    private LocalDateTime timestamps;
}
