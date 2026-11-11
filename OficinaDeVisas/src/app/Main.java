package app;

import consulado.ArchivoDeExpedientes;
import consulado.CajaDePagos;
import consulado.NotificadorDeCorreo;
import consulado.TramiteDeVisa;
import consulado.ValidadorDePasaporte;
import legado.EvaluadorMagico;
import legado.OficinaDeTodo;
import legado.SolicitudAcoplada;
import notificaciones.CitasObserver;
import notificaciones.CorreoObserver;
import notificaciones.PagosObserver;
import reporte.TablaDeAntipatrones;
import solicitudes.EstadoSolicitud;
import solicitudes.Solicitud;
import tarifas.CalculadoraDeTarifas;
import tarifas.CatalogoDeTarifas;
import tarifas.TarifaBase;
import util.Consola;
import visas.EstadoEvaluacion;
import visas.EvaluadorDeVisas;
import visas.ResultadoEvaluacion;
import visas.TipoVisa;

/**
 * Prueba de fuego: ejecuta los 5 casos mostrando la version heredada
 * y la version rescatada una al lado de la otra.
 */
public class Main {

    // ---- Datos de las pruebas (todos con nombre, tambien aqui) ----
    private static final String VIAJERO_CASO_1 = "Marta";
    private static final String PASAPORTE_VALIDO = "AB123456";
    private static final double PRECIO_CASO_1 = 190.0;

    private static final double TARIFA_BASE_INICIAL = 50;
    private static final double TARIFA_BASE_NUEVA = 60;
    private static final int MESES_PRUEBA = 3;
    private static final double TOLERANCIA = 0.0001;

    private static final int CODIGO_ESTUDIANTE_HEREDADO = 2;
    private static final int CODIGO_INEXISTENTE = 9;
    private static final int EDAD_ESTUDIANTE = 20;

    private static final String VIAJERO_CASO_4 = "Luis";
    private static final int EDAD_CASO_4 = 22;

    private static final String VIAJERO_CASO_5 = "Ana";
    private static final int EDAD_CASO_5 = 30;
    private static final int MESES_CASO_5 = 6;

    private static final int SERVICIOS_A_NOTIFICAR = 3;
    private static final int LUGARES_ESPERADOS_VERSION_NUEVA = 1;

    private static int verificacionesFallidas = 0;

    public static void main(String[] args) {
        caso1GodObject();
        caso2CopyPaste();
        caso3MagicNumbers();
        caso4AcoplamientoFuerte();
        caso5Integracion();
        resumenFinal();
    }

    // ------------------------------------------------------------------ CASO 1
    private static void caso1GodObject() {
        Consola.titulo("CASO 1 - God Object: tramitar la visa de " + VIAJERO_CASO_1);

        Consola.subtitulo("Version HEREDADA: Ramiro lo hace todo");
        boolean heredada = new OficinaDeTodo().procesar(VIAJERO_CASO_1, PASAPORTE_VALIDO, PRECIO_CASO_1);

        Consola.subtitulo("Version RESCATADA: una clase por responsabilidad");
        TramiteDeVisa tramite = new TramiteDeVisa(new ValidadorDePasaporte(), new CajaDePagos(),
                new ArchivoDeExpedientes(), new NotificadorDeCorreo());
        boolean rescatada = tramite.tramitar(VIAJERO_CASO_1, PASAPORTE_VALIDO, PRECIO_CASO_1);

        verificar("Ambas aprueban el tramite", heredada && rescatada);
    }

