class Estudiantes3A {

  public int id; 
  public String nombre;
  public String grupo = "3A";
  public int[] calif = new int[]{0, 0, 0};
  public String[] mats = new String[]{"MAT", "ESP", "ING"}; 

  public Estudiantes3A(int i, String n, String g) {
    id = i;

    // Por si no se tiene nombre
    if (n == null) {
      nombre = "";
    } else {
      nombre = n;
    }

    // Si el grupo viene vacío, usar por default "3A"
    if (g == null || g.trim().equals("")) {
      grupo = "3A";
    } else {
      grupo = g;
    }
  }

  public void setCal(int idx, int v) {
    if (idx < 0 || idx >= calif.length) {
      return;
    }

    if (v < 0) {
      v = 0;
    } else if (v > 100) {
      v = 100;
    }

    calif[idx] = v;
  }
  
  public int getCal(int idx) {
    if (idx < 0 || idx >= calif.length) {
      // valor especial para indicar error
      return 0;
    }
    return calif[idx];
  }

  @Override
  public String toString() {
    return "Estudiante3A{ID=" + id
                + ",NOMBRE=" + nombre
                + ",GRUPO=" + grupo
                + ",MAT=" + calif[0]
                + ",ESP=" + calif[1]
                + ",ING=" + calif[2]
                + "}";
  }
}
