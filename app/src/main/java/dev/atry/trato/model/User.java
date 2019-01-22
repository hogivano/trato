package dev.atry.trato.model;

public class User {
    private String id;
    private String nama;
    private String picture;
    private String email;
    private String password;
    private int umur;
    private String created_at;

    public User(){
        this.id = "";
        this.nama = "";
        this.picture = "";
        this.email = "";
        this.password = "";
        this.umur = 0;
        this.created_at = "";
    }

    public int getUmur() {
        return umur;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPicture() {
        return picture;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
