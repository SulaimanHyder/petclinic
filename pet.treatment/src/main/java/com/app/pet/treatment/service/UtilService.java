package com.app.pet.treatment.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.pet.treatment.models.Visit;
import com.app.pet.treatment.repository.OwnerRepo;

@Service
public class UtilService {
	
	@Autowired
	private OwnerRepo ownerRepo;
	private JavaMailSender mail;
	
	@Async
	public void sendVisitEmailToOwner(Visit visit) {
		
		String email = ownerRepo.getOwnerEmailByPetId(visit.getPetId());
		
		MimeMessagePreparator mp = mimeMessage -> {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			message.setFrom("petclinic.com");
			message.setTo(email);
			message.setText(visit.toString());
		};
		
		mail.send(mp);
	}
	
	public String checkOwnerNumber(String number, String location) {
		Pattern indNumPttrn = Pattern.compile("^+\\91\\-[0-9]{10}$");
		Matcher indNumMtchr = indNumPttrn.matcher(number);
		
		Pattern usNumPttrn = Pattern.compile("^(\\([0-9]{3}\\)|[0-9]{3}-)[0-9]{3}-[0-9]{4}$");
		Matcher usNumMtchr = usNumPttrn.matcher(number);
		
		if(indNumMtchr.find() && location.equals("India")) {
			return "valid Indian Number";
		} else if(usNumMtchr.find() && location.equals("US")) {
			return "valid US Number";
		}
		
		switch(location) {
			case "India": { return indNumMtchr.find() ? "valid Indian Number" : "Invalid Indian Number"; }
			case "US": { return usNumMtchr.find() ? "valid US Numberr" : "Invalid US Number"; }
			default : {return "Invalid";}
		}
	}

}
