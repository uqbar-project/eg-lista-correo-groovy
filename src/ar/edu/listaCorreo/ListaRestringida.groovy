package ar.edu.listaCorreo

//Es un decorator para el método enviar
class ListaRestringida {

	def listaDecorada
	
	def methodMissing(String method, args) {
		listaDecorada.invokeMethod(method, args)
	}
	
	def propertyMissing(String name) {
		listaDecorada."$name"
	}
	
	def enviar(post) {
		if(!this.remitentePerteneceALaLista(post)) {
			throw new RuntimeException("En la lista restringida solo se permite enviar mails a sus miembros")
		}
		listaDecorada.enviar(post)
	}
}
