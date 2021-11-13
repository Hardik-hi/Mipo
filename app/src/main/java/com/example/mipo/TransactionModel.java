package com.example.mipo;

public class TransactionModel {
    private String date;
    private String payment_mode;
    private String detail;
    private Double amount;
    private String remark;

    public TransactionModel(String date, String detail, String payment_mode, Double amount, String remark) {
        this.date = date;
        this.amount = amount;
        this.remark = remark;
        this.payment_mode = payment_mode;
        this.detail = detail;
    }

    @Override
    public String toString(){
        return "TransactionModel{" +
                "date = " + date +
                ", details = " + detail +
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
