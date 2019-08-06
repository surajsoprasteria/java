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

package org.wipo.connect.ws_factory;

import org.wipo.connect.common.vo.ServiceClientInfoVO;
import org.wipo.connect.echoservice.types.EchoResponse;

public interface EchoServiceFactory {

    EchoResponse callEcho(String msgIn, ServiceClientInfoVO clientInfoVO);

    EchoResponse callEcho(String msgIn, ServiceClientInfoVO clientInfoVO, String endpoint);

    /**
     * Change base url.
     *
     * @param baseUrl
     *            the base url
     */
    void changeBaseUrl(String baseUrl);

}
