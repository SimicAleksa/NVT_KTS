package com.example.nvt_kts_back.DTOs;

public class ReportParams {
    private String start;
    private String end;
    private String email;

    public ReportParams() {

    }

    public ReportParams(String start, String end, String email) {
        this.start = start;
        this.end = end;
        this.email = email;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
