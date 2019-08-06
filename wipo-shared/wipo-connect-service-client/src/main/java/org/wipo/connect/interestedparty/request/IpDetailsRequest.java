
package org.wipo.connect.interestedparty.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.wipo.connect.common.input.ContextType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"context",
	"interestedPartyDetailRequest"
})
@XmlRootElement(name = "IpDetailsRequest")
public class IpDetailsRequest {

    @XmlElement(required = true)
    protected ContextType context;
    @XmlElement(required = true)
    protected IpDetailsRequest.InterestedPartyDetailRequest interestedPartyDetailRequest;

    public ContextType getContext() {
	return context;
    }

    public void setContext(ContextType value) {
	this.context = value;
    }

    public IpDetailsRequest.InterestedPartyDetailRequest getInterestedPartyDetailRequest() {
	return interestedPartyDetailRequest;
    }

    public void setInterestedPartyDetailRequest(IpDetailsRequest.InterestedPartyDetailRequest value) {
	this.interestedPartyDetailRequest = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
	    "idInterestedParty",
	    "mainId"
    })
    public static class InterestedPartyDetailRequest {

	protected long idInterestedParty;

	protected String mainId;

	public long getIdInterestedParty() {
	    return idInterestedParty;
	}

	public void setIdInterestedParty(long value) {
	    this.idInterestedParty = value;
	}

	public String getMainId() {
	    return mainId;
	}

	public void setMainId(String mainId) {
	    this.mainId = mainId;
	}

    }

}
