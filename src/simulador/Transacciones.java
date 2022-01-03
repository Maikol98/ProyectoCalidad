/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import Datos.Transaccion;
import java.util.LinkedList;

/**
 *
 * @author greco
 */
public class Transacciones {

    Transaccion DTransaccion;
    LinkedList<Transaccion> prioridaduno;
    LinkedList<Transaccion> prioridaddos;
    LinkedList<Transaccion> prioridadtres;

    public Transacciones() {
        prioridaduno = new LinkedList<>();
        prioridaddos = new LinkedList<>();
        prioridadtres = new LinkedList<>();
        DTransaccion = new Transaccion();
    }

    public int insertar(int cuentaOrigen, String nombreOrigen, int cuentaDestino, String NombreDestino, double monto, int prioridad) {
        DTransaccion = new Transaccion(cuentaOrigen, nombreOrigen, cuentaDestino, NombreDestino, monto, prioridad);
        return DTransaccion.insertar() ? 1 : 0;
    }

    public void listar() {
        LinkedList<Transaccion> lista = new LinkedList<>();
        lista = DTransaccion.listar();

        for (Transaccion datos : lista) {
            switch (datos.getPrioridad()) {
                case 1:
                    prioridaduno.add(datos);
                    break;
                case 2:
                    prioridaddos.add(datos);
                    break;
                case 3:
                    prioridadtres.add(datos);
                    break;
                default:
            }
        }
    }

    public LinkedList<String> Valor(LinkedList<Transaccion> list) {
        LinkedList<String> datos = new LinkedList<>();
        if (list.isEmpty()) {
            return null;
        }
        Transaccion dat = new Transaccion();
        dat = list.pop();

        datos.add(String.valueOf(dat.getId()));
        datos.add(String.valueOf(dat.getCuentaOrigen()));
        datos.add(dat.getNombreOrigen());
        datos.add(String.valueOf(dat.getCuentaDestino()));
        datos.add(dat.getNombreDestino());
        datos.add(String.valueOf(dat.getMonto()));
        switch (dat.getPrioridad()) {
            case 1:
                datos.add("A cuenta propia");
                break;
            case 2:
                datos.add("A otras cuentas");
                break;
            case 3:
                datos.add("A otros bancos");
                break;
            default:
        }
        return datos;
    }
    
    public static void main(String[] args) {
        Transacciones trans = new Transacciones();
        int valor = trans.insertar(12345, "Maikol Sanchez", 00012, "Nell Antoni", 100, 3);
        System.out.println(valor);
    }
}
