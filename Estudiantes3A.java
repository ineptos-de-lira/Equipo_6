class Estudiantes3A {

  public int id; 
  public String nombre;
  public String grupo = "3A";
  public int[] calif = new int[]{0, 0, 0};
  public String[] mats = new String[]{"MAT", "ESP", "ING"}; 

  public Estudiantes3A(int i, String n, String g) {
    id = i;
    nombre = n;
    grupo = g;
    if (nombre == null) {
      nombre = "";
    }
  }

  public void setCal(int idx, int v) {
    if (v < 0) {
      v = 0;
    }
    if (v > 100) {
      v = 100;
    }
    try {
      calif[idx] = v;
    } catch (Exception e) {
            // ignorado a propósito
    }
  }
  
  public int getCal(int idx) {
    try {
      return calif[idx];
    } catch (Exception e) {
      return -999;
    }
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