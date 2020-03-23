package com.jcg.spring.mvc.email;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class EmailController {
	static ModelAndView modelViewObj;

	@Autowired
	private JavaMailSender mailSenderObj;

	static String emailTo,subject, message;
	@RequestMapping(value = {"/", "emailForm"}, method = RequestMethod.GET)
	
	public ModelAndView showEmailForm(ModelMap model) {
		modelViewObj = new ModelAndView("emailForm");
		return  modelViewObj;		
	}
	
	@RequestMapping(value = "sendEmail", method = RequestMethod.POST)
	public String sendEmailToClient(HttpServletRequest request, final @RequestParam CommonsMultipartFile[] attachFileObj) {

		final String mailTo=request.getParameter("mailTo");
		final String subject = request.getParameter("subject");
		final String message = request.getParameter("message");
	
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/myusers?characterEncoding=utf8","root","sushmitha");
			Statement st=con.createStatement();
			st.executeUpdate("insert into email(mailTo,subject, message,createddate) values('"+mailTo+"','"+subject+"','"+message+"','"+LocalDateTime.now()+"')");
	
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		String[] to=mailTo.split(",");
		
		System.out.println("MailTo="+mailTo);
		System.out.println("Subject="+subject);
		System.out.println("Message="+message);
		
		

		List<CommonsMultipartFile> attachments = new ArrayList<CommonsMultipartFile>();
		for(int i=0;i<attachFileObj.length;i++) {
			System.out.println("attachfile="+attachFileObj[i].getOriginalFilename());
			attachments.add(attachFileObj[i]);
		}
		
		System.out.println("attachfile success");
		
		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");		
				
				if(!to.equals("")) {
					for(String vto:to) {
						mimeMsgHelperObj.setTo(vto);	
					}
				}

				mimeMsgHelperObj.setSubject(subject);
				mimeMsgHelperObj.setText(message);
				
				if(!attachments.equals("")) {
					for(CommonsMultipartFile file :attachments) {
						mimeMsgHelperObj.addAttachment(file.getOriginalFilename(),file);
					}
				}
				
				System.out.println("sending done");
				
			}
		
		});	
		
		return "success" ;
	}

}







/*
	static String emailToRecipient, emailSubject, emailMessage;
	static final String emailFromRecipient = "software@easyinsuranceindia.com";

	static ModelAndView modelViewObj;

	@Autowired
	private JavaMailSender mailSenderObj;

	@RequestMapping(value = {"/", "emailForm"}, method = RequestMethod.GET)
	public ModelAndView showEmailForm(ModelMap model) {
		modelViewObj = new ModelAndView("emailForm");
		return  modelViewObj;		
	}

	// This Method Is Used To Prepare The Email Message And Send It To The Client
	@RequestMapping(value = "sendEmail", method = RequestMethod.POST)
	public ModelAndView sendEmailToClient(HttpServletRequest request, final @RequestParam CommonsMultipartFile attachFileObj) {

		// Reading Email Form Input Parameters		
		emailSubject = request.getParameter("subject");
		emailMessage = request.getParameter("message");
		emailToRecipient = request.getParameter("mailTo");

		User u=new User();
		u.setSubject(emailSubject);
		u.setMessage(emailMessage);
		u.setMailTo(emailToRecipient);
	
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/myusers?characterEncoding=utf8","root","sushmitha");
			Statement st=con.createStatement();
			String sql="insert into email(id,subject, message, mailTo,createddate) values('"+u.getId()+"','"+u.getSubject()+"','"+u.getMessage()+"','"+u.getMailTo()+"',LocalDateTime.now())";
			st.executeUpdate(sql);
		
		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");				
				mimeMsgHelperObj.setTo(emailToRecipient);
				mimeMsgHelperObj.setFrom(emailFromRecipient);				
				mimeMsgHelperObj.setText(emailMessage);
				mimeMsgHelperObj.setSubject(emailSubject);

				
				if ((attachFileObj != null) && (attachFileObj.getSize() > 0) && (!attachFileObj.equals(""))) {
					//System.out.println("\nAttachment Name?= " + attachFileObj.getOriginalFilename() + "\n");
					mimeMsgHelperObj.addAttachment(attachFileObj.getOriginalFilename(), new InputStreamSource() {					
						public InputStream getInputStream() throws IOException {
							return attachFileObj.getInputStream();
						}
					});
				} else {
					System.out.println("\nNo Attachment Is Selected By The User. Sending Text Email!\n");
				}
			}
		});
		System.out.println("\nMessage Send Successfully.... Hurrey!\n");
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		System.out.println("\nReceipient = " + emailToRecipient + ", Subject = " + emailSubject + ", Message = " + emailMessage + "\n");
	System.out.println("\nAttached File = " + attachFileObj.getOriginalFilename() + "\n");

		modelViewObj = new ModelAndView("success","messageObj","Thank You! Your Email Has Been Sent!");
		return  modelViewObj;	
	}*/
	
	
	
	