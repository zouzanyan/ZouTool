package org.zouzanyan.core;

import org.junit.jupiter.api.Test;


class HttpUrlConnectionRequestTest {

    @Test
    void doRequest() {
        HttpUrlConnectionRequest httpUrlConnectionRequest = new HttpUrlConnectionRequest();
        String s = httpUrlConnectionRequest.doRequest("https://api.github.com/users/zouzanyan/repos", "GET", null);
        System.out.println(s);
    }

    @Test
    void awfa(){

    }
}