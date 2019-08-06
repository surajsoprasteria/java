/**
 * RightOwnersStatus Territory
 * @author Suraj
 *
 */


package org.wipo.suite.modules.rightowners.dal.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Territory {
	@Id
	@Column(name = "ID_TERRITORY")
	private Long idTerritory;
	@Column(name = "CODE")
	private String code;
	@Column(name = "FK_TYPE")
	private String fkType;
	@Column(name = "START_DATE")
	private String startDate;
	@Column(name = "END_DATE")
	private String endDate;
	@Column(name = "USER_INSERT")
	private String userInsert;
	@Column(name = "DATE_INSERT")
	private String dateInsert;
	@Column(name = "USER_UPDATE")
	private String userUpdate;
	@Column(name = "DATE_UPDATE")
	private String dateUpdate;
	@Column(name = "MANAGED_BY_CMO")
	private String managedByCmo;
	
	@OneToMany(fetch= FetchType.EAGER, mappedBy="territory",cascade=CascadeType.ALL)
	@JsonIgnore
	Set <Cmo> cmo;
	

}
