/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Sachindra Rodrigo
 */
public class Inquiry {
    String inquiryId, userId, phone ,date, message, status;

    public Inquiry(String inquiryId, String userId, String phone, String date, String message, String status) {
        this.inquiryId = inquiryId;
        this.userId = userId;
        this.phone = phone;
        this.date = date;
        this.message = message;
        this.status = status;
    }

    public Inquiry(String userId, String phone, String date, String message, String status) {
        this.userId = userId;
        this.phone = phone;
        this.date = date;
        this.message = message;
        this.status = status;
    }
    

    public String getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
