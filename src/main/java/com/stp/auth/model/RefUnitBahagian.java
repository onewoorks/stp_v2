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
@Table(name = "refUnitBahagian")
public class RefUnitBahagian {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long unitBahagianiId;
	
	public String unitBahagianDesc;
	
	public Long getUnitBahagianiId() {
		return unitBahagianiId;
	}

	public void setUnitBahagianiId(Long unitBahagianiId) {
		this.unitBahagianiId = unitBahagianiId;
	}

	public String getUnitBahagianDesc() {
		return unitBahagianDesc;
	}

	public void setUnitBahagianDesc(String unitBahagianDesc) {
		this.unitBahagianDesc = unitBahagianDesc;
	}

}
