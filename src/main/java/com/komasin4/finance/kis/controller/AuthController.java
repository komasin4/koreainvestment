package com.komasin4.finance.kis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.komasin4.finance.kis.util.CallUtil;

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
	
	@Autowired
	private CallUtil callUtil;
	
	@GetMapping("/api/getCurrent")
	public @ResponseBody Object getCurrentValue(
					 @RequestParam(value="FID_COND_MRKT_DIV_CODE") String code
					,@RequestParam(value="FID_INPUT_ISCD") String isCd
	) {
    
//		FID_COND_MRKT_DIV_CODE	FID 조건 시장 분류 코드	String	Y	2	J : 주식, ETF, ETN
//		FID_INPUT_ISCD
		
		JSONObject param = new JSONObject();

		param.put("FID_COND_MRKT_DIV_CODE", code);
		param.put("FID_INPUT_ISCD", isCd);

		JSONObject oRtn = callUtil.call("/oauth2/tokenP", null, param);
		return oRtn;
	}
	
	@GetMapping("/api/auth")
	public @ResponseBody Object getToken() {
		JSONObject param = new JSONObject();
		param.put("grant_type", "client_credentials");
		param.put("appkey", appKey);
		param.put("appsecret", secretKey);

		JSONObject oRtn = callUtil.call("/oauth2/tokenP", null, param);
		
		return oRtn;
	}
}
