/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import modelo.Team;

/**
 *
 * @author Óscar Ezquerro Sá
 */
public class TeamRepository {

    private EntityManager em;

    public TeamRepository(EntityManager em) {
        this.em = em;
    }

    public List<Team> buscarEquiposPorRoster(String roster) {
        List<Team> equipos = new ArrayList<>();

        equipos = em.createQuery("FROM Team WHERE roster = :roster", Team.class)
                .setParameter("roster", roster).getResultList();

        return equipos;
    }

    public String insertarEquipo(Team team) {
        em.getTransaction().begin();
        em.persist(team);
        em.getTransaction().commit();

        return "Se ha insertado el equipo correctamente";
    }

    public String modificarEquipo(int id, String nombre) {
        Team equipo = em.find(Team.class, id);

        if (equipo != null) {
            em.getTransaction().begin();
            equipo.setName(nombre);
            em.merge(equipo);
            em.getTransaction().commit();
            return "Se ha modificado correctamente el equipo con ID " + id;
        } else {
            return "No se ha podido modificar el equipo con ID " + id + " ya que no existe";
        }
    }

    public String borrarEquipoPorId(int id) {
        Team equipo = em.find(Team.class, id);

        if (equipo != null) {
            em.getTransaction().begin();
            em.remove(equipo);
            em.getTransaction().commit();
            return "Se ha borrado correctamente el equipo con ID " + id;
        } else {
            return "No se ha podido borrar el equipo con ID " + id + " ya que no existe";
        }
    }

}
