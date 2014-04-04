package ar.edu.listaCorreo

import static org.junit.Assert.*

import org.junit.Assert
import org.junit.Before
import org.junit.Test

import ar.edu.listaCorreo.ListaCorreoBuilder;
import ar.edu.listaCorreo.Miembro;

class ListaCerradaTest {
	
	def lista
	def miembro
	
	@Before
	void setUp() {
		lista = new ListaCorreoBuilder().cerrada().libre().build()
		miembro = new Miembro(mailDefault:"lgassman@gmail.com")
	}
	
	@Test
	void suscribir() throws Exception {
		lista.suscribir(miembro)
		Assert.assertFalse(lista.miembros.contains(miembro))
		Assert.assertTrue(lista.miembrosPendientes.contains(miembro))	
	}

	@Test
	void aprobar() throws Exception {
		lista.suscribir(miembro)
		lista.aprobar(miembro)
		Assert.assertTrue(lista.miembros.contains(miembro))
		Assert.assertFalse(lista.miembrosPendientes.contains(miembro))
	}
	
	@Test
	void rechazar() throws Exception {
		lista.suscribir(miembro)
		lista.rechazar(miembro)
		Assert.assertFalse(lista.miembros.contains(miembro))
		Assert.assertFalse(lista.miembrosPendientes.contains(miembro))
	}



}
