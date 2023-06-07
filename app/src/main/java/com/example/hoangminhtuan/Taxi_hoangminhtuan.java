package com.example.hoangminhtuan;

public class Taxi_hoangminhtuan {
    private int id;
    private String soXe;
    private double quangDuong;
    private int donGia;
    private int khuyenMai;
    public double tong(){
        return quangDuong*donGia*(1-(double)khuyenMai/100);
    }

    @Override
    public String toString() {
        return "Taxi_hoangminhtuan{" +
                "soXe='" + soXe + '\'' +
                ", quangDuong=" + quangDuong +
                ", donGia=" + donGia +
                ", khuyenMai=" + khuyenMai +
                '}';
    }

    public Taxi_hoangminhtuan() {
    }

    public String getSoXe() {
        return soXe;
    }

    public Taxi_hoangminhtuan(String soXe, double quangDuong, int donGia, int khuyenMai) {
        this.soXe = soXe;
        this.quangDuong = quangDuong;
        this.donGia = donGia;
        this.khuyenMai = khuyenMai;
    }

    public void setSoXe(String soXe) {
        this.soXe = soXe;
    }

    public double getQuangDuong() {
        return quangDuong;
    }

    public void setQuangDuong(double quangDuong) {
        this.quangDuong = quangDuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }
}
