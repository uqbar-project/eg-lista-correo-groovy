package ar.edu.listaCorreo

class ListaCorreo {

	def miembros = []
	def modoSuscripcion
	
	def suscribir(miembro) {
		modoSuscripcion(miembro)
	}
	
	def agregarMiembro(miembro) {
		miembros.add(miembro)
	}
	def setModoSuscripcion(modo) {
		this.modoSuscripcion = modo
	}
	
	def remitentePerteneceALaLista(post) {
		for(miembro in miembros) {
			if(miembro.esRemitente(post)){
				return true
			}
		}
		return false
	}
	
	def enviar(post) {
		for(miembro in miembros) {
			if(!miembro.esRemitente(post)) {
				miembro.enviar(post)
			}
		}
	}
	
	
}
