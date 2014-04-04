package ar.edu.listasCorreo

import static org.mockito.Matchers.*
import static org.mockito.Mockito.*

import org.junit.Assert
import org.junit.Before
import org.junit.Test

import ar.edu.listaCorreo.Lista
import ar.edu.listaCorreo.Miembro
import ar.edu.listaCorreo.Post
import ar.edu.listaCorreo.config.ServiceLocator
import ar.edu.listaCorreo.exceptions.BusinessException
import ar.edu.listaCorreo.observers.Mail
import ar.edu.listaCorreo.observers.MailObserver
import ar.edu.listaCorreo.observers.MalasPalabrasObserver
import ar.edu.listaCorreo.observers.MessageSender
import ar.edu.listaCorreo.observers.StubMailSender

class TestEnvioPosts {

	Lista listaProfes
	Lista listaAlumnos
	Miembro dodain
	Miembro nico
	Miembro deby
	Miembro alumno
	Miembro fede
	Post mensajeAlumno
	Post mensajeDodainAlumnos
	Post mensajeDodainProfes
	StubMailSender stubMailSender = new StubMailSender()
	MalasPalabrasObserver malasPalabrasObserver = new MalasPalabrasObserver()

	@Before
	def void init() {

		/** Listas de correo */
		listaAlumnos = Lista.listaAbierta()
		listaProfes = Lista.listaCerrada()

		/** Profes */
		dodain = new Miembro("fernando.dodino@gmail.com")
		nico = new Miembro("nicolas.passerini@gmail.com")
		deby = new Miembro("debyfortini@gmail.com")

		/** Alumnos **/
		alumno = new Miembro("alumno@uni.edu.ar")
		fede = new Miembro("fede@uni.edu.ar")

		ServiceLocator.instance.messageSender = stubMailSender
		
		/** en la lista de profes están los profes */
		listaProfes.agregarMiembro(dodain)
		listaProfes.agregarMiembro(nico)
		listaProfes.agregarMiembro(deby)
		listaProfes.agregarPostObserver(new MailObserver())

		/** en la de alumnos hay alumnos y profes */
		listaAlumnos.agregarMiembro(dodain)
		listaAlumnos.agregarMiembro(deby)
		listaAlumnos.agregarMiembro(fede)
		listaAlumnos.agregarPostObserver(new MailObserver())
		listaAlumnos.agregarPostObserver(malasPalabrasObserver)

		mensajeAlumno = new Post(alumno, "Hola, queria preguntar que es la recursividad", listaProfes)
		mensajeDodainAlumnos = new Post(dodain,
			"Para explicarte recursividad tendría que explicarte qué es la recursividad", listaAlumnos)
		mensajeDodainProfes = new Post(dodain, "Cuantos TPs hacemos?", listaProfes)
	}

	/*************************************************************/
	/*                     TESTS CON STUBS                       */
	/*                      TEST DE ESTADO                       */
	/*************************************************************/
	@Test(expected=BusinessException.class)
	def void alumnoNoPuedeEnviarPostAListaProfes() {
		listaProfes.enviar(mensajeAlumno)
	}

	@Test
	def void alumnoPuedeEnviarMailAListaAbierta() {
		Assert.assertEquals(0, stubMailSender.mailsDe("alumno@uni.edu.ar").size())
		listaAlumnos.enviar(mensajeAlumno)
		Assert.assertEquals(1, stubMailSender.mailsDe("alumno@uni.edu.ar").size())
	}

	@Test
	def void alumnoEnviaMailConMalaPalabra() {
		def mensajeFeo = new Post(alumno, "Cuál es loco! Me tienen podrido", listaAlumnos)
		malasPalabrasObserver.agregarMalaPalabra("podrido")
		listaAlumnos.enviar(mensajeFeo)
		Assert.assertEquals(1, malasPalabrasObserver.mensajesConMalasPalabras.size())
	}

	/*************************************************************/
	/*                     TESTS CON MOCKS                       */
	/*                  TEST DE COMPORTAMIENTO                   */
	/*************************************************************/
	@Test
	def void testEnvioPostAListaAlumnosLlegaATodosLosOtrosSuscriptos() {
		//creacion de mock
		def mockedMailSender = mock(MessageSender.class)
		ServiceLocator.instance.messageSender = mockedMailSender

		// un alumno envía un mensaje a la lista
		listaAlumnos.enviar(mensajeDodainAlumnos)

		//verificacion
		//test de comportamiento, verifico que se enviaron 3 mails
		verify(mockedMailSender, times(2)).send(any(Mail.class))
	}

}
