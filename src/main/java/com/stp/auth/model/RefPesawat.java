package com.stp.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "refPesawat")
public class RefPesawat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long pesawatId;

	public String pesawatDesc;

	public Long getPesawatId() {
		return pesawatId;
	}

	public void setPesawatId(Long pesawatId) {
		this.pesawatId = pesawatId;
	}

	public String getPesawatDesc() {
		return pesawatDesc;
	}

	public void setPesawatDesc(String pesawatDesc) {
		this.pesawatDesc = pesawatDesc;
	}

}
