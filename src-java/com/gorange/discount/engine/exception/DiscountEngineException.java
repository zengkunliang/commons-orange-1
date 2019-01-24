package com.gorange.discount.engine.exception;

/**
 * 折扣引擎异常类<br>
 * 折扣引擎中所有的异常将通过该类进行捕获或抛出
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DiscountEngineException extends Exception {
    /**
     * 无参构造
     */
    public DiscountEngineException(){
        super();
    }

    /**
     * 有参构造
     * @param message 错误消息
     */
    public DiscountEngineException(String message){
        super(message);
    }

    /**
     * 有参构造
     * @param cause 错误对象
     */
    public DiscountEngineException(Throwable cause){
        super(cause);
    }

    /**
     * 有参构造
     * @param message   错误消息
     * @param cause     错误对象
     */
    public DiscountEngineException(String message,Throwable cause){
        super(message,cause);
    }
}
