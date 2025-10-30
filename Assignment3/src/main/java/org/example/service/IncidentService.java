package org.example.service;

import assignment.tables.pojos.Incident;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.IncidentDTO;
import org.example.repository.IncidentRepository;
import org.example.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
public class IncidentService {
    @Resource private IncidentRepository incidentRepository;
    @Resource private ShopRepository shopRepository;
    private final Scanner scanner = new Scanner(System.in);

    public void listAllIncidents() {
        log.info("Here are the incidents: ");
        incidentRepository.fetchAllIncidents()
                .forEach(this::logIndividualIncident);
    }

    public void completeIncident() {
        Integer incidentId = askIncidentId();
        if (incidentId == null) {
            log.info("Failed to parse the ID. Incident completion has been cancelled.");
            return;
        }
        incidentRepository.updateIncidentIsHandled(
                incidentId, Boolean.TRUE
        );
    }

    public void editIncident() {
        Integer incidentId = askIncidentId();
        if (incidentId == null) {
            log.info("Failed to parse the ID. Incident editing has been cancelled.");
            return;
        }
        IncidentDTO incidentDTO = incidentRepository.fetchIncidentById(incidentId);

        log.info("NOTE! Leave a field empty if you wish not to edit it...");

        log.info("The current title:\n {}", incidentDTO.getTitle());
        log.info("\nWhat will the new title be: ");
        String title = scanner.nextLine();

        log.info("The current description:\n {}", incidentDTO.getDescription());
        log.info("\nWhat will the new description be: ");
        String description = scanner.nextLine();

        incidentRepository.updateIncidentContent(
                incidentId,
                title != null && !title.isEmpty() ? title : incidentDTO.getTitle(),
                description != null && !description.isEmpty() ? description : incidentDTO.getDescription()
        );
    }

    private void logIndividualIncident(IncidentDTO incidentDTO) {
        String shopName = shopRepository.fetchShopById(
                incidentDTO.getShopId()
        ).getName();
        log.info(
                "ID - {} | Title - {} | Shop - {} | Solved - {} | Description - {}",
                incidentDTO.getId(),
                incidentDTO.getTitle(),
                shopName,
                incidentDTO.isHandled() ? "YES" : "NO",
                incidentDTO.getDescription()
        );
    }

    private Integer askIncidentId() {
        log.info("What is the ID of the incident: ");
        String idString = scanner.nextLine();
        try {
            return Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Failed to parse the input: {}", idString);
            return null;
        }
    }
}
