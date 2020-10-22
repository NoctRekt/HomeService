package com.example.XYZ.homeservice;



public class Customer {
    String post;
    String customerName;
   String customerLastName;
    String phone;
    String address;
    String desc;
    String type;
    String customerId;
/**/
    public Customer(String post_id,String customerId, String customerName ,String customerLastName, String phone, String address, String desc, String type) {
        this.post=post_id;
        this.customerName = customerName;
     this.customerLastName = customerLastName;
        this.phone = phone;
        this.address = address;
       this.desc = desc;
        this.type = type;
        this.customerId = customerId;
    }

    public Customer() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


     public String getCustomerLastName() {
        return customerLastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setType(String type) {
        this.type = type;
    }

}
