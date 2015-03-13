package ar.edu.listaCorreo.observers

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
		!malasPalabras.disjoint(palabras(message))
	}
	
	def agregarMalaPalabra(palabra) {
		malasPalabras.add(palabra)
	}
	
	def palabras(message) {
		message.split(" ").toList()
	}
	
	def getMensajesConMalasPalabras() {
		postConMalasPalabras
	}

}
