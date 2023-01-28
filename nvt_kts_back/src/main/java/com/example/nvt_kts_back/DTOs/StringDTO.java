package com.example.nvt_kts_back.DTOs;

public class StringDTO {
    private String value;

    private Long numberValue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Long numberValue) {
        this.numberValue = numberValue;
    }

    public StringDTO(String value) {
        this.value = value;
    }
    public StringDTO()
    {

    }

    public StringDTO(Long num) {
        this.numberValue = num;
    }

    public StringDTO(String value, Long numberValue) {
        this.value = value;
        this.numberValue = numberValue;
    }
}
