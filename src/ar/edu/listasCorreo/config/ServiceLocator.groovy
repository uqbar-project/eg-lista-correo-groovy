package ar.edu.listasCorreo.config

class ServiceLocator {

	def messageSender
	
	/*****************************************
	 * SINGLETON
	 *****************************************/
	static def instance
	
	private ServiceLocator() {
		
	}
	
	static def getInstance() {
		if (!instance) {
			instance = new ServiceLocator()
		}
		instance
	}
}
