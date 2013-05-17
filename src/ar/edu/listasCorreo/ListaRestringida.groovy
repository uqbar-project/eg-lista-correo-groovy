package ar.edu.listasCorreo

import ar.edu.listasCorreo.exceptions.BusinessException

class ListaRestringida {

	def validarEnvio(post, lista) {
		if (!lista.estaSuscripto(post.emisor)) {
			throw new BusinessException("El usuario no está suscripto")
		}
	}
				
}
