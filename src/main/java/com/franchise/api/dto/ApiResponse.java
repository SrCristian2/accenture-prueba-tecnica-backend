package com.franchise.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private String state;
    private T data;
    
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, "success", data);
    }
    
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message, "error", null);
    }
}
