package mail;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
	
	 // org.springframework.mail.javamail.JavaMailSender
    private final JavaMailSender javaMailSender;
 
    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
    	this.javaMailSender = javaMailSender;
    }
    
 
    @Override
    public boolean send(String subject, String text, String from, String to) {
        // javax.mail.internet.MimeMessage
        MimeMessage message = javaMailSender.createMimeMessage();
 
        try {
            // org.springframework.mail.javamail.MimeMessageHelper
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject);
            helper.setText(text);
            helper.setFrom(from);
            helper.setTo(to);
 
            // 첨부 파일 처리
			/*
			 * if (filePath != null) { File file = new File(filePath); if (file.exists()) {
			 * helper.addAttachment(file.getName(), new File(filePath)); } }
			 */
 
            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }


}
