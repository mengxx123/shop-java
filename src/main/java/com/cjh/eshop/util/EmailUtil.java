package com.cjh.eshop.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {
	
	private final static String EMAIL		= "3277684819@qq.com";	// 完整的邮箱地址，发送方的Email
	private final static String BACK		= "qq.com";				// 邮箱的后缀域名（邮箱@后部分）
	private final static String PASSWORD	= "";		// TODO 邮箱密码
	
	
	/**
	 * 发送邮件
	 * @param targetEmail
	 * 接收邮件的邮箱
	 * @param title
	 * 邮箱的标题
	 * @param content
	 * 邮箱的内容
	 * @return 返回是否发送成功
	 */
	public static boolean sendEmail(String targetEmail, String title, String content) {
		try {
			Properties props=new Properties();
			props.put("mail.smtp.host","smtp."+BACK);// 发信的主机，这里我填写的是我们公司的主机！可以不用修改！
			props.put("mail.smtp.auth","true");
			Session s=Session.getInstance(props);
			s.setDebug(true);
		 
			MimeMessage message = new MimeMessage(s);
		 
			// 给消息对象设置发件人/收件人/主题/发信时间
			InternetAddress from = new InternetAddress(EMAIL);
			message.setFrom(from);
			InternetAddress to = new InternetAddress(targetEmail);
			message.setRecipient(Message.RecipientType.TO,to);
			message.setSubject(title);
			message.setSentDate(new Date());
		 
		 
			// 给消息对象设置内容
			BodyPart mdp = new MimeBodyPart();			// 新建一个存放信件内容的BodyPart对象
			mdp.setContent(content, "text/html;charset=gb2312");		// 给BodyPart对象设置内容和格式/编码方式
			Multipart mm = new MimeMultipart();			// 新建一个MimeMultipart对象用来存放BodyPart对
			// 象(事实上可以存放多个)
			mm.addBodyPart(mdp);						// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
			message.setContent(mm);						// 把mm作为消息对象的内容
		 
			message.saveChanges();
			Transport transport = s.getTransport("smtp");
			transport.connect("smtp." + BACK, "3277684819", PASSWORD); // 这里的115798090也要修改为您的QQ号码
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
