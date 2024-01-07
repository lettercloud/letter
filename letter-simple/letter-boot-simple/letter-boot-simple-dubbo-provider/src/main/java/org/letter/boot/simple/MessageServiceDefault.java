package org.letter.boot.simple;


import org.letter.boot.simple.api.MessageRequest;
import org.letter.boot.simple.api.MessageResponse;
import org.letter.boot.simple.api.MessageService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service(interfaceClass = MessageService.class)
@Component
public class MessageServiceDefault implements MessageService {

	@Override
	public MessageResponse send(MessageRequest messageRequest) {
		MessageResponse response = new MessageResponse();
		System.out.println("sendMessage Rev Tid:" + messageRequest.getTid());
		return response;
	}
}