package ar.edu.listasCorreo.observers

class StubMailSender {

	def mailsEnviados
	
	def send(mail) {
		simularEnvioMail(mail.from, mail.message)
		println("Simulación envío de mail | From: " + mail.from + " | To: " + mail.to + " | Message: " + mail.message)
	}
	
	def simularEnvioMail(from, message) {
		def mensajes = mailsDe(from)
		mensajes.add(message)
		mailsEnviados.put(from, mensajes)
	}
	
	def mailsDe(from) {
		def mensajes = mailsEnviados.get(from)
		if (mensajes == null) {
			mensajes = new HashSet<String>()
		}
		mensajes
	}

	/**********************************************
	 * IMPLEMENTACION DE SINGLETON
	 * ********************************************/
	static instance

	// TODO: Ver por qué no es verdaderamente privado
	private StubMailSender() {
		mailsEnviados = new HashMap<String, Set<String>>()
	}

	static def getInstance() {
		if (!instance) {
			instance = new StubMailSender()
		}	
		instance
	}
}
