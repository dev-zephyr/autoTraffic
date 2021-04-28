package com.zephyr;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(Application.class, args);

        String cfUrl = "http://dl6vtocir0ohd.cloudfront.net/";
        String ec2Url = "http://ec2-15-165-6-67.ap-northeast-2.compute.amazonaws.com/";

        while(true) {

            int num = (int) Math.random()*2;
            boolean flag = true;

            if(num > 1) {
                flag = false;
            }

            if(flag) {
                callApi(ec2Url);
            }

            Thread.sleep(60 * 1000);

        }


    }

    public static void callApi(String url) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(url);

            HttpResponse response = client.execute(getRequest);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println(body);
            } else {
                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
            }

        } catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
