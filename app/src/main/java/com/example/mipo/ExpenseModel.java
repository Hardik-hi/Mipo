package com.example.mipo;

public class ExpenseModel {
    private String date;
    private String payment_mode;
    private String person;
    private Double amount;
    private String remark;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExpenseModel(String date, String person, String payment_mode, Double amount, String remark) {
        this.date = date;
        this.amount = amount;
        this.remark = remark;
        this.payment_mode = payment_mode;
        this.person = person;
    }

    @Override
    public String toString(){
        return "TransactionModel{" +
                "date = " + date +
                ", details = " + person +
                ", payment_mode = " + payment_mode +
                ", remark = " + remark +
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
