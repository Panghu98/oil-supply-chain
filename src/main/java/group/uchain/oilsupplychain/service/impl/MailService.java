package group.uchain.oilsupplychain.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;

/**
 * @author panghu
 * @Title: UtilService
 * @ProjectName oil-supply-chain
 * @date 19-3-27 下午3:31
 */
@Slf4j
@Service
public class MailService {

    private JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    String getUsernameFromContext(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }



    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送普通邮件
     *
     * @param to
     * @param title
     * @param content
     */
    public void sendSimpleMail(String to, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
        log.info("邮件发送成功");
    }

    /**
     * 发送带有附件的邮件
     *
     * @param to
     * @param title
     * @param content
     * @param fileList
     */
    public void sendAttachmentsMail(String to, String title, String content, List<File> fileList) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content);
            String fileName = null;
            for (File file : fileList) {
                fileName = MimeUtility.encodeText(file.getName(), "GB2312", "B");
                helper.addAttachment(fileName, file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
        log.info("邮件发送成功");
    }


}
