package org.example.repository;

import jakarta.annotation.Resource;
import org.example.client.ClientConnection;
import org.example.dto.IncidentDTO;
import org.example.utils.DatabaseUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static assignment.Tables.INCIDENT;
import static assignment.Tables.WAREHOUSE;

@Repository
public class IncidentRepository {
    @Resource private ClientConnection clientConnection;
    @Resource private DatabaseUtils databaseUtils;

    public void createIncident(IncidentDTO incidentDTO) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .insertInto(INCIDENT)
                .columns(INCIDENT.TITLE,  INCIDENT.DESCRIPTION, INCIDENT.SHOPID, INCIDENT.ISHANDLED)
                .values(incidentDTO.getTitle(), incidentDTO.getDescription(), incidentDTO.getShopId(), incidentDTO.isHandled())
                .execute();
    }

    public List<IncidentDTO> fetchAllIncidents() {
        if (!databaseUtils.isValidConnection()) {
            return List.of();
        }
        return clientConnection
                .getDslContext()
                .selectFrom(INCIDENT)
                .fetchInto(IncidentDTO.class);
    }

    public IncidentDTO fetchIncidentById(int id) {
        if (!databaseUtils.isValidConnection()) {
            return null;
        }
        return clientConnection
                .getDslContext()
                .selectFrom(INCIDENT)
                .where(INCIDENT.ID.eq(id))
                .fetchOneInto(IncidentDTO.class);
    }

    /**
     * Updating multiple values at once
     * https://www.jooq.org/doc/latest/manual/sql-building/sql-statements/update-statement/
     */
    public void updateIncidentContent(int id, String title, String description) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .update(INCIDENT)
                .set(INCIDENT.TITLE, title)
                .set(INCIDENT.DESCRIPTION, description)
                .where(WAREHOUSE.ID.eq(id))
                .execute();
    }

    public void updateIncidentIsHandled(int id, boolean isHandled) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .update(INCIDENT)
                .set(INCIDENT.ISHANDLED, isHandled)
                .where(WAREHOUSE.ID.eq(id))
                .execute();
    }
}
