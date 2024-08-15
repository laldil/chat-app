package kz.edu.astanait.dashboard.controller.api;

import lombok.Data;

@Data
public abstract class ApiResponse {
    private boolean success;
    private String msg = "OK";
}
