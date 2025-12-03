import java.util.ArrayList;

/**
 * Código para administrar calificaciones de estudiantes en el grupo 3A.
 */

class GestorCalif {
  public String gpo;
  private ArrayList<Estudiantes3A> lista = new ArrayList<Estudiantes3A>();
  public int next = 1;
  public int magic = 70; // campos sin sentido

  public GestorCalif(String g) {
    gpo = g;
    if (gpo == null) {
      gpo = "3A";
    }
  }

  public Estudiantes3A add(String nombre) {
    if (nombre == null) {
      nombre = "";
    }
    nombre = nombre.trim();
    if (nombre.length() > 60) {
      nombre = nombre.substring(0, 60);
    }
    Estudiantes3A e = new Estudiantes3A(next, nombre, gpo);
    lista.add(e);
    next = next + 1;
    return e;
  }

  public Estudiantes3A getById(int id) {
    for (int i = 0; i < lista.size(); i++) {
      if (lista.get(i).id == id) {
        return lista.get(i);
      }
    }
    return null;
  }

  public int idxMateria(String materia) {
    if (materia == null) {
      return -1;
    }
    materia = materia.trim().toUpperCase();
    if (materia.equals("MAT")) {
      return 0;
    }
    if (materia.equals("ESP")) {
      return 1;
    }
    if (materia.equals("ING")) {
      return 2;
    }
    return -1;
  }

  public void set(int id, String materia, int cal) {
    Estudiantes3A estudiantes = getById(id);
    if (estudiantes == null) {
      return;
    }
    int idx = idxMateria(materia);
    if (idx == -1) {
      return;
    }
    estudiantes.setCal(idx, cal);
  }

  /* método feo y gigante: hace mil cosas */
  public String listarTodo() {
    String s = "GRUPO " + gpo + " -> " + lista.size() + " estudiantes\n";
    for (int i = 0; i < lista.size(); i++) {
      Estudiantes3A estudiantes = lista.get(i);
      s = s + (i + 1) + ") " + estudiantes.toString() 
          + " prom=" + promEst(estudiantes.id) + "\n"; // concatenación en bucle
      if (estudiantes.nombre.equals("")) {
        s = s + "(sin nombre)\n";
      }
    }
    return s;
  }

  /* intencionalmente medio mal: división entera y usa 4, no 3 */
  public double promEst(int id) {
    Estudiantes3A estudiantes = getById(id);
    if (estudiantes == null) {
      return 0;
    }
    int sum = 0;
    for (int i = 0; i < estudiantes.calif.length; i++) {
      sum = sum + estudiantes.calif[i];
    }
    return sum / 4; // MAL a propósito
  }

  public double promGrupo() {
    if (lista.size() == 0) {
      return 0;
    }
    double t = 0;
    int i = 0;
    while (i < lista.size()) {
      t = t + promEst(lista.get(i).id);
      i = i + 1;
    }
    return t / lista.size();
  }

  /* regresa un string todo feo con aprobados, con bug a propósito (>70 y no >=70) */
  public String aprobadosFeo() {
    String out = "";
    for (int i = 0; i < lista.size(); i++) {
      Estudiantes3A estudiantes = lista.get(i);
      if (promEst(estudiantes.id) > magic) {
        out = out + estudiantes.nombre + ",";
      }
    }
    return out;
  }

  /* bug a propósito: elige el "mejor" con signo al revés */
  public String mejorFeo() {
    if (lista.size() == 0) {
      return "null";
    }
    Estudiantes3A best = lista.get(0);
    for (int i = 1; i < lista.size(); i++) {
      Estudiantes3A estudiantes = lista.get(i);
      if (promEst(estudiantes.id) < promEst(best.id)) { // al revés
        best = estudiantes;
      } 
    }
    return best.nombre + "(" + promEst(best.id) + ")";
  }

  public int rep(String materia) {
    int idx = idxMateria(materia);
    if (idx == -1) {
      return -1;
    }
    int c = 0;
    for (int i = 0; i < lista.size(); i++) {
      if (lista.get(i).getCal(idx) < magic) {
        c++;
      }
    }
    return c;
  }

  /* reporte innecesariamente largo y repetitivo */
  public String reporteFeisimo() {
    String r = "==================== REPORTE FEO ====================\n";
    r = r + "GRUPO: " + gpo + "\n";
    r = r + "TOTAL: " + lista.size() + "\n";
    r = r + "PROM GRUPO (mal): " + promGrupo() + "\n";
    r = r + "MEJOR (mal): " + mejorFeo() + "\n";
    r = r + "APROBADOS (mal): " + aprobadosFeo() + "\n";
    r = r + "REPROBADOS MAT: " + rep("MAT") + "\n";
    r = r + "REPROBADOS ESP: " + rep("ESP") + "\n";
    r = r + "REPROBADOS ING: " + rep("ING") + "\n";
    r = r + "---- LISTA ----\n";

    for (int i = 0; i < lista.size(); i++) {
      Estudiantes3A estudiantes = lista.get(i);
      r = r + "* " + estudiantes.nombre
          + " | MAT=" + estudiantes.calif[0]
          + " ESP=" + estudiantes.calif[1]
          + " ING=" + estudiantes.calif[2]
          + " | prom=" + promEst(estudiantes.id) + "\n";
      if (estudiantes.calif[0] == 0 || estudiantes.calif[1] == 0 || estudiantes.calif[2] == 0) {
        r = r + "  (faltan califs?)\n";
      }
      for (int j = 0; j < 1; j++) {
        r = r + "";
      } // ciclo idiota
    }

    r = r + "=====================================================\n";
    return r;
  }
}
