import java.util.Scanner;

/**
 *Código para administrar calificaciones de estudiantes en el grupo 3A.
 *Ya tiene las reglas de lintter y tiene tambien el checkstyle aplicado.
 */
public class Main {
  public static Scanner S = new Scanner(System.in);
  static GestorCalif g = new GestorCalif("3A");

  /**
   * Método principal del programa para tener datos de estudiantes.
   */
  public static void main(String[] a) throws Exception {
    System.out.println("ADMINISTRADOR DE CALIFICACIONES 3A (feo a propósito)");
    System.out.println("Materias válidas: MAT, ESP, ING");
    seed();
    menu();
  }

  // Datos de ejemplo para correr el programa
  static void seed() {
    g.add("Anabell");
    g.add("Luis");
    g.add("Sofia");

    g.set(1, "MAT", 95);
    g.set(1, "ESP", 88);
    g.set(1, "ING", 100);

    g.set(2, "MAT", 70);
    g.set(2, "ESP", 70);
    g.set(2, "ING", 70);

    g.set(3, "MAT", 60);
    g.set(3, "ESP", 65);
    g.set(3, "ING", 40);
  }

  static void menu() {
    int op = 0;
    String tmp = null;

    while (true) {
      System.out.println(
            "\n1) listar"
            + "\n2) agregar"
            + "\n3) calificar"
            + "\n4) promedio alumno"
            + "\n5) promedio grupo"
            + "\n6) aprobados"
            + "\n7) mejor"
            + "\n8) reprobados por materia"
            + "\n9) reporte"
            + "\n0) salir"
      );
      System.out.print("op: ");
      try {
        tmp = S.nextLine();
        if (tmp == null) {
          tmp = "";
        }
        op = Integer.parseInt(tmp.trim());
      } catch (Exception e) {
        op = -999;
      }

      if (op == 0) {
        break;
      }

      switch (op) {
        case 1:
          System.out.println(g.listarTodo());
          break;
        case 2:
          System.out.print("nombre: ");
          String n = S.nextLine();
          g.add(n);
          System.out.println("ok");
          break;
        case 3:
          System.out.print("id: ");
          int id = toInt(S.nextLine());
          System.out.print("materia(MAT/ESP/ING): ");
          String m = S.nextLine();
          System.out.print("calif: ");
          int c = toInt(S.nextLine());
          g.set(id, m, c);
          System.out.println("listo");
          break;
        case 4:
          System.out.print("id: ");
          int id2 = toInt(S.nextLine());
          System.out.println("prom=" + g.promEst(id2));
          break;
        case 5:
          System.out.println("promGrupo=" + g.promGrupo());
          break;
        case 6:
          System.out.println("aprobados=" + g.aprobados());
          break;
        case 7:
          System.out.println("mejor=" + g.mejor());
          break;
        case 8:
          System.out.print("materia(MAT/ESP/ING): ");
          String mm = S.nextLine();
          System.out.println("reprobados=" + g.rep(mm));
          break;
        case 9:
          System.out.println(g.reporte());
          break;
        default:
          System.out.println("no");
          break;
      }
    }
    System.out.println("bye");
  }

  static int toInt(String s) {
    try {
      return Integer.parseInt(s.trim());
    } catch (Exception e) {
      return -1;
    }
  }
}