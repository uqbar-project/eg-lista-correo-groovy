package ar.edu.listasCorreo.observers

import ar.edu.listasCorreo.config.ServiceLocator

class MailObserver extends PostObserver {

	def send(post) {
		def lista = post.destino
		lista.getMailsDestino(post).each { mailDestino ->
			def mail = new Mail(
				from: post.emisor.mail,
				titulo: "[" + lista.encabezado + "] nuevo post",
				message: post.mensaje,
				to: mailDestino)
			ServiceLocator.instance.messageSender.send(mail)
		}
	}
	
}
