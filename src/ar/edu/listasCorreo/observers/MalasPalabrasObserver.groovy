package ar.edu.listasCorreo.observers

class MalasPalabrasObserver extends PostObserver {

	def malasPalabras = []
	def postConMalasPalabras = []
	
	def send(post) {
		if (tieneMalasPalabras(post.mensaje)) {
			println ("Mensaje enviado a admin por mensaje con malas palabras: " + post.mensaje)
			postConMalasPalabras.add(post)
		}
	}
	
	def tieneMalasPalabras(message) {
		malasPalabras.any { mala -> palabras(message).contains(mala) }
	}
	
	def agregarMalaPalabra(palabra) {
		malasPalabras.add(palabra)
	}
	
	def palabras(message) {
		message.split(" ")
	}
	
	def getMensajesConMalasPalabras() {
		postConMalasPalabras
	}

}
