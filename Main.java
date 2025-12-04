import java.util.Scanner;

/**
 * Código para administrar calificaciones de estudiantes en el grupo 3A.
 */
public class Main {
  public static Scanner s = new Scanner(System.in);
  static GestorCalif g = new GestorCalif("3A");

  public static void main(String[] args) {
    System.out.println("ADMINISTRADOR DE CALIFICACIONES 3A");
    seed();
    menu();
  }

  static void seed() {
    g.add("Ana");
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

  // Menú principal
  static void menu() {
    int op;
    while (true) {
      System.out.println("\n1) Listar\n2) Agregar\n3) Calificar\n4) Promedio alumno\n5) Promedio grupo\n0) Salir");
      System.out.print("Elige una opción: ");
      op = toInt(s.nextLine());

      if (op == 0) {
        System.out.print("¿Estás seguro de que quieres salir? (S/N): ");
        String confirmar = s.nextLine();
        if (confirmar.equalsIgnoreCase("S")) {
          System.out.println("Adiós");
          break; // Salir del bucle y terminar el programa
        } else {
          System.out.println("Si le sabes, Volviendo al menú.");
        }
      }

      // Lógica del menú
      switch (op) {
        case 1:
          System.out.println(g.listarTodo());
          break;
        case 2:
          System.out.print("Nombre: ");
          g.add(s.nextLine());
          System.out.println("Estudiante agregado.");
          break;
        case 3:
          System.out.print("ID: ");
          int id = toInt(s.nextLine());
          System.out.print("Materia (MAT/ESP/ING): ");
          String subject = s.nextLine();
          System.out.print("Calificación: ");
          g.set(id, subject, toInt(s.nextLine()));
          System.out.println("Calificación actualizada.");
          break;
        case 4:
          System.out.print("ID: ");
          System.out.println("Promedio: " + g.promEst(toInt(s.nextLine())));
          break;
        case 5:
          System.out.println("Promedio grupo: " + g.promGrupo());
          break;
        default:
          System.out.println("Opción no válida.");
      }
    }
  }

  static int toInt(String s) {
    try {
      return Integer.parseInt(s.trim());
    } catch (NumberFormatException e) {
      return -1;
    }
  }
}
