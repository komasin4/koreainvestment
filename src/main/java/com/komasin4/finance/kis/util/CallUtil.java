package com.komasin4.finance.kis.util;

import java.util.Iterator;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CallUtil {
	@Value("${kis.url}")
	private String url;


	@Value("${secret.appkey}")
	private String appKey;

	@Value("${secret.appsecret}")
	private String secretKey;
	
	public JSONObject call(String uri, JSONObject header, JSONObject param) {
		log.debug("appKey:" + appKey);
		log.debug("secretKey:" + secretKey);
		log.debug("url:" + url);

		log.debug("param:" + param.toString());

		Builder builder = WebClient
				.builder()
				.baseUrl(url)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json; utf-8");
		
		if(header != null) {
			Iterator iterator = header.keys();
	        while(iterator.hasNext())
	        {
	            String str = (String) iterator.next();
	            builder.defaultHeader(str, header.getString(str));
	            System.out.println(str);
	        }
		}
				
		WebClient webClient = builder.build();
		
		String result = webClient.post()
				.uri(uri)
				.bodyValue(param.toString())
				.retrieve()
				.bodyToMono(String.class)
				.onErrorResume(e -> Mono.empty())
				.block();

		JSONObject oRtn = new Gson().fromJson(result, JSONObject.class);
		
		log.debug("result:" + result);

		return oRtn;
	}
}
