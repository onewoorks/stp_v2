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
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "pembelian")
public class Pembelian {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String caraBeli;
	private String hargaTiket;
	private String waran;
	private String hargaPengurangan;
	private String muatNaikTiket;
	private String alasan;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "penerbangan_penerbanganId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Penerbangan penerbangan;
	
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
	
	public Penerbangan getPenerbangan() {
		return penerbangan;
	}

	public void setPenerbangan(Penerbangan penerbangan) {
		this.penerbangan = penerbangan;
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

	public String getHargaTiket() {
		return hargaTiket;
	}

	public void setHargaTiket(String hargaTiket) {
		this.hargaTiket = hargaTiket;
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

	public String getMuatNaikTiket() {
		return muatNaikTiket;
	}

	public void setMuatNaikTiket(String muatNaikTiket) {
		this.muatNaikTiket = muatNaikTiket;
	}

	public String getAlasan() {
		return alasan;
	}

	public void setAlasan(String alasan) {
		this.alasan = alasan;
	}
	
	
//    @ManyToOne
//    @JoinColumn(name = "staff_no")
//    public Pengguna getPengguna() {
//        return pengguna;
//    }
//
//    public void setPengguna(Pengguna pengguna) {
//        this.pengguna = pengguna;
//    }


}
