package ar.edu.listasCorreo.observers

class MailObserver extends PostObserver {

	def messageSender

	public MailObserver(pMessageSender) {
		messageSender = pMessageSender
	}

	def send(post) {
		def lista = post.destino
		lista.getMailsDestino(post).each { mailDestino ->
			def mail = new Mail(
				from: post.emisor.mail,
				titulo: "[" + lista.encabezado + "] nuevo post",
				message: post.mensaje,
				to: mailDestino)
			messageSender.send(mail)
		}
	}
	
}
