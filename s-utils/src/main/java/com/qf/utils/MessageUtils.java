package com.qf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.pojo.resp.ResultMessage;

public class MessageUtils {

    public static String getMessage(boolean isSystemMessage,String fromId,Object message){
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSystem(isSystemMessage);
        resultMessage.setMessage(message);
        if (fromId != null){
            resultMessage.setFromEmail(fromId);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(resultMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
