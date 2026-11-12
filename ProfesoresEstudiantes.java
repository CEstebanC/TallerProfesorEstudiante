import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProfesoresEstudiantes {
    ArrayList<String> listaProfesores;
    ArrayList<String> listEstudiantes;
    ArrayList<ArrayList<String>> listaGeneralEstudiantes;

    public ProfesoresEstudiantes() {
        listaProfesores = new ArrayList<>();
        listaGeneralEstudiantes = new ArrayList<>();
    }

    public void iniciar() {
        int opcion = 0;
        do {
            String menu = "<<<< MENÚ DE OPCIONES >>>>\n" +
                    "1. Registrar profesores\n" +
                    "2. Registrar estudiantes asociados al profesor\n" +
                    "3. Consultar lista total de profesores y sus estudiantes asociados\n" +
                    "4. Consultar un profesor e imprimir la lista de estudiantes asociados\n" +
                    "5. Consultar un profesor e indicar la cantidad de estudiantes asociados\n" +
                    "6. Consultar un estudiante e indicar cual es su director de grupo\n" +
                    "7. Salir\n\n" +
                    "Elija una opción:";

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                break;
            }

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    registrarProfesores();
                    break;
                case 2:
                    registrarEstudiantes();
                    break;
                case 3:
                    consultarListaProfesoresYEstudiantes();
                    break;
                case 4:
                    consultaProfesorPorNombre();
                    break;
                case 5:
                    consultaCantidadEstudiantesPorProfesor();
                    break;
                case 6:
                    consultarEstudiante();
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private void registrarProfesores() {
        int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de profesores a registrar:"));
        for (int i = 0; i < cant; i++) {
            String nombreProfesor = JOptionPane.showInputDialog("Ingrese el nombre del profesor " + (i + 1) + ":");
            if (nombreProfesor != null && !nombreProfesor.trim().isEmpty()) {
                if (listaProfesores.contains(nombreProfesor)) {
                    JOptionPane.showMessageDialog(null, "El profesor " + nombreProfesor + " ya existe. No se puede registrar dos veces.");
                    i--;
                } else {
                    listaProfesores.add(nombreProfesor);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Registro de profesores exitoso.");
    }

    private void registrarEstudiantes() {
        if (listaProfesores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores antes de registrar estudiantes.");
            return;
        }

        listaGeneralEstudiantes.clear();
        for (int i = 0; i < listaProfesores.size(); i++) {
            listEstudiantes = new ArrayList<>();
            int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de estudiantes para el profesor " + listaProfesores.get(i) + ":"));
            for (int j = 0; j < cant; j++) {
                String nombreEst = JOptionPane.showInputDialog("Ingrese el nombre del estudiante " + (j + 1) + " para el profesor " + listaProfesores.get(i) + ":");
                if (nombreEst != null && !nombreEst.trim().isEmpty()) {
                    if (listEstudiantes.contains(nombreEst)) {
                        JOptionPane.showMessageDialog(null, "El estudiante " + nombreEst + " ya está registrado en este grupo.");
                        j--;
                    } else {
                        listEstudiantes.add(nombreEst);
                    }
                }
            }
            listaGeneralEstudiantes.add(listEstudiantes);
        }
        JOptionPane.showMessageDialog(null, "Registro de estudiantes exitoso.");
    }

    private void consultarListaProfesoresYEstudiantes() {
        if (listaProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        StringBuilder resultado = new StringBuilder("<<<< Lista de Profesores y Estudiantes >>>>\n");
        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
            ArrayList<String> listaTemporal = listaGeneralEstudiantes.get(i);
            resultado.append("Profesor: ").append(listaProfesores.get(i)).append(" = [");
            for (int j = 0; j < listaTemporal.size(); j++) {
                resultado.append(listaTemporal.get(j));
                if (j < listaTemporal.size() - 1) {
                    resultado.append(", ");
                }
            }
            resultado.append("]\n");
        }
        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    private void consultaProfesorPorNombre() {
        if (listaProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor a consultar:");
        if (listaProfesores.contains(nombre)) {
            int posicion = listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);
            StringBuilder resultado = new StringBuilder("Profesor: " + nombre + " = [");
            for (int j = 0; j < listaEstudiantesTemporal.size(); j++) {
                resultado.append(listaEstudiantesTemporal.get(j));
                if (j < listaEstudiantesTemporal.size() - 1) {
                    resultado.append(", ");
                }
            }
            resultado.append("]");
            JOptionPane.showMessageDialog(null, resultado.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No se encuentra el profesor " + nombre);
        }
    }

    private void consultaCantidadEstudiantesPorProfesor() {
        if (listaProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor para saber la cantidad de estudiantes:");
        if (listaProfesores.contains(nombre)) {
            int posicion = listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);
            JOptionPane.showMessageDialog(null, "La cantidad de estudiantes asociados al profesor " + nombre + " es " + listaEstudiantesTemporal.size());
        } else {
            JOptionPane.showMessageDialog(null, "No se encuentra el profesor " + nombre);
        }
    }

    private void consultarEstudiante() {
        if (listaProfesores.isEmpty() || listaGeneralEstudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar:");
        boolean encontrado = false;
        StringBuilder mensaje = new StringBuilder();

        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {
            ArrayList<String> listTemporal = listaGeneralEstudiantes.get(i);
            if (listTemporal.contains(nombre)) {
                encontrado = true;
                mensaje.append("El estudiante ").append(nombre).append(" pertenece al grupo del profesor ").append(listaProfesores.get(i)).append("\n");
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, mensaje.toString());
        } else {
            JOptionPane.showMessageDialog(null, "El estudiante " + nombre + " no se encuentra registrado en ningún grupo.");
        }
    }
}