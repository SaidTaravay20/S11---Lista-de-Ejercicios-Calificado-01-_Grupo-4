/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Entity;

public interface Observado {
    void agregarSuscriptor(Observer observer);
    void eliminarSuscriptor(Observer observer);
    void notificarSuscriptores(String mensaje);
}
