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
@Table(name = "destinasi")
public class Destinasi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long destinasiId;
	
	public String destinasi;

	public Long getDestinasiId() {
		return destinasiId;
	}

	public void setDestinasiId(Long destinasiId) {
		this.destinasiId = destinasiId;
	}

	public String getDestinasi() {
		return destinasi;
	}

	public void setDestinasi(String destinasi) {
		this.destinasi = destinasi;
	}
	
	
	

}
