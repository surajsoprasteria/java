/**
 * RightOwnersName Entity
 * @author Suraj
 *
 */
package org.wipo.suite.modules.name.dal.entity;

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
@Table(name = "INTERESTED_PARTY_NAME")
public class RightOwnersName {

	@Id
	@Column(name = "ID_INTERESTED_PARTY_NAME")
	private Long idInterestedPartyName;	
	@Column(name = "USER_INSERT")
	private String userInsert;
	@Column(name = "DATE_INSERT")
	private Date dateInsert;
	@Column(name = "USER_UPDATE")
	private String userUpdate;
	@Column(name = "DATE_UPDATE")
	private Date dateUpdate;

	/*@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_name")
	private Name name;*/
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_INTERESTED_PARTY")
	private RightOwners rightOwners;

}
