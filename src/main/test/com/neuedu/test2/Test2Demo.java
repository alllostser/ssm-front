package com.neuedu.test2;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.commons.serverResponse;
import org.junit.Test;

public class Test2Demo {
    @Test
    public void test(){
        System.out.println(JSONObject.toJSONString(serverResponse.serverSuccess()));
    }
}
