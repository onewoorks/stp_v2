package com.stp.auth.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "refRole")
public class RefRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	
	private String roleDesc;
	
	@OneToMany(mappedBy="refRole", cascade = CascadeType.ALL)
	private Set<RefJawatan> refJawatan = new HashSet<>();

	public String getRoleDesc() {
		return roleDesc;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}
