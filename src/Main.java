import java.util.ArrayList;
import java.util.List;

public class Main {

    private double coordenada1;
    private double coodenada2;
    private int range;

    public enum dir {ARRIBA, ABAJO, DERECHA, IZQUIERDA};

    public void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Se necesitan 3 args: coordenada1, coordenada2, rango");
        }
        coordenada1 = Integer.parseInt(args[0]);
        coodenada2 = Integer.parseInt(args[1]);
        range = Integer.parseInt(args[2]);

        String[] arrayUrbs = obtenerUrbanizaciones(coordenada1, coodenada2, range);
        System.out.print("La lista es: ");
        for (int i = 0; i < arrayUrbs.length; i++) {
            if (i == arrayUrbs.length - 1) {
                System.out.print(arrayUrbs[i] + ", ");
            } else {
                System.out.print(arrayUrbs[i]);
            }
        }

    }

    public String[] obtenerUrbanizaciones(double coord1, double coord2, int rango) {


        double tam2 = rango * 2 + 1;
        int tam = (int) Math.pow(tam2, 2);
        String[] listaUrb = new String[tam];
        String idIni = obtenerIdentificadorUrbanización(coord1, coord2);
        int contador = 0;
        listaUrb[contador] = idIni;


//        //******************** Opcion 1 ********************//
//
//        String[] direccion = new String[4];
//        direccion[0] = "ARRIBA";
//        direccion[1] = "ABAJO";
//        direccion[2] = "DERECHA";
//        direccion[3] = "IZQUIERDA";
//
//
//        for (int i = 0; i < direccion.length; i++) {
//            String columCentral = idIni;
//            for (int j = 0; j < rango; j++) {
//                try {
//                    columCentral = obtenerAdyacente(columCentral, direccion[j]);
//                    contador++;
//                    listaUrb[contador] = columCentral;
//                } catch (NullPointerException ex) {
//                    System.out.println("La parcela siguiente a: " + columCentral + " en la direccion: " + direccion[i] + " no existe");
//                    System.out.println(ex.getMessage());
//                    return listaUrb;
//                }
//
//
//                if (direccion[i].equals("ARRIBA") || direccion[i].equals("ABAJO")) {
//                    for (int k = 2; k < direccion.length; k++) {
//                        String idLateral = columCentral;
//                        for (int l = 0; l < rango; l++) {
//                            try {
//                                idLateral = obtenerAdyacente(idLateral, direccion[k]);
//                                contador++;
//                                listaUrb[contador] = idLateral;
//                            } catch (NullPointerException ex) {
//                                System.out.println("La parcela siguiente a: " + idLateral + " en la direccion: " + direccion[k] + "no existe");
//                                System.out.println(ex.getMessage());
//                                return listaUrb;
//                            }
//                        }
//                    }
//                }
//
//            }
//        }


        //******************** Fin opcion 1 ********************//



        //******************** Opcion 1 MOD ********************//

        ArrayList<String> vistados = new ArrayList<String>();


        ArrayList<String> columCentral = new ArrayList<String>();
        columCentral.add(idIni);

        for (int j = 0; j < rango; j++) {
            String id = columCentral.get(columCentral.size()-1);
            String idNew = "";
            try {
                idNew = obtenerAdyacente(id, dir.ABAJO.toString());
            } catch (NullPointerException ex) {
                System.out.println("La parcela adyacente a: " + id +" en la direccion: "+ dir.ABAJO.toString() + " no existe");
                System.out.println(ex.getMessage());
                String[] stringArray = columCentral.toArray(new String[columCentral.size()]);
                return stringArray;
            }
            columCentral.add(idNew);
        }

        for (int j = 0; j < rango; j++) {
            String id = columCentral.get(0);
            String idNew = "";
            try {
                idNew = obtenerAdyacente(id, dir.ARRIBA.toString());
            } catch (NullPointerException ex) {
                System.out.println("La parcela adyacente a: " + id +" en la direccion: "+ dir.ARRIBA.toString() + " no existe");
                System.out.println(ex.getMessage());
                String[] stringArray = columCentral.toArray(new String[columCentral.size()]);
                return stringArray;
            }
            columCentral.add(0, idNew);
        }


        for (String urbIdCent : columCentral) {

            ArrayList<String> fila = new ArrayList<String>();
            fila.add(urbIdCent);

            for (int j = 0; j < rango; j++) {
                String id = fila.get(fila.size() - 1);
                String idNew = "";
                try {
                    idNew = obtenerAdyacente(id, dir.IZQUIERDA.toString());
                } catch (NullPointerException ex) {
                    System.out.println("La parcela adyacente a: " + id +" en la direccion: "+ dir.IZQUIERDA.toString() + " no existe");
                    System.out.println(ex.getMessage());
                    String[] stringArray = columCentral.toArray(new String[columCentral.size()]);
                    return stringArray;
                }
                fila.add(idNew);
            }

            for (int j = 0; j < rango; j++) {
                String id = fila.get(0);
                String idNew = "";
                try {
                    idNew = obtenerAdyacente(id, dir.DERECHA.toString());
                } catch (NullPointerException ex) {
                    System.out.println("La parcela adyacente a: " + id +" en la direccion: "+ dir.DERECHA.toString() + " no existe");
                    System.out.println(ex.getMessage());
                    String[] stringArray = columCentral.toArray(new String[columCentral.size()]);
                    return stringArray;
                }
                fila.add(0, idNew);
            }

            vistados.addAll(fila);
        }

        String[] stringArray = vistados.toArray(new String[vistados.size()]);

        listaUrb = stringArray;


        //******************** Fin opcion 1 MOD ********************//



        //******************** Opcion 2 ********************//
//
//        String posIni = idIni;
//        for (int i = 0; i < rango; i++) {
//            try {
//                posIni = obtenerAdyacente(posIni, "ARRIBA");
//            } catch (NullPointerException ex) {
//                System.out.println("La parcela siguiente no existe");
//                System.out.println(ex.getMessage());
//                return listaUrb;
//            }
//            try {
//                posIni = obtenerAdyacente(posIni, "IZQUIERDA");
//            } catch (NullPointerException ex) {
//                System.out.println("La parcela siguiente no existe");
//                System.out.println(ex.getMessage());
//                return listaUrb;
//            }
//        }
//
//        listaUrb[contador] = posIni;
//        contador++;
//
//        String posUrb = posIni;
//        for (int j = 0; j < rango * 2; j++) {
//            String posUrb2 = posUrb;
//            for (int k = 0; k < rango * 2; k++) {
//                try {
//                    posUrb2 = obtenerAdyacente(posUrb2, "ABAJO");
//                    listaUrb[contador] = posUrb2;
//                    contador++;
//                } catch (NullPointerException ex) {
//                    System.out.println("La parcela siguiente no existe");
//                    System.out.println(ex.getMessage());
//                    return listaUrb;
//                }
//            }
//            if (j < rango - 1) {
//                try {
//                    posUrb = obtenerAdyacente(posUrb, "DERECHA");
//                    listaUrb[contador] = posUrb;
//                    contador++;
//                } catch (NullPointerException ex) {
//                    System.out.println("La parcela siguiente no existe");
//                    System.out.println(ex.getMessage());
//                    return listaUrb;
//                }
//            }
//        }
//
//
        //******************** Fin opcion 2 ********************//



        return listaUrb;
    }


//    // obtenerIdentificadorUrbanización(coordenadaX, coordenadaY) = identificadorUrbanización
//    private String obtenerIdentificadorUrbanización(double coordenadaX, double cooordenadaY) {
//
//        String respuesta = "id urbanizacion8";
//
//
//        return respuesta;
//    }
//
//
//    //obtenerAdyacente(identificadorUrbanizaciónOrigen, dirección) = identificadorAdyacente
//    private String obtenerAdyacente(String identificadorUrbanizaciónOrigen, String dirección) {
//
//        String respuesta = "id urbanizacion8";
//
//
//        return respuesta;
//    }

}