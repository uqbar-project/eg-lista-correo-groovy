package ar.edu.listasCorreo.observers

class BloqueoUsuarioVerbosoObserver {

	def send(post) {
		def emisor = post.emisor
		if (emisor.envioMuchosMensajes()) {
			emisor.bloquear()
		}
	}

}
