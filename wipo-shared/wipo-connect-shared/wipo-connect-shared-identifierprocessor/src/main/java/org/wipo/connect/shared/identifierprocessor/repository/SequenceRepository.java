package org.wipo.connect.shared.identifierprocessor.repository;

import org.springframework.data.repository.CrudRepository;
import org.wipo.connect.shared.identifierprocessor.entity.Sequence;

public interface SequenceRepository<P> extends CrudRepository<Sequence, Long> {    
}
