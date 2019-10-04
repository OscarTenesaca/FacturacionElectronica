package reportes;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnvioCorreo {
	
	
	public void EnviarCorreo(String emailCliente,String numFactura) {
		
		String correoEmpresa="tenesaca.999@gmail.com";
		String contrasena="tenesaca1234";
		
		
		  try
	        {
	            Properties props = new Properties();
	            props.put("mail.smtp.host", "smtp.gmail.com");
	            props.setProperty("mail.smtp.starttls.enable", "true");
	            props.setProperty("mail.smtp.port", "587");
	            props.setProperty("mail.smtp.user", correoEmpresa);
	            props.setProperty("mail.smtp.auth", "true");

	            Session session = Session.getDefaultInstance(props, null);
	            // session.setDebug(true);

	            // Se compone la parte del texto
	            BodyPart texto = new MimeBodyPart();
	            texto.setText("Almacenes PTZ S.A \n" + 
	            		"\n" + 
	            		"Estimad@.\n" + 
	            		"\n" + 
	            		"Almacenes PTZ S.A le hace llegar su Guia de remision electronica\n" + 
	            		"\n" + 
	            		"por su seguridad y la nuestra, solicitamos que nos confirme la recepcion de este correo a email: tenesaca.999@gmail.com gracias por su atencion\n" + 
	            		"");

	            // Se compone el adjunto con la imagen
	            BodyPart adjunto = new MimeBodyPart();
	            adjunto.setDataHandler(
	                new DataHandler(new FileDataSource("/Users/oscartenesaca/eclipse-workspace/FacturaElectronica/ReporteFacturasPDF/FAC"+numFactura+".pdf")));
	            adjunto.setFileName("FacturaElectronica"+numFactura+".pdf");
	            
//	            adjunto.setDataHandler(
//	                    new DataHandler(new FileDataSource("/Users/oscartenesaca/eclipse-workspace/LectorArchivo-CC/lib/exameng.txt")));
//	                adjunto.setFileName("FacturaElectronica");

	            // Una MultiParte para agrupar texto e imagen.
	            MimeMultipart multiParte = new MimeMultipart();
	            multiParte.addBodyPart(texto);
	            multiParte.addBodyPart(adjunto);

	            // Se compone el correo, dando to, from, subject y el
	            // contenido.
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(emailCliente));
	            message.addRecipient(
	                Message.RecipientType.TO,
	                new InternetAddress(emailCliente));
	            message.setSubject("Factura Electronica"+numFactura);
	            message.setContent(multiParte);

	            // Se envia el correo.
	            Transport t = session.getTransport("smtp");
	            t.connect(correoEmpresa, contrasena);
	            t.sendMessage(message, message.getAllRecipients());
	            t.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }	
		
		
		
	
	
	
	
	
	

}
