package ar.edu.listasCorreo.observers

class MailObserver extends PostObserver {

	/** 
	 * No usamos variable message sender	  
	 */
	
	def send(post) {
		def lista = post.destino
		lista.getMailsDestino(post).each { mailDestino ->
			def mail = new Mail(
				from: post.emisor.mail,
				titulo: "[" + lista.encabezado + "] nuevo post",
				message: post.mensaje,
				to: mailDestino)
			
			// messageSender se reemplaza por la instancia concreta singleton
			StubMailSender.instance.send(mail)
		}
	}
	
}



