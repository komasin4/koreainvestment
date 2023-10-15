package com.komasin4.finance.kis.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	@Value("${datasource.ip}")
	private String dbIp;
	
	@Value("${secret.appkey}")
	private String appkey;

	@Value("${bot.chatid}")
	private String chatId;

	@GetMapping("/api/test/{name}")
    public @ResponseBody String test(Model model, @PathVariable("name") String name) {
        return name;
    }

    @GetMapping("/api/test/val")
    public @ResponseBody String value() {
    	log.debug("datasource.ip" + dbIp);
    	log.debug("secret.appkey" + appkey);
    	log.debug("dbot.chatId" + chatId);
    	return "val";
    }

}
