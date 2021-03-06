package com.example.mipo;

public class ExpenseModel {
    private int id;
    private String date;
    private String payment_mode;
    private String person;
    private Double amount;
    private String remarks;

    public ExpenseModel(String date, String person, String payment_mode, Double amount, String remarks) {
        this.date = date;
        this.person = person;
        this.payment_mode = payment_mode;
        this.amount = amount;
        this.remarks = remarks;
    }

    public ExpenseModel(int id, String date, String person, String payment_mode, Double amount, String remarks) {
        this.id = id;
        this.date = date;
        this.person = person;
        this.payment_mode = payment_mode;
        this.amount = amount;
        this.remarks = remarks;
    }

    @Override
    public String toString(){
        return "ExpenseModel{" +
                "date = " + date +
                ", details = " + person +
                ", payment_mode = " + payment_mode +
                ", remarks = " + remarks +
                "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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
}
