package vn.seven.stc.umgr.services;

import io.github.jhipster.config.JHipsterProperties;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import vn.seven.stc.umgr.models.User;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class MailService {
    @Value("${baseUrl}")
    private String baseUrl;

    private final Logger logger = LoggerFactory.getLogger(MailService.class);

    private static final String USER = "user";

    private static final String USER_PASS = "userPass";

    private static final String BASE_URL = "baseUrl";

    private final JHipsterProperties jHipsterProperties;

    private final JavaMailSender javaMailSender;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    public MailService(JHipsterProperties jHipsterProperties, JavaMailSender javaMailSender,
                       MessageSource messageSource, SpringTemplateEngine templateEngine) {

        this.jHipsterProperties = jHipsterProperties;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        if(!isValid(to)) return;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            logger.debug("Sent email to User '{}'", to);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Email could not be sent to user '{}'", to);
        }
    }

    @Async
    public void sendCreationEmail(User user) {
        logger.debug("Sending creation email to '{}'", user.getEmail());
        Locale locale = getLocale(user.getLangKey());
        Context context = new Context(locale);

        String resetUrl = getUrl(user) + "/#/reset-password?token=" + user.getActivationToken() + "&email=" + user.getEmail()  + "&active=1";
        context.setVariable("RESET_URL", resetUrl);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content;
        String subject;
        content= templateEngine.process("mails/creationEmail", context);
        subject = messageSource.getMessage("email.activation.title", null, locale);

        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendPasswordResetMail(User user) {
        logger.debug("Sending password reset email to '{}'", user.getEmail());
        Locale locale = getLocale(user.getLangKey());
        Context context = new Context();
        String resetUrl = getUrl(user) + "/#/reset-password?token=" + user.getForgotPasswordToken() + "&email=" + user.getEmail() + "&active=0";
        context.setVariable("RESET_URL", resetUrl);
        context.setVariable(USER, user);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process("mails/passwordResetEmail", context);
        String subject = messageSource.getMessage("email.reset.title", null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendNotificationMail(String content, String subject, String email) {
        sendEmail(email, subject, content, false, true);
    }

    @Async
    public void sendNormalMail(String email, String organizationName, String mail, String subject) {
        Locale locale = getLocale(null);
        Context context = new Context(locale);
        context.setVariable("organization", organizationName);
        context.setVariable("mail", mail);
        String content;
        content= templateEngine.process("mails/VNPT", context);
        sendEmail(email, subject, content, false, true);
    }

    public Locale getLocale(String language) {
        if(language != null) return Locale.forLanguageTag(language);
        return Locale.forLanguageTag("en");
    }

    static boolean isValid(String email) {
        if(email.contains(".") && email.contains("@")){
            return true;
        }
        return false;
    }

    private String getUrl(User user){
        return this.baseUrl;
    }
}
