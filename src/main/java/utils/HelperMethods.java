package utils;

import java.io.File;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class HelperMethods {

    /**
     * Devolver path con rutas ya configuradas para Chrome
     */
    public static String getProperty() {
        String dirDriver = System.getProperty("user.dir");
        return dirDriver + "\\WebDriver\\Chrome\\Windows\\chromedriver.exe";
    }


    /**
     * Fecha actual
     */
    public static String fechaActual() {
        Calendar ahora = Calendar.getInstance();
        int n = (ahora.get(Calendar.YEAR) * 10000) + ((ahora.get(Calendar.MONTH) + 1) * 100) + (ahora.get(Calendar.DAY_OF_MONTH));
        return Integer.toString(n);
    }

    /**
     * Comprobar si el valor es numerico
     *
     * @param valor Parametro a comprobar si es numerico
     */
    public static boolean isNumeric(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException ne) {
            return false;
        }
    }

    public long dateDiff(String fechaInicio, String fechaFin) {
        return dateDiff(fechaInicio, fechaFin, 0);
    }

    public long dateDiff(String fechaInicio, String fechaFin, int intervalo) {
        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();
        inicio.set(Integer.parseInt(fechaInicio.substring(0, 4)), Integer.parseInt(fechaInicio.substring(4, 6)) - 1, Integer.parseInt(fechaInicio.substring(6, 8)));
        fin.set(Integer.parseInt(fechaFin.substring(0, 4)), Integer.parseInt(fechaFin.substring(4, 6)) - 1, Integer.parseInt(fechaFin.substring(6, 8)));
        inicio.add(Calendar.DAY_OF_MONTH, +intervalo);
        return fin.getTimeInMillis() - inicio.getTimeInMillis();
    }

    public long diferenciaDias(String fechaInicio, String fechaFin) {
        long dias = 0;
        dias = dateDiff(fechaInicio, fechaFin) / (3600 * 24 * 1000);
        return dias;
    }

    public String formateaFecha(String fecha) {
        try {
            String fechaFormato = fecha.substring(6, 8);
            fechaFormato += "/" + fecha.substring(4, 6);
            fechaFormato += "/" + fecha.substring(0, 4);
            return fechaFormato;
        } catch (Exception e) {
            return fecha;
        }
    }

    /**
     * Crear digito verificador mediante numeros x entregados
     *
     * @param r digitos para generar rut
     * @return String
     */
    public String generaDvRut(String r) {
        String dv = "";
        int rut, digito, suma, resto, resultado, factor;
        rut = Integer.parseInt(r);
        for (factor = 2, suma = 0; rut > 0; factor++) {
            digito = rut % 10;
            rut /= 10;
            suma += digito * factor;
            if (factor >= 7)
                factor = 1;
        }
        resto = suma % 11;
        resultado = 11 - resto;
        if (resultado < 10)
            dv = String.valueOf(resultado);
        else if (resultado == 10)
            dv = "K";
        else
            dv = "0";

        return dv;
    }

    public String getDate() {
        Calendar fecha = new GregorianCalendar();
        Integer annio = fecha.get(Calendar.YEAR);
        Integer mes = fecha.get(Calendar.MONTH) + 1;
        Integer dia = fecha.get(Calendar.DAY_OF_MONTH);
        Integer hora = fecha.get(Calendar.HOUR_OF_DAY);
        Integer minuto = fecha.get(Calendar.MINUTE);
        Integer segundo = fecha.get(Calendar.SECOND);
        String anio = String.valueOf(annio) + mes + dia;
        String horaTotal = String.valueOf(hora) + minuto + segundo;
        return anio + "_" + horaTotal;
    }

    public String getDateFormated() {
        Calendar fecha = new GregorianCalendar();
        Integer annio = fecha.get(Calendar.YEAR);
        Integer mes = fecha.get(Calendar.MONTH) + 1;
        Integer dia = fecha.get(Calendar.DAY_OF_MONTH);
        Integer hora = fecha.get(Calendar.HOUR_OF_DAY);
        Integer minuto = fecha.get(Calendar.MINUTE);
        Integer segundo = fecha.get(Calendar.SECOND);
        String anio = annio + "-" + mes + "-" + dia;
        String horaTotal = hora + ":" + minuto + ":" + segundo;
        return anio + " " + horaTotal;
    }

    public String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }

    public String generarRut() {
        String rut = String.valueOf(new Random().nextInt(9999999) + 10000000);
        int multi = 2;
        int acum = 0;
        for (int i = rut.length() - 1; i > -1; i--) {
            if (multi > 7) {
                multi = 2;
            }
            acum += multi * Integer.parseInt(rut.substring(i, i + 1));
            multi++;
        }
        int dv = 11 - (acum % 11);
        String dvf = "";
        if (dv > 9) {
            if (dv == 10) {
                dvf = "K";
            }
            if (dv == 11) {
                dvf = "0";
            }
        } else {
            dvf = String.valueOf(dv);
        }
        return rut + "-" + dvf;
    }

    public void deleteDirectory(File folder) {
        if (folder.exists()) {
            if (folder.isDirectory()) {
                for (File file : folder.listFiles()) {
                    if (file.isDirectory() && file.getName().contains("img")) {
                        for (File img : file.listFiles()) {
                            System.out.println("se elimino archivo " + file.getPath());
                            img.delete();
                        }
//						if(!file.getName().toLowerCase().contains(".rar"))
//						if(file.delete()) {
//							System.out.println("se elimino archivo "+ file.getPath());
//						}else {
//							System.out.println("No se elimino archivo "+ file.getPath());
//						}
                    }
                }
            }
        }
    }


}

