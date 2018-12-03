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
@Table(name = "refJawatan")
public class RefJawatan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jawatanId;
	
	private String jawatanDesc;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "refRole_Id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private RefRole refRole;
	
	public Long getJawatanId() {
		return jawatanId;
	}

	public void setJawatanId(Long jawatanId) {
		this.jawatanId = jawatanId;
	}

	public String getJawatanDesc() {
		return jawatanDesc;
	}

	public void setJawatanDesc(String jawatanDesc) {
		this.jawatanDesc = jawatanDesc;
	}
}
