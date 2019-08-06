/**
 * RightOwners Entity
 * @author Suraj
 *
 */
package org.wipo.suite.modules.name.dal.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(name = "ID_INTERESTED_PARTY")
	private Long idInterestedParty;
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_birth_country")
	private Territory territory;
	@Column(name = "amendment_timestamp")
	private String amendmentTimestamp;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_account")
	private Account account;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_status")
	private RightOwnersStatus rightOwnersStatus;

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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_cmo")
	private Cmo cmo;
	private Integer deleted;
	private Integer dummy;

	@Column(name = "date_insert")
	private String dateInsert;
	@Column(name = "sync_version")
	private String syncVersion;

	@OneToMany(mappedBy = "rightOwners", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<RightOwnersName> rightOwnersName;
}
