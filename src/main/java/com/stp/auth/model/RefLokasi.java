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
@Table(name = "refLokasi")
public class RefLokasi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long lokasiId;

	public String lokasiDesc;
	
	public String lokasiShortName;

	public Long getLokasiId() {
		return lokasiId;
	}

	public void setLokasiId(Long lokasiId) {
		this.lokasiId = lokasiId;
	}

	public String getLokasiDesc() {
		return lokasiDesc;
	}

	public void setLokasiDesc(String lokasiDesc) {
		this.lokasiDesc = lokasiDesc;
	}

	public String getLokasiShortName() {
		return lokasiShortName;
	}

	public void setLokasiShortName(String lokasiShortName) {
		this.lokasiShortName = lokasiShortName;
	}
	

	
}
