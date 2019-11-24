package com.pku.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 返回接口统一包装类
 * Package : com.pku.common
 * 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvokeResult<T extends Object> {
    private Object data;
    private String errorMessage;
    private boolean hasErrors;

    public InvokeResult() {
        super();
    }

    public InvokeResult(T dto) {
        super();
        this.data = dto;
    }

    public InvokeResult(List<T> dtos) {
        super();
        this.data = dtos;
    }

    public void setData(T dto) {
        this.data = dto;
    }

    public InvokeResult<T> success() {
        this.hasErrors = false;
        return this;
    }

    public InvokeResult<T> failure(String message) {
        this.hasErrors = true;
        this.errorMessage = message;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean isHasErrors() {
        return this.hasErrors;
    }

    public boolean isSuccess() {
        return !this.hasErrors;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }
}

