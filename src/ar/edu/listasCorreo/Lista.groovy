package ar.edu.listasCorreo

import ar.edu.listasCorreo.suscripcion.SuscripcionAbierta
import ar.edu.listasCorreo.suscripcion.SuscripcionCerrada

class Lista {
	def miembros
	def tipoEnvio
	def postObservers
	def encabezado
	def tipoSuscripcion
	
	def static Lista listaAbierta() {
		new Lista()
	}	

	def static Lista listaCerrada() {
		new Lista(tipoEnvio: new ListaRestringida(), tipoSuscripcion: new SuscripcionCerrada())
	}	

	def suscribir(miembro)  {
		tipoSuscripcion.suscribir(miembro, this)
	}
		
	def aprobarSuscripcion(miembro)  {
		tipoSuscripcion.aprobarSuscripcion(miembro, this)
	}
	
	/** Constructor, por default toda lista es abierta */
	public Lista() {
		miembros = []
		tipoEnvio = new ListaAbierta()
		postObservers = []
		tipoSuscripcion = new SuscripcionAbierta()
	}
	
	def void agregarMiembro(miembro) {
		miembros.add(miembro)
	}
	
	def void agregarPostObserver(postObserver) {
		postObservers.add(postObserver)
	}
	
	def enviar(post) {
		tipoEnvio.validarEnvio(post, this)
		postObservers.each { sender -> sender.send(post) }
	}
	
	def getDestinatarios(post) {
		miembros.findAll { miembro -> !miembro.equals(post.emisor) }
	}
	
	def getMailsDestino(post) {
		getDestinatarios(post).collect { miembro -> miembro.mail }
	}
	
	def estaSuscripto(miembro) {
		miembros.contains(miembro)	
	}
	
}