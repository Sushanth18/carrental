package com.carrental.util;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class MailConfiguration {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmailWithAttachment(String[] sendTo, String subject, String message, boolean html,
			String attachmentData) {
		File createdFile = null;
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();

			// true = multipart message
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setTo(sendTo);

			helper.setSubject(subject);

			// default = text/plain
			// helper.setText("Check attachment for image!");

			// true = text/html
			helper.setText(message, html);
			
			javaMailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while sending mail:" + e.getMessage());
		} finally {
			if (createdFile != null) {
				createdFile.delete();
			}
		}
	}

}
