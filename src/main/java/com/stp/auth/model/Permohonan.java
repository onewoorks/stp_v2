package com.stp.auth.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "permohonan")
public class Permohonan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long kp;

	private Boolean wakilPermohon;

//	private Pengguna pengguna;
	private String noBilBom;
	private String muatNaikBom;
	private String nama;
	private String namaPelulus;
	private String namaPemohon;
	private String bahagian;
	private String emel;
	private String noTelefonBimbit;
	private String pembangunan;
	private String kelulusan;
	private String passport;
	private String tujuan;
	private String tempatBertugas;
	private String peruntukan;
	private String tarikhMohon;
	private String tarikhMula;
	private String tarikhTamat;
	private String alasan;
	private String staffNo;
	private String statusPermohonan;
//	private String penerbangan;
//	private String tarikhPergi;
//	private String waktuBerlepas;
//	private String waktuTiba;
//	private String noPesawat;
//	private String dariLokasi;
//	private String destinasi;
//	private String bilangan;
//	private String baranganDibawa;
//	private String jumlah;
//	private String anggaranBerat;
	private String catatan;
	private String enrichNo;
//	private String total;
	
	@OneToMany(mappedBy="permohonan", cascade = CascadeType.ALL)
	private Set<Penerbangan> penerbangan = new HashSet<>();
	
	@OneToMany(mappedBy="permohonan", cascade = CascadeType.ALL)
	private Set<Barangan> barangan = new HashSet<>();
	
	@OneToMany(mappedBy="permohonan", cascade = CascadeType.ALL)
	private Set<Pembelian> pembelian = new HashSet<>();
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "userid", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private User user;
	
//	public String getPenerbangan() {
//		return penerbangan;
//	}
//
//	public void setPenerbangan(String penerbangan) {
//		this.penerbangan = penerbangan;
//	}
//
//	public String getTarikhPergi() {
//		return tarikhPergi;
//	}
//
//	public void setTarikhPergi(String tarikhPergi) {
//		this.tarikhPergi = tarikhPergi;
//	}
//
//	public String getWaktuBerlepas() {
//		return waktuBerlepas;
//	}
//
//	public void setWaktuBerlepas(String waktuBerlepas) {
//		this.waktuBerlepas = waktuBerlepas;
//	}
//
//	public String getWaktuTiba() {
//		return waktuTiba;
//	}
//
//	public void setWaktuTiba(String waktuTiba) {
//		this.waktuTiba = waktuTiba;
//	}
//
//	public String getNoPesawat() {
//		return noPesawat;
//	}
//
//	public void setNoPesawat(String noPesawat) {
//		this.noPesawat = noPesawat;
//	}
//
//	public String getDariLokasi() {
//		return dariLokasi;
//	}
//
//	public void setDariLokasi(String dariLokasi) {
//		this.dariLokasi = dariLokasi;
//	}
//
//	public String getDestinasi() {
//		return destinasi;
//	}
//
//	public void setDestinasi(String destinasi) {
//		this.destinasi = destinasi;
//	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

