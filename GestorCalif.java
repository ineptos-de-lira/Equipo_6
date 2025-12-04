import java.util.ArrayList;

class GestorCalif {
  public String gpo;
  private ArrayList<Estudiantes3A> lista = new ArrayList<>();
  public int next = 1;
  public int magic = 70;

  public GestorCalif(String g) {
    gpo = (g != null) ? g : "3A"; // Asignación simplificada
  }

  public Estudiantes3A add(String nombre) {
    nombre = (nombre == null) ? "" : nombre.trim();
    nombre = (nombre.length() > 60) ? nombre.substring(0, 60) : nombre;
    Estudiantes3A e = new Estudiantes3A(next++, nombre, gpo);
    lista.add(e);
    return e;
  }

  public Estudiantes3A getId(int id) {
    for (Estudiantes3A estudiante : lista) {
      if (estudiante.id == id) {
        return estudiante;
      }
    }
    return null;
  }

  public int idxMateria(String materia) {
    int idx = -1;
    if (materia != null) {
      materia = materia.trim().toUpperCase();
      if (materia.equals("MAT")) {
        idx = 0;
      } else if (materia.equals("ESP")) {
        idx = 1;
      } else if (materia.equals("ING")) {
        idx = 2;
      }
    }
    return idx;
  }

  public void set(int id, String materia, int cal) {
    Estudiantes3A estudiante = getId(id);
    if (estudiante != null) {
      int idx = idxMateria(materia);
      if (idx != -1) {
        estudiante.setCal(idx, cal);
      }
    }
  }

  public String listarTodo() {
    StringBuilder s = new StringBuilder("GRUPO " + gpo + " -> " + lista.size() + " estudiantes\n");
    for (int i = 0; i < lista.size(); i++) {
      Estudiantes3A estudiante = lista.get(i);
      s.append(i + 1).append(") ")
      .append(estudiante).append(" prom=")
      .append(promEst(estudiante.id)).append("\n");
      if (estudiante.nombre.isEmpty()) {
        s.append("(sin nombre)\n");
      }
    }
    return s.toString();
  }

  public double promEst(int id) {
    Estudiantes3A estudiante = getId(id);
    if (estudiante == null) {
      return 0;
    }
    int sum = 0;
    for (int cal : estudiante.calif) {
      sum += cal;
    }
    return sum / (double) estudiante.calif.length;
  }

  public double promGrupo() {
    if (lista.isEmpty()) {
      return 0;
    }
    double total = 0;
    for (Estudiantes3A estudiante : lista) {
      total += promEst(estudiante.id);
    }
    return total / lista.size();
  }

  public String aprobados() {
    StringBuilder out = new StringBuilder();
    for (Estudiantes3A estudiante : lista) {
      if (promEst(estudiante.id) >= magic) {
        out.append(estudiante.nombre).append(",");
      }
    }
    return out.toString();
  }

  public String mejor() {
    if (lista.isEmpty()) {
      return "null";
    }
    Estudiantes3A best = lista.get(0);
    for (Estudiantes3A estudiante : lista) {
      if (promEst(estudiante.id) > promEst(best.id)) {
        best = estudiante;
      }
    }
    return best.nombre + "(" + promEst(best.id) + ")";
  }

  public int rep(String materia) {
    int idx = idxMateria(materia);
    if (idx == -1) {
      return -1;
    }
    int count = 0;
    for (Estudiantes3A estudiante : lista) {
      if (estudiante.getCal(idx) < magic) {
        count++;
      }
    }
    return count;
  }

  public String reporte() {
    StringBuilder r = new StringBuilder("==================== REPORTE ====================\n");
    r.append("GRUPO: ").append(gpo).append("\n")
      .append("TOTAL: ").append(lista.size()).append("\n")
      .append("PROM GRUPO: ").append(promGrupo()).append("\n")
      .append("MEJOR: ").append(mejor()).append("\n")
      .append("APROBADOS: ").append(aprobados()).append("\n")
      .append("REPROBADOS MAT: ").append(rep("MAT")).append("\n")
      .append("REPROBADOS ESP: ").append(rep("ESP")).append("\n")
      .append("REPROBADOS ING: ").append(rep("ING")).append("\n")
      .append("---- LISTA ----\n");
    for (Estudiantes3A estudiante : lista) {
        r.append("* ").append(estudiante.nombre)
        .append(" | MAT=").append(estudiante.calif[0])
        .append(" ESP=").append(estudiante.calif[1])
        .append(" ING=").append(estudiante.calif[2])
        .append(" | prom=").append(promEst(estudiante.id)).append("\n");
    }
    r.append("=====================================================\n");
    return r.toString();
  }
}
