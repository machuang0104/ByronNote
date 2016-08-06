package com.byron.library.http.vo;

import java.io.Serializable;

/**
 * Created by sinyoo on 2016/7/21.
 */
public class BaseResult implements Serializable {
    /**
     * 1 :sucess; 0 :fail
     */
    public int CallResult;

    public String ErrorMessage;

    public String ResultMessage;

    public int getCallResult() {
        return CallResult;
    }

    public void setCallResult(int callResult) {
        CallResult = callResult;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String resultMessage) {
        ResultMessage = resultMessage;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "CallResult=" + CallResult +
                ", ErrorMessage='" + ErrorMessage + '\'' +
                ", ResultMessage='" + ResultMessage + '\'' +
                '}';
    }
}
