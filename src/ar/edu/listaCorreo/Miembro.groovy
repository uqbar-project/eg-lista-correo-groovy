package ar.edu.listaCorreo

class Miembro {

	String mail
	int mailsEnviados = 0
	boolean bloqueado = false
	
	Miembro(String pMail) {
		mail = pMail
	}
	
	def boolean envioMuchosMensajes() {
		mailsEnviados >= 5
	}
	
	def void bloquear() {
		bloqueado = true
	}

	def void desbloquear() {
		bloqueado = false
	}

}
