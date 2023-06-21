package com.example.firenew;

public class Note {
    public Note(String nama, String isi){
        this.isi=isi;
        this.Nama=nama;
    }
    public Note(String link, String nama, String isi){
        this.isi=isi;
        this.Nama=nama;
        this.imgUrl=link;
    }
    public Note(){}
private String Nama,isi,key,imgUrl;

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getNama() {
        return Nama;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getIsi() {
        return isi;
    }
}
