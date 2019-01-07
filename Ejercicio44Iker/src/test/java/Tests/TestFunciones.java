package Tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Scanner;

import org.junit.Test;

import Gestion.Alumno;
import Gestion.FuncionesMain;
import Gestion.LeerDatos;
import Gestion.FuncionesAlumnos;

public class TestFunciones {
	@Test
	public void testProcesarDatos() {
		String nombre = "Carlos gonzalez";
		char sexo = 'H';
		int edad = 34;
		float altura = (float) 1.78;
		float peso = (float) 67.6;
		Alumno alumno = new Alumno(nombre,sexo,edad,altura,peso);
		FuncionesMain funcMain = new FuncionesMain();
		
		Alumno[] alumnos = new Alumno[1];
		alumnos[0] = alumno;
		
		FuncionesAlumnos funcAlumnosMock = mock(FuncionesAlumnos.class);
		
		funcMain.ProcesarDatos(alumnos, funcAlumnosMock);
		
		// Verificar que está llamando a los dos métodos necesarios
		verify(funcAlumnosMock, times(1)).CalcularPromedio(alumnos);
		verify(funcAlumnosMock, times(1)).CalcularTotal(alumnos);
	}
	
	@Test
	public void testCargarDatos() {
		String nombre = "Carlos gonzalez";
		char sexo = 'H';
		int edad = 34;
		float altura = (float) 1.78;
		float peso = (float) 67.6;
		Scanner reader = new Scanner(System.in);
		Alumno alumno = new Alumno(nombre,sexo,edad,altura,peso);
		FuncionesMain funcMain = new FuncionesMain();
		
		Alumno[] alumnos = new Alumno[1];
		alumnos[0] = alumno;
		
		LeerDatos funcLeerDatosMock = mock(LeerDatos.class);
		when(funcLeerDatosMock.RecogerOpcionInt(reader,0,100,"Introduce la cantidad de alumnos: ")).thenReturn(0);
				
		Alumno[] resultado = funcMain.CargarDatos(reader, funcLeerDatosMock);
		
		assertEquals(resultado.length, 0);
	}
	
	@Test
	public void testCargarDatos2() {
		String nombre = "Carlos gonzalez";
		char sexo = 'H';
		int edad = 34;
		float altura = (float) 1.78;
		float peso = (float) 67.6;
		Scanner reader = new Scanner(System.in);
		Alumno alumno = new Alumno(nombre,sexo,edad,altura,peso);
		FuncionesMain funcMain = new FuncionesMain();
		
		Alumno[] alumnos = new Alumno[1];
		alumnos[0] = alumno;
		
		LeerDatos funcLeerDatosMock = mock(LeerDatos.class);
		when(funcLeerDatosMock.RecogerOpcionInt(reader,0,100,"Introduce la cantidad de alumnos: ")).thenReturn(1);
		when(funcLeerDatosMock.IntroducirNombre(reader)).thenReturn("Sara");
		when(funcLeerDatosMock.IntroducirValidarSexo(reader)).thenReturn('M');
		when(funcLeerDatosMock.RecogerOpcionInt(reader,0,200,"Introduce la edad: ")).thenReturn(20);
		when(funcLeerDatosMock.RecogerOpcionFloat(reader,0,(float) 2.5,"Introduce la altura: ")).thenReturn(1.60f);
		when(funcLeerDatosMock.RecogerOpcionFloat(reader,0,150,"Introduce el peso: ")).thenReturn(52f);
		
		Alumno[] resultado = funcMain.CargarDatos(reader, funcLeerDatosMock);
		
		assertEquals(resultado[0].getNombre(), "Sara");
		assertEquals(resultado[0].getSexo(), 'M');
		assertEquals(resultado[0].getEdad(), 20);
		assertEquals(resultado[0].getAltura(), 1.60f, 0);
		assertEquals(resultado[0].getPeso(), 52f, 0);
		
		// Verificar que está llamando a los dos métodos necesarios
		verify(funcLeerDatosMock, times(1)).RecogerOpcionInt(reader,0,100,"Introduce la cantidad de alumnos: ");
		
	}
}
