package com.easyspring.core.model.po;


import com.easyspring.core.model.Model;

import java.lang.reflect.Field;
import java.util.Date;

public abstract class AbstractPO<K> implements Model<K> {

    public static final String CREATE_TIME_FIELD_NAME ="createTime";
    public static final String MODIFY_TIME_FIELD_NAME ="modifyTime";

    protected void assignment(String fieldName,Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(fieldName);
        if(field!=null){
            field.setAccessible(true);
            field.set(this,value);
        }
    }

    public void setDefaultCreateTime(){
        try{
            assignment(CREATE_TIME_FIELD_NAME, new Date());
        }catch (Exception e){

        }
    }

    public void setDefaultModifyTime(){
        try{
            assignment(MODIFY_TIME_FIELD_NAME, new Date());
        }catch (Exception e){

        }
    }

    public void clearCreateTime(){
        try{
            assignment(CREATE_TIME_FIELD_NAME, null);
        }catch (Exception e){

        }
    }

    public void clearModifyTime(){
        try{
            assignment(MODIFY_TIME_FIELD_NAME, null);
        }catch (Exception e){

        }
    }
}
