package com.example.drama_kill_system.Exception;

/**
 * @author: luhe
 * @date: 2022/1/17 23:13
 * @description:
 */
public class AppException extends RuntimeException {
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
