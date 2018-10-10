package com.boa.mq;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boa.mq.model.User;

@RestController
@RequestMapping("/testJms")
public class JmsRestController {

//	public static final Logger logger = LoggerFactory.getLogger(JmsRestController.class);
	
    @GetMapping("/message")
    public ResponseEntity<?> reaUser() {//REST Endpoint.
//    	logger.info("Get User ");
		JmsPutGet jmsPutGet = new JmsPutGet();
		return new ResponseEntity<String>(jmsPutGet.readMessage(),HttpStatus.OK);

    }
    
    @PostMapping("/message")
	public ResponseEntity<?> createUser(@RequestBody User user) {
//		logger.info("Inserted User : {}", user);
		JmsPutGet jmsPutGet = new JmsPutGet();
        jmsPutGet.putMessage(user);
		return  ResponseEntity.ok().build();
	}
}
