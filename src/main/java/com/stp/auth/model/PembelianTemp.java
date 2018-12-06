package com.stp.auth.model;

import org.springframework.web.multipart.MultipartFile;

public class PembelianTemp {
	
	private Long id;
	private String caraBeli;
	private String hargaTiket;
	private String waran;
	private String hargaPengurangan;
	private MultipartFile muatNaikTiket;
	private String alasan;
	private Penerbangan penerbangan;
	private Permohonan permohonan;
	
	public String getHargaTiket() {
		return hargaTiket;
	}
	public void setHargaTiket(String hargaTiket) {
		this.hargaTiket = hargaTiket;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCaraBeli() {
		return caraBeli;
	}
	public void setCaraBeli(String caraBeli) {
		this.caraBeli = caraBeli;
	}
	public String getWaran() {
		return waran;
	}
	public void setWaran(String waran) {
		this.waran = waran;
	}
	public String getHargaPengurangan() {
		return hargaPengurangan;
	}
	public void setHargaPengurangan(String hargaPengurangan) {
		this.hargaPengurangan = hargaPengurangan;
	}
	public MultipartFile getMuatNaikTiket() {
		return muatNaikTiket;
	}
	public void setMuatNaikTiket(MultipartFile muatNaikTiket) {
		this.muatNaikTiket = muatNaikTiket;
	}
	public String getAlasan() {
		return alasan;
	}
	public void setAlasan(String alasan) {
		this.alasan = alasan;
	}
	public Penerbangan getPenerbangan() {
		return penerbangan;
	}
	public void setPenerbangan(Penerbangan penerbangan) {
		this.penerbangan = penerbangan;
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	
}
