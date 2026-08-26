/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Observer;

/**
 * Representa las modalidades de notificación disponibles para un observador
 * (Barist) dentro del patrón Observer.
 *
 * PUSH_COMPLETO      -> el Subject envía la lista completa de lotes actuales.
 * PUSH_INCREMENTAL   -> el Subject envía únicamente el lote recién agregado.
 * PULL               -> el Subject solo avisa que hubo un cambio y el propio
 *                        observador consulta ("hala") los datos que necesita.
 *
 * @author Estudiantes
 */
public enum ModalidadNotificacion {
    PUSH_COMPLETO,
    PUSH_INCREMENTAL,
    PULL
}
