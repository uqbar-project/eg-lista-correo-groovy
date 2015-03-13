package ar.edu.listaCorreo

import ar.edu.listaCorreo.mails.Email


class Miembro {
	def mailDefault
	def otrosMails = []
	
	def esRemitente(post) {
		mailDefault == post.from || otrosMails.contains(post.from)
	}
	def enviar(post) {
		EmailSenderProvider.emailSender.send(
		new Email(from:post.from, to:mailDefault, subject:post.subject, content:post.content))
	}
}
