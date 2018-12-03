package com.stp.auth.model;

public class BaranganTemp {
	
	private Long baranganId;
	
	private String bilangan;
	private String baranganDibawa;
	private String jumlah;
	private String anggaranBerat;
	private String catatan;
	private String total;

	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public String getAnggaranBerat() {
		return anggaranBerat;
	}
	public void setAnggaranBerat(String anggaranBerat) {
		this.anggaranBerat = anggaranBerat;
	}
	public String getJumlah() {
		return jumlah;
	}
	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}
	public String getBaranganDibawa() {
		return baranganDibawa;
	}
	public void setBaranganDibawa(String baranganDibawa) {
		this.baranganDibawa = baranganDibawa;
	}
	public String getBilangan() {
		return bilangan;
	}
	public void setBilangan(String bilangan) {
		this.bilangan = bilangan;
	}
	
	public Long getBaranganId() {
		return baranganId;
	}
	public void setBaranganId(Long baranganId) {
		this.baranganId = baranganId;
	}

}
