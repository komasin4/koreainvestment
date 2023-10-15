package com.komasin4.finance.kis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class AuthController {

	@Value("${kis.url}")
	private String url;


	@Value("${secret.appkey}")
	private String appKey;

	@Value("${secret.appsecret}")
	private String secretKey;

	@GetMapping("/api/auth")
	public @ResponseBody String auth() {
		log.debug("appKey:" + appKey);
		log.debug("secretKey:" + secretKey);
		log.debug("url:" + url);


		//    	WebClient webClient = WebClient
		//                .builder()
		//                .baseUrl("http://localhost:8080")
		//                .defaultCookie("쿠키","쿠키값")
		//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		//                .build();
		
		JSONObject param = new JSONObject();
		param.put("grant_type", "client_credentials");
		param.put("appkey", appKey);
		param.put("secretkey", secretKey);
		
		log.debug("param:" + param.toString());


		WebClient webClient = WebClient
				.builder()
				.baseUrl(url)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json; utf-8")
				.build();

		String result = webClient.post()
				.uri("/oauth2/Approval")
				.bodyValue(param.toString())
				.retrieve()
				.bodyToMono(String.class)
				.onErrorResume(e -> Mono.empty())
				.block();

		log.debug("result:" + result);

		return "auth";
	}



}
