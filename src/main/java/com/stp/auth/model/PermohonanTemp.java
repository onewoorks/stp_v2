package com.stp.auth.model;

import org.springframework.web.multipart.MultipartFile;

public class PermohonanTemp {
	

	private Long id;
	private Long kp;

	private Boolean wakilPermohon;

	private String noBilBom;
	private MultipartFile muatNaikBom;
	private String nama;
	private String namaPelulus;
	private String namaPemohon;
	private String bahagian;
	private String emel;
	private String noTelefonBimbit;
	private String pembangunan;
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
	private String catatan;
	private String enrichNo;
	private MultipartFile muatNaikTiket;
	
	private Long penerbanganId;
	
	private String jenisPesawat;
	private String penerbangan;
	private String tarikhPergi;
	private String waktuBerlepas;
	private String waktuTiba;
	private String noPesawat;
	private String dariLokasi;
	private String destinasi;
	
	private Long baranganId;
	
	private String bilangan;
	private String baranganDibawa;
	private String jumlah;
	private String anggaranBerat;
	private String catatanBarangan;
	
	public Long getPenerbanganId() {
		return penerbanganId;
	}



	public void setPenerbanganId(Long penerbanganId) {
		this.penerbanganId = penerbanganId;
	}



	public String getJenisPesawat() {
		return jenisPesawat;
	}



	public void setJenisPesawat(String jenisPesawat) {
		this.jenisPesawat = jenisPesawat;
	}



	public String getPenerbangan() {
		return penerbangan;
	}



	public void setPenerbangan(String penerbangan) {
		this.penerbangan = penerbangan;
	}



	public String getTarikhPergi() {
		return tarikhPergi;
	}



	public void setTarikhPergi(String tarikhPergi) {
		this.tarikhPergi = tarikhPergi;
	}



	public String getWaktuBerlepas() {
		return waktuBerlepas;
	}



	public void setWaktuBerlepas(String waktuBerlepas) {
		this.waktuBerlepas = waktuBerlepas;
	}



	public String getWaktuTiba() {
		return waktuTiba;
	}



	public void setWaktuTiba(String waktuTiba) {
		this.waktuTiba = waktuTiba;
	}



	public String getNoPesawat() {
		return noPesawat;
	}



	public void setNoPesawat(String noPesawat) {
		this.noPesawat = noPesawat;
	}



	public String getDariLokasi() {
		return dariLokasi;
	}



	public void setDariLokasi(String dariLokasi) {
		this.dariLokasi = dariLokasi;
	}



	public String getDestinasi() {
		return destinasi;
	}



	public void setDestinasi(String destinasi) {
		this.destinasi = destinasi;
	}



	public Long getBaranganId() {
		return baranganId;
	}



	public void setBaranganId(Long baranganId) {
		this.baranganId = baranganId;
	}



	public String getBilangan() {
		return bilangan;
	}



	public void setBilangan(String bilangan) {
		this.bilangan = bilangan;
	}



	public String getBaranganDibawa() {
		return baranganDibawa;
	}



	public void setBaranganDibawa(String baranganDibawa) {
		this.baranganDibawa = baranganDibawa;
	}



	public String getJumlah() {
		return jumlah;
	}



	public void setJumlah(String jumlah) {
		this.jumlah = jumlah;
	}



	public String getAnggaranBerat() {
		return anggaranBerat;
	}



	public void setAnggaranBerat(String anggaranBerat) {
		this.anggaranBerat = anggaranBerat;
	}



	public String getCatatanBarangan() {
		return catatanBarangan;
	}



	public void setCatatanBarangan(String catatanBarangan) {
		this.catatanBarangan = catatanBarangan;
	}



	public String getTotal() {
		return total;
	}



	public void setTotal(String total) {
		this.total = total;
	}

	private String total;


	public String getStaffNo() {
		return staffNo;
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

	public String getTempatBertugas() {
		return tempatBertugas;
	}

	public void setTempatBertugas(String tempatBertugas) {
		this.tempatBertugas = tempatBertugas;
	}

	public MultipartFile getMuatNaikBom() {
		return muatNaikBom;
	}

	public void setMuatNaikBom(MultipartFile muatNaikBom) {
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



	public MultipartFile getMuatNaikTiket() {
		return muatNaikTiket;
	}



	public void setMuatNaikTiket(MultipartFile muatNaikTiket) {
		this.muatNaikTiket = muatNaikTiket;
	}


}