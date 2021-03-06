/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afrigis.services.addresssubmission.impl;

import com.afrigis.services.addresssubmission.AddressSubmissionResponse;
import com.afrigis.services.exceptions.AfriGISServicesException;
import com.afrigis.services.impl.AbstractResponse;
import com.afrigis.services.internal.saas.api2.dataCorrectionSubmit.paresdObjects.SubmissionResult;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.binary.StringUtils;

/**
 * Default implementation of {@link AddressSubmissionResponse}.
 * @author Pieterv
 */
public class AddressSubmissionResponseImpl extends
        AbstractResponse implements AddressSubmissionResponse {

    private String clientID;
    private String ticketNumber;
    private AddressSubmissionResponsePojo data;

    /**
     *
     * @param input
     */
    @Override
    protected void completeBuild(byte[] input) {
        if (isError()) {
            throw new AfriGISServicesException(getError());
        }
        final String utf8Str = StringUtils.newStringUtf8(input);
        final Gson gson = new Gson();
        data = gson.fromJson(utf8Str, AddressSubmissionResponsePojo.class);
    }

    /**
     *
     * @return List of Result
     */
    @Override
    public List<SubmissionResult> listResults() {
        return getResult() != null ? Arrays.asList(getResult()) : new ArrayList<SubmissionResult>();

    }

    /**
     *
     * @return Result
     */
    public SubmissionResult getResult() {
        return data.result != null ? data.result : null;
    }

    /**
     *
     * @return Client ID your AfriGIS key.
     */
    @Override
    public String getClientID() {
        return clientID;
    }

    /**
     *
     * @return Ticket number the reference number in the internal AfriGIS
     *         ticketing system with which you can track the progress of the Address
     *         Submission.
     */
    @Override
    public String getTicketNumber() {
        return ticketNumber;
    }

    /**
     *
     * @return Source
     */
    public String getSource() {
        return data.source;
    }

    /**
     *
     * @return Code
     */
    public Integer getCode() {
        return data.code;
    }

    /**
     *
     * @return Message
     */
    public String getMessage() {
        return data.message;
    }

    private class AddressSubmissionResponsePojo {

        private SubmissionResult result;
        private Integer code;
        private String message;
        private String source;
    }

}
