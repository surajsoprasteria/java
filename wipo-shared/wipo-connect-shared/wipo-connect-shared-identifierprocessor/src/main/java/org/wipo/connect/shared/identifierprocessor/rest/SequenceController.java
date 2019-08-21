package org.wipo.connect.shared.identifierprocessor.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.shared.identifierprocessor.entity.Sequence;
import org.wipo.connect.shared.identifierprocessor.service.SequenceService;

@RestController
public class SequenceController {

	@Autowired
	SequenceService sequenceService;

	@RequestMapping(value = "/sequence", method = RequestMethod.GET)
	public List<Sequence> getSequenceByName() {
		return sequenceService.findByName();
	}

	@RequestMapping(value = "/sequence/{sequenceName}", method = RequestMethod.GET)
	public int getNextSequenceByName(@PathVariable String sequenceName) {
		List<Sequence> sequenceList = sequenceService.findByName();
		
		System.out.println("sequenceName to be find next value"+sequenceName);
		for(Sequence seq:sequenceList){
			if(seq.getName().equalsIgnoreCase(sequenceName)){
				System.out.println("next value ::  found :: "+seq.getNext());
				return seq.getNext();
			}
		}
		return 1;
	}
}
