package org.letter.boot.simple.control;

import org.letter.boot.simple.api.MessageRequest;
import org.letter.boot.simple.api.MessageResponse;
import org.letter.boot.simple.api.MessageService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DubboControl {

	//注册中心负载-dubbo
//	@DubboReference(protocol = "dubbo", check = false)
//	private MessageService messageServiceDubbo;


	@GetMapping("/dubbo/test")
	@ResponseBody
	public MessageResponse doTestDubbo() {
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setTid(UUID.randomUUID().toString());
		System.out.println("call send for dubbo");
//		MessageResponse messageResponse = messageServiceDubbo.send(messageRequest);
		MessageResponse messageResponse = new MessageResponse();
		return messageResponse;
	}


}
