package ar.edu.listaCorreo

import ar.edu.listaCorreo.exceptions.BusinessException


class ListaRestringida {

	def validarEnvio(post, lista) {
		if (!lista.estaSuscripto(post.emisor)) {
			throw new BusinessException("El usuario no está suscripto")
		}
	}
				
}
