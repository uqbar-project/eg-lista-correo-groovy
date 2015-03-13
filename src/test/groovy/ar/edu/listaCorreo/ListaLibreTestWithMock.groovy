package ar.edu.listaCorreo

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import org.junit.Before
import org.junit.Test

import ar.edu.listaCorreo.mails.Email
import ar.edu.listaCorreo.mails.EmailSenderImplementation

class ListaLibreTestWithMock {
	
	def lista
	def emailSender
	
	@Before
	void setUp() {
		emailSender = mock(EmailSenderImplementation.class)
		
		EmailSenderProvider.emailSender = emailSender
		lista = new ListaCorreoBuilder().abierta().libre().build()
		lista.agregarMiembro(new Miembro(mailDefault:"lgassman@gmail.com"))
		lista.agregarMiembro(new Miembro(mailDefault:"npasserini@gmail.com"))
		lista.agregarMiembro(new Miembro(mailDefault:"tesonep@gmail.com"))
	}
	
	@Test 
	void enviarExterno() throws Exception {
		def post = new Post(from:"fulano@gmail.com", subject:"hola", content:"yeah")
		lista.enviar(post)
		verify(emailSender, times(3)).send(any(Email.class))
	}

	@Test 
	void enviarInterno() throws Exception {
		def post = new Post(from:"lgassman@gmail.com", subject:"hola", content:"yeah")
		lista.enviar(post)
		verify(emailSender, times(2)).send(any(Email.class))
	}


}