    // ------------------------------------------------------------------ CASO 2
    private static void caso2CopyPaste() {
        Consola.titulo("CASO 2 - Copy-Paste Programming: subir la tarifa base de "
                + (int) TARIFA_BASE_INICIAL + " a " + (int) TARIFA_BASE_NUEVA);

        Consola.subtitulo("Version HEREDADA (formula clonada 3 veces)");
        legado.CalculadoraDeTarifas clonada = new legado.CalculadoraDeTarifas();
        clonada.subirTarifaBaseTurista(TARIFA_BASE_NUEVA);
        clonada.subirTarifaBaseEstudiante(TARIFA_BASE_NUEVA);
        clonada.subirTarifaBaseTrabajo(TARIFA_BASE_NUEVA);
        Consola.registrar("Heredada", "Lugares editados: " + clonada.getLugaresEditados());
        Consola.registrar("Heredada", "Turista=" + clonada.visaTurista(MESES_PRUEBA)
                + "  Estudiante=" + clonada.visaEstudiante(MESES_PRUEBA)
                + "  Trabajo=" + clonada.visaTrabajo(MESES_PRUEBA));

        Consola.subtitulo("Version HEREDADA si alguien edita solo 2 lugares (lo que paso en el consulado)");
        legado.CalculadoraDeTarifas descuidada = new legado.CalculadoraDeTarifas();
        descuidada.subirTarifaBaseTurista(TARIFA_BASE_NUEVA);
        descuidada.subirTarifaBaseEstudiante(TARIFA_BASE_NUEVA);
        Consola.registrar("Heredada", "Trabajo=" + descuidada.visaTrabajo(MESES_PRUEBA)
                + "  <-- se quedo con la tarifa vieja (deberia ser "
                + clonada.visaTrabajo(MESES_PRUEBA) + ")");

        Consola.subtitulo("Version RESCATADA (Strategy)");
        TarifaBase base = new TarifaBase(TARIFA_BASE_INICIAL);
        CalculadoraDeTarifas estrategia = CatalogoDeTarifas.crear(base);
        base.cambiarPrecioPorMes(TARIFA_BASE_NUEVA);
        Consola.registrar("Strategy", "Lugares editados: " + base.getCambiosRealizados());
        double turista = estrategia.calcular(TipoVisa.TURISTA, MESES_PRUEBA);
        double estudiante = estrategia.calcular(TipoVisa.ESTUDIANTE, MESES_PRUEBA);
        double trabajo = estrategia.calcular(TipoVisa.TRABAJO, MESES_PRUEBA);
        Consola.registrar("Strategy", "Turista=" + turista + "  Estudiante=" + estudiante
                + "  Trabajo=" + trabajo);

        verificar("Heredada exigio 3 ediciones y Strategy 1",
                clonada.getLugaresEditados() == SERVICIOS_A_NOTIFICAR
                        && base.getCambiosRealizados() == LUGARES_ESPERADOS_VERSION_NUEVA);
        verificar("El precio final coincide en ambas versiones",
                iguales(clonada.visaTurista(MESES_PRUEBA), turista)
                        && iguales(clonada.visaEstudiante(MESES_PRUEBA), estudiante)
                        && iguales(clonada.visaTrabajo(MESES_PRUEBA), trabajo));
    }

    // ------------------------------------------------------------------ CASO 3
    private static void caso3MagicNumbers() {
        Consola.titulo("CASO 3 - Magic Numbers: evaluar visas");

        EvaluadorMagico heredado = new EvaluadorMagico();
        EvaluadorDeVisas nuevo = new EvaluadorDeVisas();

        Consola.subtitulo("tipo " + CODIGO_ESTUDIANTE_HEREDADO + " y edad " + EDAD_ESTUDIANTE
                + "  vs  TipoVisa.ESTUDIANTE");
        String resultadoHeredado = heredado.evaluar(CODIGO_ESTUDIANTE_HEREDADO, EDAD_ESTUDIANTE);
        ResultadoEvaluacion resultadoNuevo = nuevo.evaluar(TipoVisa.ESTUDIANTE, EDAD_ESTUDIANTE);
        Consola.registrar("Heredada", resultadoHeredado);
        Consola.registrar("Rescatada", resultadoNuevo.estado() + " -> " + resultadoNuevo.mensaje());

        Consola.subtitulo("tipo inexistente " + CODIGO_INEXISTENTE);
        String silencioso = heredado.evaluar(CODIGO_INEXISTENTE, EDAD_ESTUDIANTE);
        ResultadoEvaluacion claro = nuevo.evaluarPorCodigo(CODIGO_INEXISTENTE, EDAD_ESTUDIANTE);
        Consola.registrar("Heredada", silencioso + "   (sin ninguna explicacion)");
        Consola.registrar("Rescatada", claro.estado() + " -> " + claro.mensaje());

        verificar("Mismo resultado para tipo 2 / ESTUDIANTE",
                resultadoHeredado.equals(resultadoNuevo.estado().name()));
        verificar("El tipo 9 se rechaza con mensaje claro en la version nueva",
                claro.estado() == EstadoEvaluacion.RECHAZADA && claro.mensaje().contains("invalido"));
    }

