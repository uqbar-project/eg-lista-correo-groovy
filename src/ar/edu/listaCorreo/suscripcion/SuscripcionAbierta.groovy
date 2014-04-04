package ar.edu.listaCorreo.suscripcion

class SuscripcionAbierta {

	def suscribir(miembro, lista) {
		lista.agregarMiembro(miembro)
	}
	

	def aprobarSuscripcion(miembro, lista) {
		throw new UnsupportedOperationException("No se deben aprobar suscripciones de listas de suscripción abierta")
	}

}
