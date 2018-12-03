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
@Table(name = "refCawangan")
public class RefCawangan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long cawanganiId;
	
	public String cawanganDesc;

	public Long getCawanganiId() {
		return cawanganiId;
	}

	public void setCawanganiId(Long cawanganiId) {
		this.cawanganiId = cawanganiId;
	}

	public String getCawanganDesc() {
		return cawanganDesc;
	}

	public void setCawanganDesc(String cawanganDesc) {
		this.cawanganDesc = cawanganDesc;
	}

	
}
