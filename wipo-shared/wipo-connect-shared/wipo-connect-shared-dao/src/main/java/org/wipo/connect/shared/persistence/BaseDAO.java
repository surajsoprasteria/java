/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */



package org.wipo.connect.shared.persistence;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;
import org.wipo.connect.common.logging.ExecutionTimeTrackable;
import org.wipo.connect.common.logging.Loggable;
import org.wipo.connect.shared.persistence.mapping.Mapper;



/**
 * The Class BaseDAO.
 *
 * @author Minervini
 * @param <T>
 *            the generic type
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BaseDAO<T> implements Dao<T> {

    /** The mapper. */
    @Autowired
    private Mapper<T> mapper;




    @Loggable(level = "INFO")
    @ExecutionTimeTrackable
    @Override
    public int delete(Long id) {
        int result = this.mapper.deleteByPrimaryKey(id);
        return result;
    }




    @Loggable(level = "INFO")
    @ExecutionTimeTrackable
    @Override
    public T find(Long id) {
        T dto = this.mapper.selectByPrimaryKey(id);
        return dto;
    }




    @Loggable(level = "INFO")
    @ExecutionTimeTrackable
    @Override
    public List<T> findAll() {
        List<T> listOut;
        listOut = this.mapper.findAll();
        return listOut;
    }




    @Loggable(level = "INFO")
    @ExecutionTimeTrackable
    @Override
    public T merge(T obj) {
        if (!(obj instanceof Identifiable)) {
            throw new IllegalArgumentException(
                    "The object is not instance of Identifiable and cannot be updated!");
        }
        Identifiable idable = (Identifiable) obj;

        if (idable.getId() == null) {
            if (obj instanceof Creatable) {
            	Creatable creatable = (Creatable) obj;
                creatable.setDateInsert(new Date());
            }
            this.mapper.insert(obj);
        } else {
            if (obj instanceof Updatable) {
            	Updatable updtable = (Updatable) obj;
                updtable.setDateUpdate(new Date());
            }
            this.mapper.updateByPrimaryKey(obj);
        }
        return obj;
    }

}
