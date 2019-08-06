/**
 * Cmo Entity
 * @author Suraj
 *
 */

package org.wipo.suite.modules.rightowners.dal.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Cmo {

	@Id
	@Column(name = "ID_CMO")
	private Long idCmo;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CODE")
	private String code;
	@Column(name = "ACRONYM")
	private String acronym;
	
	@ManyToOne
	@JoinColumn
	private Territory territory;
	/*@Column(name = "FK_ORIGIN_COUNTRY")
	private String fkOriginCountry;
	*/
	@Column(name = "CONTACT")
	private String contact;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "INTERNATIONAL_NAME")
	private String internationalName;
	@Column(name = "IS_DATA_SOURCE")
	private String isDataSource;
	@Column(name = "FK_CMO_TYPE")
	private String fkCmoType;
	
	@OneToMany(mappedBy="cmo",cascade= CascadeType.ALL)
	@JsonIgnore
	Set <RightOwners> rightowners;
	
	

}
