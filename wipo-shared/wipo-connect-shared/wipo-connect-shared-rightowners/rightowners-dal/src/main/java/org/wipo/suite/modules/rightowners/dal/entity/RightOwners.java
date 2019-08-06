/**
 * RightOwners Entity
 * @author Suraj
 *
 */
package org.wipo.suite.modules.rightowners.dal.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "INTERESTED_PARTY")
public class RightOwners {

	@Id
	@Column(name = "main_id")
	private String mainId;
	private String type;

	private String sex;
	@Column(name = "birth_date")
	private Date birthDate;
	@Column(name = "death_date")
	private Date deathDate;
	@Column(name = "birth_place")
	private String birthPlace;
	@Column(name = "birth_state")
	private String birthState;
	
	@ManyToOne(cascade=CascadeType.ALL)
	/*@JoinColumns({
		@JoinColumn(name="fk_birth_country"),
		@JoinColumn(name="FK_AFFILIATION_TERRITORY")
	})*/
	@JoinColumn(name="fk_birth_country")	
	private Territory territory;
	/*@Column(name = "fk_birth_country")
	private String birthCountry;
	@Column(name = "FK_AFFILIATION_TERRITORY")
	private String fkAffiliationTerritory;	
	*/
	@Column(name = "amendment_timestamp")
	private String amendmentTimestamp;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_account")
    private Account account;
	
	/*
	 * fk_account value is null enable and no value inserted for right owner
	 * @Column(name = "fk_account")
	private String account;*/
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_status")
	private RightOwnersStatus rightOwnersStatus; 

	/*
	 * fk_status value is not null enable and value required to insert right owner
	 * @Column(name = "fk_status")
	 * private String status;
	*/	
	
	@Column(name = "marital_status")
	private String maritalStatus;
	@Column(name = "user_insert")
	private String userInsert;
	@Column(name = "user_update")
	private String userUpdate;
	@Column(name = "date_update")
	private Date dateUpdate;
	@Column(name = "creation_date")
	private Date creationDate;
	@Column(name = "do_affiliation")
	private String doAffiliation;
	@Column(name = "is_affiliated")
	private String isAffiliated;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_cmo")
	private Cmo cmo;
	/*@Column(name = "fk_cmo")
	private String fkCmo;*/

	private Integer deleted;
	private Integer dummy;

	@Column(name = "date_insert")
	private Date dateInsert;
	@Column(name = "sync_version")
	private String syncVersion;
}
