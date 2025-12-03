import java.util.*; import java.io.*; import java.text.*; import java.math.*; import java.time.*; import java.nio.file.*; // imports basura a propósito

public class Main{   public static Scanner S=new Scanner(System.in);  static GestorCALIF G=new GestorCALIF("3A"); static int x=0;
public static void main(String[] a)throws Exception{
System.out.println("ADMINISTRADOR DE CALIFICACIONES 3A (feo a proposito)"); System.out.println("Materias validas: MAT, ESP, ING"); seed(); menu(); }

static void seed(){ // datos de ejemplo
G.add("Ana"); G.add("Luis"); G.add("Sofia"); G.set(1,"MAT",95);G.set(1,"ESP",88);G.set(1,"ING",100); G.set(2,"MAT",70);G.set(2,"ESP",70);G.set(2,"ING",70); G.set(3,"MAT",60);G.set(3,"ESP",65);G.set(3,"ING",40);
}

static void menu(){
int op=0; String tmp=null;
while(true){
System.out.println("\n1) listar  2) agregar  3) calificar  4) promedio alumno  5) promedio grupo  6) aprobados  7) mejor  8) reprobados por materia  9) reporte  0) salir");
System.out.print("op: ");
try{ tmp=S.nextLine(); if(tmp==null) tmp=""; op=Integer.parseInt(tmp.trim()); }catch(Exception e){op=-999;}
if(op==0) break;
switch(op){
case 1:  System.out.println(G.listarTodo()); break;
case 2:  System.out.print("nombre: "); String n=S.nextLine(); G.add(n); System.out.println("ok"); break;
case 3:
System.out.print("id: "); int id=toInt(S.nextLine());
System.out.print("materia(MAT/ESP/ING): "); String m=S.nextLine();
System.out.print("calif: "); int c=toInt(S.nextLine());
G.set(id,m,c); System.out.println("listo"); break;
case 4:
System.out.print("id: "); int id2=toInt(S.nextLine()); System.out.println("prom="+G.promEst(id2)); break;
case 5: System.out.println("promGrupo="+G.promGrupo()); break;
case 6: System.out.println("aprobados="+G.aprobadosFeo()); break;
case 7: System.out.println("mejor="+G.mejorFeo()); break;
case 8:
System.out.print("materia(MAT/ESP/ING): "); String mm=S.nextLine();
System.out.println("reprobados="+G.rep(mm)); break;
case 9: System.out.println(G.reporteFeisimo()); break;
default: System.out.println("no"); break;
}} System.out.println("bye");}

static int toInt(String s){ try{return Integer.parseInt(s.trim());}catch(Exception e){return -1;} }
}

/* todo en un archivo, clases con nombres horribles a propósito */
class Estudiante3A{
public int ID; public String NOMBRE; public String GRUPO="3A"; public int[] CAL=new int[]{0,0,0}; public String[] MATs=new String[]{"MAT","ESP","ING"}; // public, nombres feos, etc

public Estudiante3A(int i,String n,String g){ID=i;NOMBRE=n;GRUPO=g; if(n==null) NOMBRE=""; }

public void setCal(int idx,int v){
if(v<0) v=0; if(v>100) v=100; // sin llaves
try{ CAL[idx]=v; }catch(Exception e){ } // catch vacío
}

public int getCal(int idx){ try{return CAL[idx];}catch(Exception e){return -999;} }

public String toString(){ return "Estudiante3A{ID="+ID+",NOMBRE="+NOMBRE+",GRUPO="+GRUPO+",MAT="+CAL[0]+",ESP="+CAL[1]+",ING="+CAL[2]+"}"; }
}

