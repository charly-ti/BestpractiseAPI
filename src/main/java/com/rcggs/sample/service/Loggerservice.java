package com.rcggs.sample.service;

//import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rcggs.sample.dto.AuditLog;

@Service
public class Loggerservice {
//	@Autowired
//	ProducerTemplate producerTemplate;
	@Autowired
	private KafkaTemplate<Object, Object> template;
	
	public AuditLog sendToLog(AuditLog auditLog) {
//		producerTemplate.sendBody("direct:customKafka", auditLog);
//		AuditLog auditLog = new AuditLog("operationName","buisinessTxnNumber",15,new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()),"INFO","myMessage","source");
		this.template.send("topic1",auditLog);
		return auditLog;
	}
	
	

}
