package com.stp.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "refPeruntukan")
public class RefPeruntukan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long peruntukanId;

	public String peruntukanDesc;

	public Long getPeruntukanId() {
		return peruntukanId;
	}

	public void setPeruntukanId(Long peruntukanId) {
		this.peruntukanId = peruntukanId;
	}

	public String getPeruntukanDesc() {
		return peruntukanDesc;
	}

	public void setPeruntukanDesc(String peruntukanDesc) {
		this.peruntukanDesc = peruntukanDesc;
	}
	
}
