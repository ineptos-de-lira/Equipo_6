import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para todos los archivos.
 */
public class PruebaUnitariaMain {

  @Test
    public void testAgregar() {
    GestorCalif gestor = new GestorCalif("3B");
    Estudiantes3A estudiante = gestor.add("   Juan Perez   ");
    assertNotNull(estudiante);
    assertEquals("Juan Perez", estudiante.nombre);
    assertEquals("3B", estudiante.grupo);
  } 

  @Test
    public void testPromedio() {
    GestorCalif gestor = new GestorCalif("3A");
    Estudiantes3A estudiante = gestor.add("Ana");

    gestor.set(estudiante.id, "MAT", 100);
    gestor.set(estudiante.id, "ESP", 70);
    gestor.set(estudiante.id, "ING", 40);
    
    double promedioEsperado = 70.0;

    assertEquals(promedioEsperado, gestor.promEst(estudiante.id), 0.01);
  }

  @Test
    public void testRepCuentaReprobadosEnMateria() {
    GestorCalif gestor = new GestorCalif("3A");
    Estudiantes3A estudiante1 = gestor.add("Ana");
    Estudiantes3A estudiante2 = gestor.add("Beto");

    gestor.set(estudiante1.id, "MAT", 60); // reprobado
    gestor.set(estudiante2.id, "MAT", 80); // aprobado

    int reprobadosMat = gestor.rep("MAT");
    
    assertEquals(1, reprobadosMat);
  }

  @Test
    public void testNombre() {
    // Creamos un estudiante con nombre null
    Estudiantes3A estudiante = new Estudiantes3A(1, null, "3A");

    // Verificamos que el nombre sea cadena vacía
    assertEquals("", estudiante.nombre);
  }

  @Test
    public void testGrupoVacio() {
    // Creamos un estudiante con grupo vacío
    Estudiantes3A estudiante = new Estudiantes3A(2, "Ana", "   ");

    // Verificamos que el grupo sea "3A" por defecto
    assertEquals("3A", estudiante.grupo);
  }

  @Test
    public void testSetCalFueraDeRangoNoCambiaCalificaciones() {
    Estudiantes3A estudiante = new Estudiantes3A(3, "Luis", "3A");

    // Intentamos setear calificaciones en índices inválidos
    estudiante.setCal(-1, 80);
    estudiante.setCal(5, 90);

    // Verificamos que las calificaciones sigan en 0
    assertEquals(0, estudiante.getCal(0));
    assertEquals(0, estudiante.getCal(1));
    assertEquals(0, estudiante.getCal(2));
  }

  @Test
    public void testToIntConvierteNumeroConEspacios() {
    // Convertimos un número válido con espacios
    int valor = Main.toInt("   123   ");

    assertEquals(123, valor);
  }

  @Test
    public void testToIntRegresaMenosUnoSiNoEsNumero() {
    
    int valor = Main.toInt("no es numero");
    assertEquals(-1, valor);
  }
}