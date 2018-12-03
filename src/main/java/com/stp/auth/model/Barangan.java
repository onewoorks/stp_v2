package com.stp.auth.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Barangan")
public class Barangan {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long baranganId;
	
	private String bilangan;
	private String baranganDibawa;
	private String jumlah;
	private String anggaranBerat;
	private String catatan;
	private String total;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "permohonan_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Permohonan permohonan;
	
	
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	
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