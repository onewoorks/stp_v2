package com.stp.auth.model;

import java.util.HashSet;
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
@Table(name = "refJawatan")
public class RefJawatan {

	public RefRole getRefRole() {
		return refRole;
	}

	public void setRefRole(RefRole refRole) {
		this.refRole = refRole;
	}

	public Set<Pengguna> getPengguna() {
		return pengguna;
	}

	public void setPengguna(Set<Pengguna> pengguna) {
		this.pengguna = pengguna;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jawatanId;
	
	private String jawatanDesc;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "refRole_Id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private RefRole refRole;
	
	@OneToMany(mappedBy="refJawatan", cascade = CascadeType.ALL)
	private Set<Pengguna> pengguna = new HashSet<>();
	
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
