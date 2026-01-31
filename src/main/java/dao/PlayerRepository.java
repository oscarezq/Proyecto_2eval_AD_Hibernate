/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import modelo.Player;

/**
 *
 * @author Óscar Ezquerro Sá
 */
public class PlayerRepository {
    private EntityManager em;

    public PlayerRepository(EntityManager em) {
        this.em = em;
    }
    
    public List<Player> buscarJugadoresPorPosicion(String posicion) {
        List<Player> players = new ArrayList<>();
        
        players = em.createQuery("FROM Player WHERE position=:pos", Player.class)
                .setParameter("pos", posicion).getResultList();
        
        return players;
    }
    
    public String insertarJugador(Player player) {
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();

        return "Se ha insertado el jugador correctamente";
    }

    public String modificarJugador(int id, String nombre) {
        Player jugador = em.find(Player.class, id);

        if (jugador != null) {
            em.getTransaction().begin();
            jugador.setName(nombre);
            em.merge(jugador);
            em.getTransaction().commit();
            return "Se ha modificado correctamente el jugador con ID " + id;
        } else {
            return "No se ha podido modificar el jugador con ID " + id + " ya que no existe";
        }
    }

    public String borrarJugadorPorId(int id) {
        Player jugador = em.find(Player.class, id);

        if (jugador != null) {
            em.getTransaction().begin();
            em.remove(jugador);
            em.getTransaction().commit();
            return "Se ha borrado correctamente el jugador con ID " + id;
        } else {
            return "No se ha podido borrar el jugador con ID " + id + " ya que no existe";
        }
    }
}
