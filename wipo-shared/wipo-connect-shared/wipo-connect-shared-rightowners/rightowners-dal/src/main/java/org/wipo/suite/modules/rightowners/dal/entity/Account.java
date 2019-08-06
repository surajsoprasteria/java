/**
 * Account Entity
 * @author Suraj
 *
 */

package org.wipo.suite.modules.rightowners.dal.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Account {
	@Id
	@Column(name = "ID_ACCOUNT")
	private Long idAccount;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "ACTIVE")
	private String active;
	@Column(name = "USER_INSERT")
	private String userInsert;
	@Column(name = "DATE_INSERT")
	private String dateInsert;
	@Column(name = "USER_UPDATE")
	private String userUpdate;
	@Column(name = "DATE_UPDATE")
	private String dateUpdate;
	@Column(name = "FK_UI_LANGUAGE")
	private String fkUiLanguage;
	
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<RightOwners> rightOwners;

}
