package ar.edu.listasCorreo.suscripcion

class SuscripcionCerrada {

	def miembrosPendientes = []
	
	def suscribir(miembro, lista) {
		this.agregarMiembrosPendientes(miembro)
	}
	

	def aprobarSuscripcion(miembro, lista) {
		this.eliminarMiembrosPendientes(miembro)
		lista.agregarMiembro(miembro)
	}

	def agregarMiembrosPendientes(miembro) {
		miembrosPendientes.add(miembro)
	}

	def eliminarMiembrosPendientes(miembro) {
		miembrosPendientes.remove(miembro)
	}

}
