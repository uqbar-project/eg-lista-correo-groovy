package ar.edu.listaCorreo

class ListaCorreoBuilder {

	def lista = new ListaCorreo()
	
	def abierta() {
		lista.modoSuscripcion = {miembro -> lista.agregarMiembro(miembro)}
		this
	}
	
	def cerrada() {
		lista.metaClass { 
			miembrosPendientes = []
			aprobar = {miembro -> miembrosPendientes.remove(miembro) 	
								lista.agregarMiembro(miembro)}
			rechazar = {miembro -> miembrosPendientes.remove(miembro)} 
		}
		
		lista.modoSuscripcion = {miembro -> lista.miembrosPendientes.add(miembro)}
		this
	}
	
	def libre() {
		this
		//por default es libre, le dejo el metodo para que quede consistente desde el exterior
	}
	
	def restringida() {
		lista = new ListaRestringida(listaDecorada:lista)
		this
	}
	
	
	def build() {
		return lista
	}
}
