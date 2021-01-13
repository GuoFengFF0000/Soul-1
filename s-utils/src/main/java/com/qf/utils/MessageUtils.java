package com.qf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.pojo.resp.ResultMessage;

public class MessageUtils {

    public static String getMessage(boolean isSystemMessage,String fromEmail,Object message){
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSystem(isSystemMessage);
        resultMessage.setMessage(message);
        if (fromEmail != null){
            resultMessage.setFromEmail(fromEmail);
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
