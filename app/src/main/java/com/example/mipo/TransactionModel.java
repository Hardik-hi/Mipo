package com.example.mipo;

public class TransactionModel {
    private int id;
    private String date;
    private String detail;
    private String payment_mode;
    private Double amount;
    private String remarks;

    public TransactionModel(int id, String date, String detail, String payment_mode, Double amount, String remarks) {
        this.id = id;
        this.date = date;
        this.detail = detail;
        this.payment_mode = payment_mode;
        this.amount = amount;
        this.remarks = remarks;
    }

    @Override
    public String toString(){
        return "TransactionModel{" +
                "date = " + date +
                ", details = " + detail +
                ", payment_mode = " + payment_mode +
                ", remarks = " + remarks +
                "}";
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