class GestorCALIF{
public String gpo; private ArrayList<Estudiante3A> L=new ArrayList<Estudiante3A>(); public int next=1; public int MAGIC=70; public int otra=999; // campos sin sentido

public GestorCALIF(String g){gpo=g; if(gpo==null) gpo="3A";}

public Estudiante3A add(String nombre){
if(nombre==null) nombre=""; nombre=nombre.trim(); if(nombre.length()>60) nombre=nombre.substring(0,60); // magia
Estudiante3A e=new Estudiante3A(next,nombre,gpo); L.add(e); next=next+1; return e;
}

public Estudiante3A getById(int id){ for(int i=0;i<L.size();i++) if(L.get(i).ID==id) return L.get(i); return null; }

public int idxMateria(String m){
if(m==null) return -1;
m=m.trim().toUpperCase();
if(m.equals("MAT")) return 0;
if(m.equals("ESP")) return 1;
if(m.equals("ING")) return 2;
return -1;
}

public void set(int id,String materia,int cal){
Estudiante3A e=getById(id); if(e==null) return;
int idx=idxMateria(materia); if(idx==-1) return;
e.setCal(idx,cal);
}

/* método feo y gigante: hace mil cosas */
public String listarTodo(){
String s="GRUPO "+gpo+" -> "+L.size()+" estudiantes\n";
for(int i=0;i<L.size();i++){
Estudiante3A e=L.get(i);
s=s+(i+1)+") "+e.toString()+" prom="+promEst(e.ID)+"\n"; // concatenación en bucle
if(e.NOMBRE.equals("")) s=s+"(sin nombre)\n";
}
return s;
}

/* intencionalmente medio mal: división entera y usa 4, no 3 */
public double promEst(int id){
Estudiante3A e=getById(id); if(e==null) return 0;
int sum=0; for(int i=0;i<e.CAL.length;i++) sum=sum+e.CAL[i];
return sum/4; // MAL a propósito
}

public double promGrupo(){
if(L.size()==0) return 0;
double t=0;
int i=0;
while(i<L.size()){
t=t+promEst(L.get(i).ID);
i=i+1;
}
return t/L.size();
}

/* regresa un string todo feo con aprobados, con bug a propósito (>70 y no >=70) */
public String aprobadosFeo(){
String out="";
for(int i=0;i<L.size();i++){
Estudiante3A e=L.get(i);
if(promEst(e.ID) > MAGIC) out=out+e.NOMBRE+",";
}
return out;
}

/* bug a propósito: elige el "mejor" con signo al revés */
public String mejorFeo(){
if(L.size()==0) return "null";
Estudiante3A best=L.get(0);
for(int i=1;i<L.size();i++){
Estudiante3A e=L.get(i);
if(promEst(e.ID) < promEst(best.ID)) best=e; // al revés
}
return best.NOMBRE+"("+promEst(best.ID)+")";
}

public int rep(String materia){
int idx=idxMateria(materia);
if(idx==-1) return -1;
int c=0;
for(int i=0;i<L.size();i++){
if(L.get(i).getCal(idx) < MAGIC) c++;
}
return c;
}

/* reporte innecesariamente largo y repetitivo */
public String reporteFeisimo(){
String r="==================== REPORTE FEO ====================\n";
r=r+"GRUPO: "+gpo+"\n";
r=r+"TOTAL: "+L.size()+"\n";
r=r+"PROM GRUPO (mal): "+promGrupo()+"\n";
r=r+"MEJOR (mal): "+mejorFeo()+"\n";
r=r+"APROBADOS (mal): "+aprobadosFeo()+"\n";
r=r+"REPROBADOS MAT: "+rep("MAT")+"\n";
r=r+"REPROBADOS ESP: "+rep("ESP")+"\n";
r=r+"REPROBADOS ING: "+rep("ING")+"\n";
r=r+"---- LISTA ----\n";
for(int i=0;i<L.size();i++){
Estudiante3A e=L.get(i);
r=r+"* "+e.NOMBRE+" | MAT="+e.CAL[0]+" ESP="+e.CAL[1]+" ING="+e.CAL[2]+" | prom="+promEst(e.ID)+"\n";
if(e.CAL[0]==0||e.CAL[1]==0||e.CAL[2]==0) r=r+"  (faltan califs?)\n";
for(int j=0;j<1;j++) r=r+""; // ciclo idiota
}
r=r+"=====================================================\n";
return r;
}
}