    // ------------------------------------------------------------------ CASO 4
    private static void caso4AcoplamientoFuerte() {
        Consola.titulo("CASO 4 - Acoplamiento fuerte: cancelar una solicitud");

        Consola.subtitulo("Version HEREDADA: SolicitudAcoplada llama a los servicios a mano");
        SolicitudAcoplada acoplada = new SolicitudAcoplada();
        acoplada.cancelar();
        Consola.registrar("Heredada", "Estado: " + acoplada.getEstado());
        if (!acoplada.reembolsoFueProcesado()) {
            Consola.registrar("Heredada", "!! REEMBOLSO OLVIDADO: la caja de pagos nunca se entero");
        }

        Consola.subtitulo("Version RESCATADA: Observer");
        Solicitud solicitud = new Solicitud(VIAJERO_CASO_4, TipoVisa.ESTUDIANTE, EDAD_CASO_4, MESES_PRUEBA);
        PagosObserver pagos = new PagosObserver();
        solicitud.suscribir(new CorreoObserver());
        solicitud.suscribir(new CitasObserver());
        solicitud.suscribir(pagos);
        solicitud.cancelar();
        Consola.registrar("Rescatada", "Estado: " + solicitud.getEstado());

        verificar("La heredada olvido el reembolso", !acoplada.reembolsoFueProcesado());
        verificar("La nueva aviso a correo, citas y pagos (reembolsos: "
                        + pagos.getReembolsosProcesados() + ")",
                pagos.getReembolsosProcesados() == LUGARES_ESPERADOS_VERSION_NUEVA);
    }

    // ------------------------------------------------------------------ CASO 5
    private static void caso5Integracion() {
        Consola.titulo("CASO 5 - Integracion: viaje completo con la version nueva");

        TarifaBase base = new TarifaBase(TARIFA_BASE_NUEVA);
        CalculadoraDeTarifas tarifas = CatalogoDeTarifas.crear(base);
        EvaluadorDeVisas evaluador = new EvaluadorDeVisas();
        TramiteDeVisa tramite = new TramiteDeVisa(new ValidadorDePasaporte(), new CajaDePagos(),
                new ArchivoDeExpedientes(), new NotificadorDeCorreo());

        Consola.subtitulo("1. Solicitar");
        Solicitud solicitud = new Solicitud(VIAJERO_CASO_5, TipoVisa.TRABAJO, EDAD_CASO_5, MESES_CASO_5);
        PagosObserver pagos = new PagosObserver();
        solicitud.suscribir(new CorreoObserver());
        solicitud.suscribir(new CitasObserver());
        solicitud.suscribir(pagos);
        Consola.registrar("Main", "Solicitud creada: " + solicitud.getViajero() + ", visa "
                + solicitud.getTipo() + ", " + solicitud.getMeses() + " meses");

        Consola.subtitulo("2. Evaluar");
        ResultadoEvaluacion evaluacion = evaluador.evaluar(solicitud.getTipo(), solicitud.getEdad());
        Consola.registrar("EvaluadorDeVisas", evaluacion.estado() + " -> " + evaluacion.mensaje());

        Consola.subtitulo("3. Cobrar");
        double precio = tarifas.calcular(solicitud.getTipo(), solicitud.getMeses());
        boolean cobrado = evaluacion.fueAprobada()
                && tramite.tramitar(solicitud.getViajero(), PASAPORTE_VALIDO, precio);

        Consola.subtitulo("4. Cancelar");
        solicitud.cancelar();

        verificar("Evaluada, cobrada y cancelada con reembolso",
                evaluacion.fueAprobada() && cobrado
                        && solicitud.getEstado() == EstadoSolicitud.CANCELADA
                        && pagos.getReembolsosProcesados() == LUGARES_ESPERADOS_VERSION_NUEVA);

        Consola.subtitulo("Tabla final: antipatrones capturados");
        TablaDeAntipatrones.delConsulado().imprimir();
    }

    // ---------------------------------------------------------------- AUXILIARES
    private static boolean iguales(double a, double b) {
        return Math.abs(a - b) < TOLERANCIA;
    }

    private static void verificar(String descripcion, boolean cumplido) {
        if (!cumplido) {
            verificacionesFallidas++;
        }
        Consola.linea("   >> VERIFICACION: " + descripcion + " ... " + (cumplido ? "OK" : "FALLO"));
    }

    private static void resumenFinal() {
        Consola.titulo("RESUMEN");
        Consola.linea(verificacionesFallidas == 0
                ? "Todas las verificaciones pasaron."
                : "Verificaciones fallidas: " + verificacionesFallidas);
    }
}
