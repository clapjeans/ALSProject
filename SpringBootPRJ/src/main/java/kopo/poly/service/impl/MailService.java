package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.service.IMailService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.PrivateUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import reactor.core.Exceptions;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service("MailService")
public class MailService implements IMailService {




    private final String mainEmailID = PrivateUtil.mainEmailID;
    private final String mainEmailPW = PrivateUtil.mainEmailPW;

   /* @Override
    public int doSendMail(MailDTO pDTO) {

        // 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
        log.info(this.getClass().getName() + ".doSendMail start");

        // 메일 발송 성공여부(발송성공 : 1 / 발송실패 : 0)
        int res = 1;

        // 전달 받은 DTO로부터 데이터 가져오기(null 방지 if문)
        if (pDTO == null) {
            pDTO = new MailDTO();
        }

        String toMail = CmmUtil.nvl(pDTO.getToMail()); // 받는 사람

        Properties props = new Properties();
        String host = "smtp.naver.com";
        props.put("mail.smtp.host", host); // javax 외부 라이브러리에 메일 보내는 사람의 정보 설정
        props.put("mail.smtp.port",465);
        props.put("mail.smtp.auth", "true"); // javax 외부 라이브러리에 메일 보내는 사람 인증 여부 설정
        props.put("mail.smtp.ssl.enable","true");


        // 네이버 SMTP서버 인증 처리 로직
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mainEmailID, mainEmailPW);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mainEmailID));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
            log.info(pDTO.getToMail());
            log.info(pDTO.getTitle());
            // 메일 제목
            message.setSubject(CmmUtil.nvl(pDTO.getTitle()));

            // 메일 내용
            message.setText(CmmUtil.nvl(pDTO.getContents()));

            // 메일 발송
            Transport.send(message);

        } catch (Exception e) {
            res = 0; // 메일 발송 실패 0
            log.info("[ERROR]" + this.getClass().getName() + ".doSendMail :" + e);
        }

        // 로그 찍기
        log.info(this.getClass().getName() + ".doSendMail end!");

        return res;
    }*/
   @Override
   public  int doSendMail(MailDTO pDTO){

       log.info("메일 전송 시작");


       HtmlEmail email1 = new HtmlEmail();
       email1.setHostName("smtp.naver.com");
       email1.setSmtpPort(465);

       email1.setAuthentication(mainEmailID, mainEmailPW);

       email1.setSSLOnConnect(true);
       email1.setStartTLSEnabled(true);

       int res = 0;

       try{
           email1.setFrom(mainEmailID, "관리자", "utf-8"); //???
           email1.addTo(pDTO.getToMail(), "사용자", "utf-8"); //받는사람
           email1.setSubject(pDTO.getTitle()); //제목






           email1.setHtmlMsg((String)pDTO.getContents());  //내용
           email1.send();
           res = 1;
       } catch (EmailException e) {
           e.printStackTrace();
       }

       log.info("메일 전송 완료");
       return  res;
   }
}
