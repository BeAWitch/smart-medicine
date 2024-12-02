package top.medicine.component;

import cn.hutool.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
public class EmailClient {

    @Autowired
    private JavaMailSenderImpl mailSender;

    
    @Value("${spring.mail.username}")
    private String email;

    
    @Value("${spring.mail.valid}")
    private Integer valid;

    
    @Value("${spring.mail.template}")
    private String template;

    
    @Value("${spring.mail.title}")
    private String title;

    
    public String sendEmailCode(String targetEmail) {
        // 生成随机验证码
        String verifyCode = RandomUtil.randomNumbers(6);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject(title);
            helper.setText(String.format(template, verifyCode, valid), true);
            helper.setFrom(email);
            helper.setTo(targetEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
        return verifyCode;
    }

    
    public void sendEmail(String targetEmail, String title, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject(title);
            helper.setText(content, true);
            helper.setFrom(email);
            helper.setTo(targetEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}