//	public Pengguna getPengguna() {
//		return pengguna;
//	}
//
//	public void setPengguna(Pengguna pengguna) {
//		this.pengguna = pengguna;
//	}

	public Set<Pembelian> getPembelian() {
		return pembelian;
	}

	public void setPembelian(Set<Pembelian> pembelian) {
		this.pembelian = pembelian;
	}

	public Set<Barangan> getBarangan() {
		return barangan;
	}

	public void setBarangan(Set<Barangan> barangan) {
		this.barangan = barangan;
	}

	public String getStaffNo() {
		return staffNo;
	}
	
	public Set<Penerbangan> getPenerbangan() {
		return penerbangan;
	}

	public void setPenerbangan(Set<Penerbangan> penerbangan) {
		this.penerbangan = penerbangan;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getStatusPermohonan() {
		return statusPermohonan;
	}

	public void setStatusPermohonan(String statusPermohonan) {
		this.statusPermohonan = statusPermohonan;
	}

	public String getAlasan() {
		return alasan;
	}

	public void setAlasan(String alasan) {
		this.alasan = alasan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTarikhMohon() {
		return tarikhMohon;
	}

	public void setTarikhMohon(String tarikhMohon) {
		this.tarikhMohon = tarikhMohon;
	}

	public Boolean getWakilPermohon() {
		return wakilPermohon;
	}

	public void setWakilPermohon(Boolean wakilPermohon) {
		this.wakilPermohon = wakilPermohon;
	}

	public String getTarikhMula() {
		return tarikhMula;
	}

	public void setTarikhMula(String tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public String getTarikhTamat() {
		return tarikhTamat;
	}

	public void setTarikhTamat(String tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getTujuan() {
		return tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getPeruntukan() {
		return peruntukan;
	}

	public void setPeruntukan(String peruntukan) {
		this.peruntukan = peruntukan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNamaPelulus() {
		return namaPelulus;
	}

	public void setNamaPelulus(String namaPelulus) {
		this.namaPelulus = namaPelulus;
	}

	public String getNamaPemohon() {
		return namaPemohon;
	}

	public void setNamaPemohon(String namaPemohon) {
		this.namaPemohon = namaPemohon;
	}

	public Long getKp() {
		return kp;
	}

	public void setKp(Long kp) {
		this.kp = kp;
	}

	public String getBahagian() {
		return bahagian;
	}

	public void setBahagian(String bahagian) {
		this.bahagian = bahagian;
	}

	public String getEmel() {
		return emel;
	}

	public void setEmel(String emel) {
		this.emel = emel;
	}

	public String getNoTelefonBimbit() {
		return noTelefonBimbit;
	}

	public void setNoTelefonBimbit(String noTelefonBimbit) {
		this.noTelefonBimbit = noTelefonBimbit;
	}

	public String getPembangunan() {
		return pembangunan;
	}

	public void setPembangunan(String pembangunan) {
		this.pembangunan = pembangunan;
	}

	public String getNoBilBom() {
		return noBilBom;
	}

	public void setNoBilBom(String noBilBom) {
		this.noBilBom = noBilBom;
	}

	public String getKelulusan() {
		return kelulusan;
	}

	public void setKelulusan(String kelulusan) {
		this.kelulusan = kelulusan;
	}

	public String getTempatBertugas() {
		return tempatBertugas;
	}

	public void setTempatBertugas(String tempatBertugas) {
		this.tempatBertugas = tempatBertugas;
	}

//	public String getBilangan() {
//		return bilangan;
//	}
//
//	public void setBilangan(String bilangan) {
//		this.bilangan = bilangan;
//	}
//
//	public String getBaranganDibawa() {
//		return baranganDibawa;
//	}
//
//	public void setBaranganDibawa(String baranganDibawa) {
//		this.baranganDibawa = baranganDibawa;
//	}
//
//	public String getAnggaranBerat() {
//		return anggaranBerat;
//	}
//
//	public void setAnggaranBerat(String anggaranBerat) {
//		this.anggaranBerat = anggaranBerat;
//	}
//
//	public String getJumlah() {
//		return jumlah;
//	}
//
//	public void setJumlah(String jumlah) {
//		this.jumlah = jumlah;
//	}

	public String getMuatNaikBom() {
		return muatNaikBom;
	}

	public void setMuatNaikBom(String muatNaikBom) {
		this.muatNaikBom = muatNaikBom;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getEnrichNo() {
		return enrichNo;
	}

	public void setEnrichNo(String enrichNo) {
		this.enrichNo = enrichNo;
	}

//	public String getCatatan() {
//		return catatan;
//	}
//
//	public void setCatatan(String catatan) {
//		this.catatan = catatan;
//	}
//
//	public String getTotal() {
//		return total;
//	}
//
//	public void setTotal(String total) {
//		this.total = total;
//	}
	
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
