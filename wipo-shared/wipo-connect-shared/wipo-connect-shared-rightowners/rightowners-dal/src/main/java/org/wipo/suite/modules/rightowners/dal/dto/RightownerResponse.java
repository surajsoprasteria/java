/**
 * RightownerResponse
 * @author Suraj
 *
 */
package org.wipo.suite.modules.rightowners.dal.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RightownerResponse {
	private String mainId;
	private String type;
	private String sex;
	private Date birthDate;
	private Date deathDate;
	private String birthPlace;
	private String birthState;
	private String amendmentTimestamp;
	private String maritalStatus;
	private String userInsert;
	private String userUpdate;
	private Date dateUpdate;
	private Date creationDate;
	private String doAffiliation;
	private String isAffiliated;
	private Integer deleted;
	private Integer dummy;
	private Date dateInsert;
	private String syncVersion;

}
